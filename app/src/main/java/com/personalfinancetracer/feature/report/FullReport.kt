package com.personalfinancetracer.feature.report

import Report
import com.personalfinancetracer.feature.summary.FullSummary

class FullReport(override var fullSummary: FullSummary): Report {
    override fun getTotalSummaryReport() {
        val depositAndWithDraw = fullSummary.getTotalSummary()

        println("Total Income: ${depositAndWithDraw["Deposit"]}")
        println("Total Expense: ${depositAndWithDraw["Deposit"]}")
        println("Balance: ${(depositAndWithDraw["Deposit"]?.toInt()?:0) - (depositAndWithDraw["WithDraw"]?.toInt()?:0)} ")
        println("-----------------------------")
    }

    override fun getCategorySummaryReport(category: String) {
        val depositAndWithDraw = fullSummary.getCategorySummary(category)

        println("Category: $category")
        println("Total Income: ${depositAndWithDraw["Deposit"]}")
        println("Total Expense: ${depositAndWithDraw["Deposit"]}")
        println("Balance: ${(depositAndWithDraw["Deposit"]?.toInt()?:0) - (depositAndWithDraw["WithDraw"]?.toInt()?:0)} ")
        println("-----------------------------")
    }

    override fun getMonthlySummaryReport(month: String) {
        val depositAndWithDraw = fullSummary.getMonthlySummary(month)

        println("month: $month")
        println("Total Income: ${depositAndWithDraw["Deposit"]}")
        println("Total Expense: ${depositAndWithDraw["Deposit"]}")
        println("Balance: ${(depositAndWithDraw["Deposit"]?.toInt()?:0) - (depositAndWithDraw["WithDraw"]?.toInt()?:0)} ")
        println("-----------------------------")
    }

    override fun getCategoryMonthlySummaryReport(category: String, month: String) {
        val depositAndWithDraw = fullSummary.getCategoryMonthlySummary(category, month)

        println("Category : $category , Month: $month")
        println("Total Income: ${depositAndWithDraw["Deposit"]}")
        println("Total Expense: ${depositAndWithDraw["Deposit"]}")
        println("Balance: ${(depositAndWithDraw["Deposit"]?.toInt()?:0) - (depositAndWithDraw["WithDraw"]?.toInt()?:0)} ")
        println("-----------------------------")
    }
}