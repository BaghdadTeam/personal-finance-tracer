package com.personalfinancetracer

interface TransactionRepository {
    fun addTransaction(transaction: Transaction)
    fun getAllTransactions(): List<Transaction>
    fun deleteTransaction(id: Int)
}