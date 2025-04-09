package com.personalfinancetracer

data class Transaction(
    val id: Int,
    val amount: Double,
    val type: String, // "Income" or "Expense"
    val category: String, // "Salary", "Rent"
    val date: String //  "YYYY-MM-DD"
)