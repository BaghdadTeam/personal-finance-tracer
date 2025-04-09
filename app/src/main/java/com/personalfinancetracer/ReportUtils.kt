package com.personalfinancetracer
fun main(){
    generateMonthlyReport(transactions)

}
fun generateMonthlyReport(transactions: List<Transaction>) {
    // Step 1: Group transactions by month
    val groupedByMonth = transactions.groupBy { it.date.substring(0, 10) }

    // Step 2: Iterate over each month and calculate totals
    groupedByMonth.forEach { (month, monthlyTransactions) ->
        // Calculate total income for the month
        val totalIncome = monthlyTransactions
            .filter { it.type == "Income" } // Filter only income transactions
            .sumOf { it.amount } // Sum their amounts

        // Calculate total expense for the month
        val totalExpense = monthlyTransactions
            .filter { it.type == "Expense" } // Filter only expense transactions
            .sumOf { it.amount } // Sum their amounts

        // Calculate balance (Income - Expense)
        val balance = totalIncome - totalExpense

        // Step 3: Print the report for the current month
        println("Month: $month")
        println("Total Income: $totalIncome")
        println("Total Expense: $totalExpense")
        println("Balance: $balance")
        println("-----------------------------")
    }
}
val transactions = listOf(
    Transaction(1, 5000.0, "Income", "Salary", "2023-10-01"),
    Transaction(2, 2000.0, "Expense", "Rent", "2023-10-05"),
    Transaction(3, 1000.0, "Income", "Bonus", "2023-10-10"),
    Transaction(4, 500.0, "Expense", "Food", "2023-10-15"),
    Transaction(5, 3000.0, "Income", "Freelance", "2023-11-01"),
    Transaction(6, 1500.0, "Expense", "Rent", "2023-11-05")
)