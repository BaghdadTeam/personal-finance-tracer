package com.personalfinancetracer

import java.time.LocalDate
import java.time.format.DateTimeFormatter
fun main() {
    // Step 1: Create an instance of the repository
    val repository = InMemoryTransactionRepository()

    // Step 2: Add some transactions
    repository.addTransaction(Transaction(1, 5000.0, "Income", "Salary", "2023-10-01"))//id , amount,type,Category,date
    repository.addTransaction(Transaction(2, 2000.0, "Expense", "Rent", "2023-10-05"))
    repository.addTransaction(Transaction(3, 1000.0, "Income", "Bonus", "2023-10-10"))

    // Step 3: Create an instance of TotalSummary
    val totalSummary = TotalSummary(repository)

    // Step 4: Print the summary
    totalSummary.printSummary()
}

class BalanceReport(private val repository: TransactionRepository) {

    fun generateMonthlyReport(month: Int, year: Int): String {
        // Get all transactions from the repository
        val transactions = repository.getAllTransactions()

        // Filter transactions by the given month and year
        val filteredTransactions = transactions.filter {
            val transactionDate = LocalDate.parse(it.date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            transactionDate.monthValue == month && transactionDate.year == year
        }

        // Calculate total income and expenses
        val totalIncome = filteredTransactions.filter { it.type == "Income" }.sumOf { it.amount }
        val totalExpenses = filteredTransactions.filter { it.type == "Expense" }.sumOf { it.amount }
        val balance = totalIncome - totalExpenses

        // Return the report as a formatted string
        return """
            |Monthly Report for $month/$year
            |Total Income: $totalIncome
            |Total Expenses: $totalExpenses
            |Balance: $balance
        """.trimMargin()// delete space
    }
}