package com.personalfinancetracer.finance.app.usecase

import com.personalfinancetracer.finance.app.Command

class AddTransactionUseCase: Command {
    override fun execute() {

        var newTransaction: List<String> = listOf()
        var userInputSize = 0
        while (userInputSize != 3) {
            println("Enter transaction details separated by space like this (amount  income/expense  category): ")
            val userInput = readln()
            newTransaction = userInput.trim().split(" ")
            userInputSize = newTransaction.size
        }


        newTransaction.let { (amount, transactionType, category) ->

            println(
                "Successfully Added New  " +
                        "Transaction : $amount$ ${category.uppercase()} ${transactionType.uppercase()}"
            )
        }
    }
}