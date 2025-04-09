package com.personalfinancetracer.repository

import com.personalfinancetracer.models.Transaction

interface DataSource {

    /**
     * Saves a single transaction.
     *
     * @param transaction The transaction to be saved.
     * @return `true` if the transaction was saved successfully, `false` otherwise.
     */
    fun saveTransaction(transaction: Transaction): Boolean

    /**
     * Saves a list of transactions.
     *
     * @param transactions The list of transactions to be saved.
     * @return `true` if all transactions were saved successfully, `false` otherwise.
     */
    fun saveTransactions(transactions: List<Transaction>): Boolean

    /**
     * Loads all transactions.
     *
     * @return A list of transactions.
     */
    fun loadTransactions(): List<Transaction>
}