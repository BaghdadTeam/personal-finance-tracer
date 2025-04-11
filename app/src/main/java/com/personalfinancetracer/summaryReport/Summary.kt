package com.personalfinancetracer.summaryReport

import com.personalfinancetracer.datasource.FileTransactionStorage

interface SingleUserTransactionSummary{
    val fileTransactionStorage: FileTransactionStorage
    fun getBalanceReport() : Map<String , Double>
    fun getMonthlySummary(month : String) : Map<String , Double>
}

