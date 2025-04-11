package com.personalfinancetracer.summaryReport

import com.personalfinancetracer.models.Category
import com.personalfinancetracer.models.Transaction
import com.personalfinancetracer.models.TransactionType
import java.util.Date



fun testCases(){
    // valid test cases
    testTotalSummaryReport(
        "Valid Total summary report",
        correctResult = true,
        mapResult = mapOf("Income" to 1000.0, "Expense" to 0.0, "Balance" to 1000.0),
        inputTransaction = listOf(
            Transaction(date = Date(2020 , 1 ,10) , category = Category.SALARY , type = TransactionType.INCOME , amount = 1000.0 )))

    testMonthlySummaryReport(
        "Valid Monthly summary report",
        correctResult = true,
        mapResult = mapOf("Income" to 1000.0, "Expense" to 0.0, "Balance" to 1000.0),
        inputTransaction = listOf(Transaction(date = Date(2020 , 1 ,10) , category = Category.SALARY , type = TransactionType.INCOME , amount = 1000.0 )),
        month = "January")

    testMonthlySummaryReport(
        "different month",
        correctResult = true,
        mapResult = mapOf("Income" to 0.0, "Expense" to 0.0, "Balance" to 0.0),
        inputTransaction = listOf(Transaction(date = Date(2020 , 1 ,10) , category = Category.SALARY , type = TransactionType.INCOME , amount = 1000.0 )),
        month = "February")

    // Invalid test cases
    testTotalSummaryReport(
        "wrong map result",
        correctResult = false,
        mapResult = mapOf("Income" to 1000.0, "Expense" to 0.0, "Balance" to 100.0),
        inputTransaction = listOf(
            Transaction(date = Date(2020 , 1 ,10) , category = Category.SALARY , type = TransactionType.INCOME , amount = 1000.0 )))

}


fun testTotalSummaryReport(
        name : String,
        correctResult : Boolean,
        mapResult : Map<String, Double>,
        inputTransaction : List<Transaction>,
        ) {

        val summaryReport = SummaryReport(inputTransaction)
        val summaryResult = summaryReport.getTotalSummary()
        val result = mapResult == summaryResult

        println()
        println("summary Result : $summaryResult")
        println("map Result : $mapResult")

        if (result == correctResult) {
            println("$name - Test passed")
        } else {
            println("$name -Test failed")
        }
}

fun testMonthlySummaryReport(
    name : String,
    correctResult : Boolean,
    mapResult : Map<String, Double>,
    inputTransaction : List<Transaction>,
    month: String
) {
    val summaryReport = SummaryReport(inputTransaction)
    val summaryResult = summaryReport.getMonthlySummary(month)
    val result = mapResult == summaryResult

    println()
    println("Month  : $month")
    println("summary Result : $summaryResult")
    println("map Result : $mapResult")

    if (result == correctResult) {
        println("$name - Test passed")
    } else {
        println("$name -Test failed")
    }
}
// {Income=1000.0, Expense=700.0, Balance=300.0} monthly summary report
// {Income=1000.0, Expense=850.0, Balance=150.0} total summary report


