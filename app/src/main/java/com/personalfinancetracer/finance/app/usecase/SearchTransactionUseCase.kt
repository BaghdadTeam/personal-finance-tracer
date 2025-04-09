package com.personalfinancetracer.finance.app.usecase

import com.personalfinancetracer.finance.app.Command

class SearchTransactionUseCase: Command {
    private val listOfTransactionsIds: List<Int> = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    override fun execute() {
        println("Please Enter the transaction ID you want to search for : ")
        val userInput = readln()

        if (userInput.isNotEmpty() && userInput.isNotBlank()) {
            if (idExists(userInput)) {
                println("Transaction Details : Amount 10 , Type income , Category Food")
                println(
                    "Do you want to delete or edit this transaction ?(1 for delete , 2 for edit ," +
                            " or exit)"
                )
                val userChoice = readln()
                if (userChoice.toIntOrNull() == 1) {
                    println("Transaction Deleted Successfully")
                } else if (userChoice.toIntOrNull() == 2) {
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
        return intID in listOfTransactionsIds
    }
}


