package com.personalfinancetracer.summaryReport

import com.personalfinancetracer.models.Transaction

interface Summary{
    val listTransaction: List<Transaction>
    fun getTotalSummary() : Map<String , Double>
    fun getMonthlySummary(month : String) : Map<String , Double>
}

