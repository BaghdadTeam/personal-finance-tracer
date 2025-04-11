package com.personalfinancetracer.summaryReport

import com.personalfinancetracer.datasource.DataSource

interface SingleUserTransactionSummary{
    fun getBalanceReport() : Map<String , Double>
    fun getMonthlySummary(month : String) : Map<String , Double>
}

