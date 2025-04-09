package com.personalfinancetracer.models

import java.util.Date
import java.util.UUID

data class Transaction(
    val id: UUID = UUID.randomUUID(),
    val date: Date,
    val category: Category,
    val type: TransactionType,
    val amount: Double,
)
