package com.personalfinancetracer.feature.report

class FileReader {
    fun readFile() : List<Map<String , Any>>{
        return listOf(
            mapOf("ID" to 1 , "Date" to "2020-01-01", "Category" to "Salary" , "Type" to "Deposit" , "Amount" to 1000),
            mapOf("ID" to 1 , "Date" to "2020-01-01", "Category" to "Salary" , "Type" to "Deposit" , "Amount" to 1000),
            mapOf("ID" to 1 , "Date" to "2020-01-01", "Category" to "Salary" , "Type" to "Deposit" , "Amount" to 1000),
            mapOf("ID" to 1 , "Date" to "2020-01-01", "Category" to "Salary" , "Type" to "Deposit" , "Amount" to 1000))
    }
}
