package com.personalfinancetracer

import com.personalfinancetracer.datasource.FileTransactionStorage
import com.personalfinancetracer.models.Category
import com.personalfinancetracer.models.Transaction
import com.personalfinancetracer.models.TransactionType
import java.util.Date

fun main() {

    FileTransactionStorage().saveTransaction(
        Transaction(
            date = Date(),
            category = Category.FOOD,
            type = TransactionType.EXPENSE,
            amount = 49.99
        )
    )

}