package com.personalfinancetracer.summaryReport

interface Summary{
    val testCases : TestCases
    fun getTotalSummary() : Map<String , Double>
    fun getMonthlySummary(month : String) : Map<String , Double>
}

