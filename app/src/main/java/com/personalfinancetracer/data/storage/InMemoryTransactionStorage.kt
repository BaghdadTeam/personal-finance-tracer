package com.personalfinancetracer.data.storage

import com.personalfinancetracer.models.Transaction
import com.personalfinancetracer.repository.DataSource

/**
 * An in-memory implementation of the `DataSource` interface for storing transactions.
 * This class uses a mutable list to store transactions temporarily.
 */
class InMemoryTransactionStorage: DataSource {

    /**
     * A mutable list to hold transactions in memory.
     */
    val transactions = mutableListOf<Transaction>()

    /**
     * Saves a single transaction to the in-memory storage.
     *
     * @param T The type of the transaction.
     * @param transaction The transaction to be saved.
     * @return `true` if the transaction was saved successfully, `false` otherwise.
     */
    override fun saveTransaction(transaction: Transaction): Boolean {
        return try {
            this.transactions.clear()
            this.transactions.add(transaction)
            true
        } catch (e: Exception) {
            println("Sorry there were an error while saving your transaction to the file, please try again later")
            false
        }
    }

    /**
     * Saves a list of transactions to the in-memory storage.
     *
     * @param T The type of the transactions.
     * @param transactions The list of transactions to be saved.
     * @return `true` if the transactions were saved successfully, `false` otherwise.
     */
    override fun saveTransactions(transactions: List<Transaction>): Boolean {
        return try {
            this.transactions.clear()
            this.transactions.addAll(transactions)
            true
        } catch (e: Exception) {
            println("Sorry there were an error while saving your transactions to the file, please try again later")
            false
        }
    }

    /**
     * Loads all transactions from the in-memory storage.
     *
     * @return A list of transactions of type `T`.
     */
    override fun loadTransactions(): List<Transaction> = transactions
}