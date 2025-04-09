package com.personalfinancetracer.datasource

import com.personalfinancetracer.models.Transaction
import com.personalfinancetracer.repository.DataSource
import com.personalfinancetracer.utils.JsonUtil
import java.io.File

/**
 * A storage implementation for transactions that uses a file as the data source.
 * This class provides methods to save and load transactions to/from a file.
 */
class FileTransactionStorage : DataSource {

    /**
     * Saves a single transaction to the file.
     *
     * @param transaction The transaction to save.
     * @return `true` if the transaction was successfully saved, `false` otherwise.
     */
    override fun saveTransaction(transaction: Transaction): Boolean {
        return try {
            file.appendText(JsonUtil.serializeTransaction(transaction))
            println("Transaction has been saved to file: $FILE_NAME")
            true
        } catch (e: Exception) {
            println("Sorry there were an error while saving your transaction to the file, please try again later")
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
            file.readText().let { JsonUtil.deserializeTransactionList(it) }
        } catch (e: Exception) {
            println("Sorry there were an error while loading your transactions from the file, please try again later")
            emptyList()
        }
        return transaction
    }

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
        } catch (e: Exception) {
            println("Sorry there were an error while editing your transaction in the file, please try again later")
            false
        }
    }

    override fun deleteTransaction(transactionID: String, transaction: Transaction): Boolean {
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
        } catch (e: Exception) {
            println("Sorry there were an error while deleting your transaction from the file, please try again later")
            false
        }
    }

    companion object {
        /**
         * The name of the file where transactions are stored.
         */
        private const val FILE_NAME = "transactions.txt"

        /**
         * The file object representing the transactions file.
         */
        val file = File(FILE_NAME)
    }
}