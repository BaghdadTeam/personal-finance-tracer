package com.personalfinancetracer.finance.app

import com.personalfinancetracer.models.Category
import com.personalfinancetracer.models.TransactionType

interface TransactionsServices {
    fun addTransactionService(amount: String, category: Category, transactionType: TransactionType)
    fun editTransactionService(ID: String)
    fun viewTransactionService(ID: String)
    fun deleteTransactionService(ID: String)
    fun getAllTransactionsService()
}