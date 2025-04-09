package com.personalfinancetracer.data.storage

import com.personalfinancetracer.repository.DataSource
import com.personalfinancetracer.utils.JsonUtil
import java.io.File

/**
 * A storage implementation for transactions that uses a file as the data source.
 * This class provides methods to save and load transactions to/from a file.
 */
class FileTransactionStorage : DataSource {

    /**
     * Saves a list of transactions to the file.
     *
     * @param transactions The list of transactions to save.
     * @return `true` if the transactions were successfully saved, `false` otherwise.
     */
    override fun <T> saveTransactions(transactions: List<T>): Boolean {
        return try {
            file.printWriter().use { output ->
                output.println(JsonUtil.serializeTransactionList(transactions))
            }
            println("All transactions have been saved to file: $FILE_NAME")
            true
        } catch (e: Exception) {
            println("Sorry there were an error while saving your transactions to the file, please try again later")
            false
        }
    }

    /**
     * Saves a single transaction to the file.
     *
     * @param transaction The transaction to save.
     * @return `true` if the transaction was successfully saved, `false` otherwise.
     */
    override fun <T> saveTransaction(transaction: T): Boolean {
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
    override fun <T> loadTransactions(): List<T> {
        val transaction: List<T> = try {
            file.readText().let { JsonUtil.deserializeTransactionList(it) }
        } catch (e: Exception) {
            println("Sorry there were an error while loading your transactions from the file, please try again later")
            emptyList()
        }
        return transaction
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