package finance.app

class FinanceApp {
    private var isOn = true

    fun run() {

        println("Welcome to your favorite finance personal tracker 👋")
        do {
            println(
                "What do you wanna do choice from (1-6)\n1 - Add new transaction\n" +
                        "2 - Search Transaction\n3 - List all your transactions\n4 - Generate new Report" +
                        "\n5 - Generate monthly summary\n6 - Generate Category Summary\n7 - Exit   "
            )
            println("Please Enter : ")
            val userChoice = readln()
            when (userChoice.toIntOrNull()) {
                1 -> AddTransactionCommand().execute()
                2 -> SearchTransactionCommand().execute()
                3 -> ListAllTransactionsCommand().execute()
                4 -> GenerateReportCommand().execute()
                5 -> GenerateMonthlySummaryCommand().execute()
                6 -> GenerateCategorySummaryCommand().execute()
                7 -> {
                    println("Existing the app ...")
                    isOn = false
                }


                else -> {
                    println("Please Enter a valid choice ")
                    this.run()
                }
            }



        } while (isOn)


    }

}

