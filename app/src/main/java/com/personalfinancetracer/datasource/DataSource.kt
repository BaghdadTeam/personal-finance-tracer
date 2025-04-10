package com.personalfinancetracer.datasource

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
     * Loads all transactions.
     *
     * @return A list of transactions.
     */
    fun getAllTransactions(): List<Transaction>

    /**
     * Edits an existing transaction.
     *
     * @param transactionID The unique identifier of the transaction to be edited.
     * @param transaction The transaction object containing updated details.
     * @return `true` if the transaction was edited successfully, `false` otherwise.
     */
    fun editTransaction(transactionID: String, transaction: Transaction): Boolean

    /**
     * Deletes an existing transaction.
     *
     * @param transactionID The unique identifier of the transaction to be deleted.
     * @param transaction The transaction object to be deleted.
     * @return `true` if the transaction was deleted successfully, `false` otherwise.
     */
    fun deleteTransaction(transactionID: String, transaction: Transaction): Boolean
}