package com.personalfinancetracer.model

import java.util.Date

data class Transaction(
    val id: Int,
    val date: Date,
    val category: Category,
    val type: TransactionType,
    val amount: Int,
    val isDelete: Boolean = false,
)
