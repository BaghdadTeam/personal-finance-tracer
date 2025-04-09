package com.personalfinancetracer

class TotalSummary(private val repository: TransactionRepository) {

    // Function to calculate the total summary
    fun calculateSummary(): Map<String, Double> {
        // Get all transactions from the repository
        val transactions = repository.getAllTransactions()// repository store we can retrieve data

        // Calculate total income and expenses
        val totalIncome = transactions
            .filter { it.type == "Income" } // Filter only income transactions
            .sumOf { it.amount } // Sum their amounts

        val totalExpenses = transactions
            .filter { it.type == "Expense" } // Filter only expense transactions
            .sumOf { it.amount } // Sum their amounts

        // Calculate balance
        val balance = totalIncome - totalExpenses

        // Return the results as a map
        return mapOf(
            "Total Income" to totalIncome,
            "Total Expenses" to totalExpenses,
            "Balance" to balance
        )
    }

    // Function to print the summary in a readable format
    fun printSummary() {
        val summary = calculateSummary()
        println("""
            |Total Summary:
            |Total Income: ${summary["Total Income"]}
            |Total Expenses: ${summary["Total Expenses"]}
            |Balance: ${summary["Balance"]}
        """.trimMargin())
    }
}