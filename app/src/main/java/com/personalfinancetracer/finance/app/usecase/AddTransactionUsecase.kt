package com.personalfinancetracer.finance.app.usecase

import com.personalfinancetracer.finance.app.Command
import com.personalfinancetracer.models.Category
import com.personalfinancetracer.models.Transaction
import com.personalfinancetracer.models.TransactionType

import kotlin.system.exitProcess

class AddTransactionUseCase: Command {

    private var isAdding = true
    override fun execute() {
        do {
            val newTransaction: Transaction
            println("Enter transaction details...")
            println("Enter amount :")
            val amount = readln()
            val transactionType = choiceTransactionType()
            val category = choiceCategory()

            //create a new instance from transaction class to add it later to transactions file
            newTransaction =
                Transaction(category = category, amount = amount.toDouble(), type = transactionType)
            // TODO: implement the logic of adding new transaction to transactions file
            println("Transaction Added Successfully")
            println(
                "New Transaction Details : ID  ${newTransaction.id} Amount : ${newTransaction.amount}$, " +
                        " Category : ${newTransaction.category} , Type : ${newTransaction.type} Date : ${newTransaction.date} "
            )

            //Check if user wanna sad
            println("Do you wanna add another transaction (y/n)")
            val continueAdding = readln().lowercase()
            if (continueAdding == "y") this.execute()
            else {
                println("Exiting ...")
                isAdding = false
            }


        } while (isAdding)
    }


    private fun choiceCategory(): Category {
        println("Please Choice Category : ")
        for (category in Category.entries) {
            println("${category.ordinal + 1} - ${category.name}")
        }

        println("0 - Quit")
        val userChoice = readln().toIntOrNull()
        if (userChoice == 0) exitProcess(0)
        // check if user choice is valid if not recall the function
        if (userChoice == null || userChoice - 1 !in Category.entries.indices) return choiceCategory()
        return Category.entries[userChoice - 1]
    }


    private fun choiceTransactionType(): TransactionType {
        println("Please Choice Transaction Type : ")
        for (transactionType in TransactionType.entries) {
            println("${transactionType.ordinal + 1} - ${transactionType.name}")
        }
        println("0 - Quit")
        val userChoice = readln().toIntOrNull()
        if (userChoice == 0) exitProcess(0)

        // check if user choice is valid if not recall the function
        if (userChoice == null || userChoice - 1 !in TransactionType.entries.indices) return choiceTransactionType()
        return TransactionType.entries[userChoice - 1]
    }
}






