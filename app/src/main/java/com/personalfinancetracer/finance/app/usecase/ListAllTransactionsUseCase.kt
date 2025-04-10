package com.personalfinancetracer.finance.app.usecase

import com.personalfinancetracer.finance.app.Command
import com.personalfinancetracer.models.Category
import com.personalfinancetracer.models.Transaction
import com.personalfinancetracer.models.TransactionType

class ListAllTransactionsUseCase:Command {

    // After merge the store to storage feature we will use actual transactions file
    private val listOfTransactions = listOf(
        Transaction(category = Category.FOOD, type = TransactionType.EXPENSE, amount = 2000.0),
        Transaction(category = Category.OTHER, type = TransactionType.EXPENSE, amount = 10000.0),
        Transaction(category = Category.TRANSPORT, type = TransactionType.EXPENSE, amount = 25000.0),
        Transaction(category = Category.SALARY, type = TransactionType.INCOME, amount = 200000.0)
    )

    override fun execute() {
        println("List of all transactions")
        if (listOfTransactions.isEmpty()) println("No transactions found")
        else
            listOfTransactions.forEachIndexed { index, transaction ->
                println("$index - ${transaction.id} | ${transaction.category} | ${transaction.type} | " +
                        "${transaction.amount} ${transaction.date}")
            }



    }
}