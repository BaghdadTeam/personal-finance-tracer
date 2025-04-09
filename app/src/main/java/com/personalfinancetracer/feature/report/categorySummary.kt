package com.personalfinancetracer.feature.report

import java.io.File

data class Transaction(
    val category: String,
    val type: String, // "Deposit" or "Withdraw"
    val amount: Int
)

class CategorySummary {
    fun summary(filePath: String, category: String): Map<String, Int> {
        // Read the file and parse it into Transactions
        val reader = File(filePath).readLines().map { line ->
            val parts = line.split(",") // Assuming CSV format
            Transaction(parts[0], parts[1], parts[2].toInt())
        }

        var deposit = 0
        var withDraw = 0

        for (row in reader) {
            if (row.category == category) {
                if (row.type == "Deposit") {
                    deposit += row.amount
                } else {
                    withDraw += row.amount
                }
            }
        }

        // Calculate balance
        val balance = deposit - withDraw

        // Return the results as a map
        return mapOf(
            "Deposit" to deposit,
            "WithDraw" to withDraw,
            "Balance" to balance
        )
    }
}