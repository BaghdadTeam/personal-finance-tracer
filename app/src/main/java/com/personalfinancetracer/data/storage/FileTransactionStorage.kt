package com.personalfinancetracer.data.storage

import com.personalfinancetracer.repository.DataSource

class FileTransactionStorage: DataSource {
    override fun <T> saveTransaction(transaction: T): Boolean {
        TODO("Not yet implemented")
    }

    override fun <T> saveTransactions(transactions: List<T>): Boolean {
        TODO("Not yet implemented")
    }

    override fun <T> loadTransactions(): List<T> {
        TODO("Not yet implemented")
    }
}