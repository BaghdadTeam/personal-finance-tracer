package com.personalfinancetracer.summaryReport

import com.personalfinancetracer.models.TransactionType
import com.personalfinancetracer.utils.isDateInRange


class SummaryReport(override val testCases: TestCases) : Summary {
    override fun getTotalSummary(): Map<String, Double> {
        val totalIncome =
            testCases.test().filter { it.type == TransactionType.INCOME }.sumOf { it.amount }
        val totalExpense =
            testCases.test().filter { it.type == TransactionType.EXPENSE }.sumOf { it.amount }

        val balance = totalIncome - totalExpense
        return mapOf("Income" to totalIncome, "Expense" to totalExpense , "Balance" to balance)
    }

    override fun getMonthlySummary(month: String): Map<String, Double> {
        val filteredTransactions = testCases.test().filter { isDateInRange(it.date, month) }
        val totalIncome =
            filteredTransactions.filter { it.type == TransactionType.INCOME }.sumOf { it.amount }
        val totalExpense =
            filteredTransactions.filter { it.type == TransactionType.EXPENSE }.sumOf { it.amount }

        val balance = totalIncome - totalExpense
        return mapOf("Income" to totalIncome, "Expense" to totalExpense , "Balance" to balance)

    }
}
