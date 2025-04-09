package com.personalfinancetracer.feature.report

import java.io.File
import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.Locale
fun main() {
    val monthlyCategorySummary = MonthlyCategorySummary()

    // Path to the CSV file
    val filePath = "transactions.csv"

    // Generate summary for a specific category and month
    val summary = monthlyCategorySummary.summary(filePath, "Food", "OCTOBER")

    // Print the summary
    println(summary)
}
data class Transactions(
    val category: String,
    val type: String, // "Deposit" or "Withdraw"
    val amount: Int,
    val date: String // Format: "yyyy-MM-dd"
)

class MonthlyCategorySummary {
    fun summary(filePath: String, category: String, month: String): Map<String, Int> {
        // Read the file and parse it into Transactions
        val reader = File(filePath).readLines().mapNotNull { line ->
            try {
                val parts = line.split(",")
                if (parts.size == 4) {
                    Transactions(parts[0], parts[1], parts[2].toInt(), parts[3])
                } else {
                    null
                }
            } catch (e: Exception) {
                println("Error parsing line: $line")
                null
            }
        }

        var deposit = 0
        var withDraw = 0

        for (row in reader) {
            if (isDateInRange(row.date, month)) {
                if (row.category == category) {
                    if (row.type == "Deposit") {
                        deposit += row.amount
                    } else {
                        withDraw += row.amount
                    }
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

    fun isDateInRange(date: String, month: String): Boolean {
        val parsedMonth = Month.valueOf(month.uppercase(Locale.getDefault()))
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val parsedDate = LocalDate.parse(date, formatter)

        val firstDayOfMonth = LocalDate.of(parsedDate.year, parsedMonth, 1)
        val lastDayOfMonth = firstDayOfMonth.with(TemporalAdjusters.lastDayOfMonth())

        return parsedDate in firstDayOfMonth..lastDayOfMonth
    }
}