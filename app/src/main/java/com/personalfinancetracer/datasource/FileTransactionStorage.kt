package com.personalfinancetracer.datasource

import com.personalfinancetracer.models.Transaction
import com.personalfinancetracer.utils.JsonUtil
import java.io.File

/**
 * A storage implementation for transactions that uses a file as the data source.
 * This class provides methods to save and load transactions to/from a file.
 */
class FileTransactionStorage(private val file: File = File(FILE_NAME)) : DataSource {

    /**
     * Saves a single transaction to the file.
     *
     * @param transaction The transaction to save.
     * @return `true` if the transaction was successfully saved, `false` otherwise.
     */
    override fun saveTransaction(transaction: Transaction): Boolean {
        return try {
            if (!file.exists()) file.createNewFile()

            val transactions = getAllTransactions().toMutableList()
            transactions.add(transaction)

            // Rewrite the entire list
            file.writeText(JsonUtil.serializeTransactionList(transactions))

            println("Transaction has been saved to file: ${file.name}")
            true
        } catch (e: Exception) {
            e.printStackTrace()
            println("Error saving transaction: ${e.message}")
            false
        }
    }

    /**
     * Loads all transactions from the file.
     *
     * @return A list of transactions loaded from the file. Returns an empty list if an error occurs.
     */
    override fun getAllTransactions(): List<Transaction> {
        val transaction: List<Transaction> = try {
            val str = file.readText()
            if (str.isEmpty()) {
                println("No transactions found in the file.")
                return emptyList()
            }
            file.readText().let { JsonUtil.deserializeTransactionList(it) }
        } catch (_: Exception) {
            println("Sorry there were an error while loading your transactions from the file, please try again later")
            emptyList()
        }
        return transaction
    }

    /**
     * Edits an existing transaction in the file.
     *
     * @param transactionID The ID of the transaction to edit.
     * @param transaction The updated transaction object.
     * @return `true` if the transaction was successfully edited, `false` otherwise.
     */
    override fun editTransaction(transactionID: String, transaction: Transaction): Boolean {
        return try {
            val transactions = getAllTransactions().toMutableList()
            val index = transactions.indexOfFirst { it.id.toString() == transactionID }
            if (index != -1) {
                transactions[index] = transaction
                file.writeText(JsonUtil.serializeTransactionList(transactions))
                println("Transaction has been edited in the file: $FILE_NAME")
                true
            } else {
                println("Transaction not found")
                false
            }
        } catch (_: Exception) {
            println("Sorry there were an error while editing your transaction in the file, please try again later")
            false
        }
    }


    /**
     * Deletes a transaction from the file.
     *
     * @param transactionID The ID of the transaction to delete.
     * @return `true` if the transaction was successfully deleted, `false` otherwise.
     */
    override fun deleteTransaction(transactionID: String): Boolean {
        return try {
            val transactions = getAllTransactions().toMutableList()
            val index = transactions.indexOfFirst { it.id.toString() == transactionID }
            if (index != -1) {
                transactions.removeAt(index)
                file.writeText(JsonUtil.serializeTransactionList(transactions))
                println("Transaction has been deleted from the file: $FILE_NAME")
                true
            } else {
                println("Transaction not found")
                false
            }
        } catch (_: Exception) {
            println("Sorry there were an error while deleting your transaction from the file, please try again later")
            false
        }
    }

    companion object {
        /**
         * The name of the file where transactions are stored.
         */
        private const val FILE_NAME = "transactions.txt"
    }
}