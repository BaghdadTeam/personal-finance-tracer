package com.personalfinancetracer.data.storage

import com.personalfinancetracer.repository.DataSource

class InMemoryTransactionStorage: DataSource {

    val transactions = mutableListOf<Any>()

    override fun <T> saveTransaction(transaction: T): Boolean {
        return try {
            this.transactions.clear()
            this.transactions.add(transaction as Any)
            true
        } catch (e: Exception) {
            println("Sorry there were an error while saving your transaction to the file, please try again later")
            false
        }
    }

    override fun <T> saveTransactions(transactions: List<T>): Boolean {
        return try {
            this.transactions.clear()
            this.transactions.addAll(transactions as List<Any>)
            true
        } catch (e: Exception) {
            println("Sorry there were an error while saving your transactions to the file, please try again later")
            false
        }
    }

    override fun <T> loadTransactions(): List<T> = transactions as List<T>
}