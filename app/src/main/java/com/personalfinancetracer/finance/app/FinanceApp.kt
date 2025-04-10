package com.personalfinancetracer.finance.app
import com.personalfinancetracer.finance.app.usecase.AddTransactionUseCase
import com.personalfinancetracer.finance.app.usecase.GenerateCategorySummaryUseCase
import com.personalfinancetracer.finance.app.usecase.GenerateMonthlySummaryUseCase
import com.personalfinancetracer.finance.app.usecase.GenerateReportUseCase
import com.personalfinancetracer.finance.app.usecase.ListAllTransactionsUseCase
import com.personalfinancetracer.finance.app.usecase.SearchTransactionUseCase

class FinanceApp {
    private var isOn = true
    private val commandsList = listOf(
        "What do you wanna do choice from (0 - 6)", "1 - Add new transaction",
        "2 - Search Transaction", "3 - List all your transactions", "4 - Generate new Report",
        "5 - Generate monthly summary","6 - Generate category summary", "0 - Exit"
    )

    fun run() {
        println("Welcome to your favorite finance personal tracker 👋")
        do {
            for (command in commandsList) {
                println(command)
            }
            println("Please Enter : ")
            val userChoice = readln()
            when (userChoice.toIntOrNull()) {
                1 -> AddTransactionUseCase().execute()
                2 -> SearchTransactionUseCase().execute()
                3 -> ListAllTransactionsUseCase().execute()
                4 -> GenerateReportUseCase().execute()
                5 -> GenerateMonthlySummaryUseCase().execute()
                6 -> GenerateCategorySummaryUseCase().execute()
                0  -> {
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

