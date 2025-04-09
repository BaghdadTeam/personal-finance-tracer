package com.personalfinancetracer.feature.summary

import com.personalfinancetracer.feature.report.FileReader


interface Summary{
    var reader : FileReader
    fun getTotalSummary():Map<String,Int>
    fun getCategorySummary(category : String):Map<String,Int>
    fun getMonthlySummary(month : String):Map<String,Int>
    fun getCategoryMonthlySummary(category : String , month : String):Map<String,Int>
}

