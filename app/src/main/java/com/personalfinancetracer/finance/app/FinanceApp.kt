package com.personalfinancetracer.finance.app
import com.personalfinancetracer.finance.app.cli.UserInputImpl
import com.personalfinancetracer.finance.app.services.TransactionSummaryServices
import com.personalfinancetracer.finance.app.services.TransactionsServicesImpl
import com.personalfinancetracer.models.TransactionType

class FinanceApp(private val userInput: UserInputImpl, private val services: TransactionsServicesImpl ,
                 private val transactionSummaryService : TransactionSummaryServices
) {
    private var isOn = true
    private val commandsList = listOf(
        "",
        "What do you wanna do choice from (0 - 6)", "1 - Add new transaction",
        "2 - View Transaction", "3 - Delete Transaction", "4 - Edit Transaction",
        "5 - List All Transactions", "6 - Generate Report", "7 - Get Monthly Summary", "0 - Exit"
    )


    private var balance = 0.0

    fun run() {
        println("Welcome to your favorite finance personal tracker 👋")


        do {
            for (command in commandsList) {
                println(command)
            }
            println("Balance : $balance")
            println("Please Enter : ")
            val userChoice = readln()
            when (userChoice.toIntOrNull()) {
                1 -> {
                    val amount = userInput.readAmount()
                    val transactionType = userInput.choiceTransactionType()
                    val category = userInput.choiceCategory()
                    if (amount == null || transactionType == null || category == null) {
                        println("Please Enter a valid choice... ")
                        return
                    }

                    if (amount > balance && transactionType == TransactionType.EXPENSE) println("Insufficient Balance")
                    else {
                        services.addTransactionService(
                            amount.toString(),
                            category,
                            transactionType
                        )
                        if (transactionType == TransactionType.EXPENSE)
                            balance -= amount
                        else balance += amount
                    }

                }

                2 -> {

                    val id = userInput.readID()
                    if (id == "") {
                        println("Please Enter a valid ID ... ")
                        return
                    }
                    val viewedTransaction = services.viewTransactionService(id)
                    if (viewedTransaction != null)
                        println(
                            "Transaction Details : ${viewedTransaction.id} |" +
                                    " Amount ${viewedTransaction.amount} | Type ${viewedTransaction.type} |" +
                                    " Category ${viewedTransaction.category} | ${viewedTransaction.date}"
                        )
                    else
                        println("Transaction Not Found")
                }

                3 -> {
                    val id = userInput.readID()
                    if (id == "") {
                        println("Please Enter a valid ID ... ")
                        return
                    }

                    balance = services.deleteTransactionService(id, balance)

                }

                4 -> {
                    val id = userInput.readID()
                    if (id == "") {
                        println("Please Enter a valid ID ... ")
                        return
                    }


                    val amount = userInput.readAmount()
                    val transactionType = userInput.choiceTransactionType()
                    val category = userInput.choiceCategory()
                    if (amount == null || transactionType == null || category == null) {
                        return
                    }

                    balance = services.editTransactionService(
                        id,
                        balance,
                        amount,
                        category,
                        transactionType
                    )
                }

                5 -> services.getAllTransactionsService()

                6 -> transactionSummaryService.getBalanceReport().forEach { (key, value) ->
                    println("$key : $value")
              }
                7 -> transactionSummaryService.getMonthlySummary(userInput.readMonth()).forEach { (key, value) -> println("$key : $value") }

                0 -> {
                    println("Existing the app ...")
                    isOn = false
                }

                else -> println("Please Enter a valid choice... ")

            }
        } while (isOn)
    }
}

