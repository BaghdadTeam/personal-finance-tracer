package com.personalfinancetracer.finance.app
import com.personalfinancetracer.finance.app.services.TransactionsServicesImpl
import com.personalfinancetracer.finance.app.cli.UserInputImpl
import com.personalfinancetracer.models.TransactionType

class FinanceApp {
    private var isOn = true
    private val commandsList = listOf(
        "",
        "What do you wanna do choice from (0 - 6)", "1 - Add new transaction",
        "2 - View Transaction", "3 - Delete Transaction", "4 - Edit Transaction",
        "5 - List All Transactions", "6 - Generate Report", "7 - Get Monthly Summary", "0 - Exit"
    )


    var balance = 0.0

    fun run() {
        println("Welcome to your favorite finance personal tracker 👋")
        do {
            for (command in commandsList) {
                println(command)
            }
            println("Please Enter : ")
            val userChoice = readln()
            when (userChoice.toIntOrNull()) {
                1 -> {
                    val amount = UserInputImpl().readAmount()
                    val transactionType = UserInputImpl().choiceTransactionType()
                    val category = UserInputImpl().choiceCategory()
                    if (amount > balance && transactionType == TransactionType.EXPENSE) println("Insufficient Balance")
                    else {
                        TransactionsServicesImpl().addTransactionService(
                            amount.toString(),
                            category,
                            transactionType
                        )
                        balance += amount
                    }

                }

                2 -> {
                    println("Enter the ID of the transaction:")
                    val ID = readln()
                    TransactionsServicesImpl().viewTransactionService(ID)
                }

                3 -> {
                    println("Enter the ID of the transaction:")
                    val ID = readln()
                    TransactionsServicesImpl().deleteTransactionService(ID)

                }

                4 -> {
                    println("Enter the ID of the transaction:")
                    val ID = readln()

                    TransactionsServicesImpl().editTransactionService(ID)
                }

                5 -> TransactionsServicesImpl().getAllTransactionsService()

                6 -> TODO("Generate Report")

                7 -> TODO("Get Monthly Summary")

                0 -> {
                    println("Existing the app ...")
                    isOn = false
                }

                else -> {
                    println("Please Enter a valid choice... ")
                }
            }
        } while (isOn)
    }
}

