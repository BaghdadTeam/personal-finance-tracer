package com.personalfinancetracer.finance.app.services

import com.personalfinancetracer.datasource.FileTransactionStorage
import com.personalfinancetracer.models.TransactionType
import com.personalfinancetracer.utils.isDateInRange


class TransactionSummaryServices(private val storage : FileTransactionStorage) : TransactionSummary {

    override fun getBalanceReport(): Map<String, Double> {
        val totalIncome =
            storage.getAllTransactions().filter { it.type == TransactionType.INCOME }.sumOf { it.amount }
        val totalExpense =
            storage.getAllTransactions().filter { it.type == TransactionType.EXPENSE }.sumOf { it.amount }

        val balance = totalIncome - totalExpense
        return mapOf("Income" to totalIncome, "Expense" to totalExpense , "Balance" to balance)
    }

    override fun getMonthlySummary(month: String): Map<String, Double> {

        val filteredTransactions = storage.getAllTransactions().filter { isDateInRange(it.date, month) }
        val totalIncome =
            filteredTransactions.filter { it.type == TransactionType.INCOME }.sumOf { it.amount }
        val totalExpense =
            filteredTransactions.filter { it.type == TransactionType.EXPENSE }.sumOf { it.amount }

        val balance = totalIncome - totalExpense
        return mapOf("Income" to totalIncome, "Expense" to totalExpense , "Balance" to balance)

    }
}
