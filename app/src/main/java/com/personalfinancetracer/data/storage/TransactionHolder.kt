package com.personalfinancetracer.data.storage

import com.personalfinancetracer.models.Transaction
import com.personalfinancetracer.repository.DataSource

object TransactionHolder {

    val transactions: MutableList<Transaction> = mutableListOf()

    private val fileTransactionStorage: DataSource = FileTransactionStorage()
    private val memoryTransactionStorage: DataSource = InMemoryTransactionStorage()

/**
 * Loads transactions from file storage into memory storage.
 *
 * This method clears the current list of transactions, attempts to save
 * the transactions loaded from file storage into memory storage, and if
 * successful, adds the loaded transactions to the in-memory list.
 */
        fun loadTransactions() {
            transactions.clear()
            val state = memoryTransactionStorage.saveTransactions(fileTransactionStorage.loadTransactions())
            if (state) {
                println("Transactions have been loaded from file to memory")
                transactions.addAll(memoryTransactionStorage.loadTransactions())
            }
        }

/**
 * Saves a transaction to both memory and file storage.
 *
 * @param transaction The transaction object to be saved.
 * @return `true` if the transaction was successfully saved to both memory and file storage,
 *         `false` otherwise.
 */
    fun saveTransaction(transaction: Transaction): Boolean {
        val memoryState = memoryTransactionStorage.saveTransaction(transaction)
        val fileState = fileTransactionStorage.saveTransaction(transaction)
        return if (memoryState && fileState) {
            println("Transaction has been saved to file and memory")
            true
        } else {
            println("Sorry there were an error while saving your transaction to the file, please try again later")
            false
        }
    }

    // TODO: Implement editTransaction and deleteTransaction method
}