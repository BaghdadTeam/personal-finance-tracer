package com.personalfinancetracer.finance.app.services

import com.personalfinancetracer.models.Category
import com.personalfinancetracer.models.Transaction
import com.personalfinancetracer.models.TransactionType

interface TransactionsServices {
    fun addTransactionService(amount: String, category: Category, transactionType: TransactionType)
    fun editTransactionService(id: String, balance: Double, amount: Double, category: Category, transactionType: TransactionType  ): Double
    fun viewTransactionService(id: String):Transaction?
    fun deleteTransactionService(id: String, balance: Double): Double
    fun getAllTransactionsService():List<Transaction>
}