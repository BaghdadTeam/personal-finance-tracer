package com.personalfinancetracer.finance.app.services


interface SingleUserTransactionSummaryServices{
    fun getBalanceReport() : Map<String , Double>
    fun getMonthlySummary(month : String) : Map<String , Double>
}

