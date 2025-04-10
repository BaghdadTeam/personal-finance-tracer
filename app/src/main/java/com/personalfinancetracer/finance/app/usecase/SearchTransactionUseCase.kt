package com.personalfinancetracer.finance.app.usecase

import com.personalfinancetracer.finance.app.Command

class SearchTransactionUseCase: Command {

    override fun execute() {
        println("Please Enter the transaction ID you want to search for : ")
        val userInput = readln()

        if (userInput.isNotEmpty() && userInput.isNotBlank()) {
            if (idExists(userInput)) {

                println("Transaction Details : Amount 10 , Type income , Category Food")
                println(
                    "Do you want to delete or edit this transaction ?\n1 - Delete\n2 - Edit ," +
                            "\n0 - Exit"
                )
                val userChoice = readln()
                if (userChoice.toIntOrNull() == 1) {
                    // TODO: implement actual deleting transaction from transactions file
                    println("Transaction Deleted Successfully")
                } else if (userChoice.toIntOrNull() == 2) {
                    // TODO: implement actual updating transaction from transactions file
                    println("Please Enter the new updated transaction details separated by space like this (amount  income/expense  category): ")

                } else {

                    println("Exiting ...")
                this.execute()
                }
            } else {
                println("Transaction Not Found , Please Try Again")
                this.execute()

            }
        }
    }


    private fun idExists(id: String): Boolean {
        val intID: Int = id.toIntOrNull() ?: return false
        // TODO: use actual Transactions file to check if ID exists
        return intID in 1..10
    }
}


