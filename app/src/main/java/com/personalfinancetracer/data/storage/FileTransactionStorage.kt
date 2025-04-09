package com.personalfinancetracer.data.storage

import com.personalfinancetracer.repository.DataSource
import com.personalfinancetracer.utils.JsonUtil
import java.io.File

class FileTransactionStorage : DataSource {

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

    override fun <T> loadTransactions(): List<T> {
        TODO("Not yet implemented")
    }

    companion object {
        private const val FILE_NAME = "transactions.txt"
        val file = File(FILE_NAME)
    }
}