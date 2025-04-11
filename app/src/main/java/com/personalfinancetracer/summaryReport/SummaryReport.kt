package com.personalfinancetracer.summaryReport

import com.personalfinancetracer.models.Transaction
import com.personalfinancetracer.models.TransactionType
import com.personalfinancetracer.utils.isDateInRange


class SummaryReport(override val listTransaction: List<Transaction>) : Summary {
    override fun getTotalSummary(): Map<String, Double> {
        val totalIncome =
            listTransaction.filter { it.type == TransactionType.INCOME }.sumOf { it.amount }
        val totalExpense =
            listTransaction.filter { it.type == TransactionType.EXPENSE }.sumOf { it.amount }

        val balance = totalIncome - totalExpense
        return mapOf("Income" to totalIncome, "Expense" to totalExpense , "Balance" to balance)
    }

    override fun getMonthlySummary(month: String): Map<String, Double> {
        val filteredTransactions = listTransaction.filter { isDateInRange(it.date, month) }
        val totalIncome =
            filteredTransactions.filter { it.type == TransactionType.INCOME }.sumOf { it.amount }
        val totalExpense =
            filteredTransactions.filter { it.type == TransactionType.EXPENSE }.sumOf { it.amount }

        val balance = totalIncome - totalExpense
        return mapOf("Income" to totalIncome, "Expense" to totalExpense , "Balance" to balance)

    }
}
