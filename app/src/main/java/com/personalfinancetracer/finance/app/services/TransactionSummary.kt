package com.personalfinancetracer.finance.app.services


interface TransactionSummary{
    fun getBalanceReport() : Map<String , Double>
    fun getMonthlySummary(month : String) : Map<String , Double>
}

