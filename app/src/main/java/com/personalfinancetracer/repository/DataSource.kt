package com.personalfinancetracer.repository

interface DataSource {

    // TODO: Import the Transaction class from the data module
    fun <T>saveTransaction(transaction: T): Boolean
    fun <T>saveTransactions(transactions: List<T>): Boolean

    fun <T>loadTransactions(): List<T>
}