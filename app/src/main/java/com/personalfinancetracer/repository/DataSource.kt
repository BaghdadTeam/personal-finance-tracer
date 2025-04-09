package com.personalfinancetracer.repository

interface DataSource {

    /**
     * Saves a single transaction.
     *
     * @param transaction The transaction to be saved.
     * @return `true` if the transaction was saved successfully, `false` otherwise.
     */
    fun <T> saveTransaction(transaction: T): Boolean

    /**
     * Saves a list of transactions.
     *
     * @param transactions The list of transactions to be saved.
     * @return `true` if all transactions were saved successfully, `false` otherwise.
     */
    fun <T> saveTransactions(transactions: List<T>): Boolean

    /**
     * Loads all transactions.
     *
     * @return A list of transactions.
     */
    fun <T> loadTransactions(): List<T>
}