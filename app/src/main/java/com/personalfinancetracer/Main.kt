package com.personalfinancetracer

import com.personalfinancetracer.feature.report.FileReader
import com.personalfinancetracer.feature.report.FullReport
import com.personalfinancetracer.feature.summary.FullSummary
import com.personalfinancetracer.feature.summary.Summary
import java.io.File

fun main() {
        val transactions = mutableListOf<Transaction>()

        while (true) {
            println("Choose an option:")
            println("1. Add Transaction")
            println("2. Generate Monthly Report")
            println("3. Exit")

            when (readLine()?.toIntOrNull()) {
                1 -> {
                    println("Enter amount:")
                    val amount = readLine()?.toDoubleOrNull() ?: continue
                    println("Enter type (Income/Expense):")
                    val type = readLine() ?: continue
                    println("Enter category:")
                    val category = readLine() ?: continue
                    println("Enter date (YYYY-MM-DD):")
                    val date = readLine() ?: continue

                    val newTransaction = Transaction(
                        id = transactions.size + 1,
                        amount = amount,
                        type = type,
                        category = category,
                        date = date
                    )
                    transactions.add(newTransaction)
                }
                2 -> generateMonthlyReport(transactions)
                3 -> return
                else -> println("Invalid option!")
            }
        }
    }

fun SummaryReportImplementation(){
    val fileReader : FileReader = FileReader()
    val fullReport: FullReport = FullReport(FullSummary(fileReader))
    fullReport.getTotalSummaryReport()
    fullReport.getMonthlySummaryReport("January")
    fullReport.getCategorySummaryReport("Salary")
    fullReport.getCategoryMonthlySummaryReport("Salary","January")

}