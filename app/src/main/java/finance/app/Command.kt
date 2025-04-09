package finance.app

interface Command {
    fun execute()
}


class AddTransactionCommand: Command{

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
                        "Transactions : $amount$ ${category.uppercase()} ${transactionType.uppercase()}"
            )
        }
    }
}




class SearchTransactionCommand: Command{
    override fun execute() {
        TODO("Not yet implemented")
    }
}
class ListAllTransactionsCommand: Command{
    override fun execute() {

        TODO("Not yet implemented")
    }
}
class GenerateReportCommand: Command {
    override fun execute() {
        println("Enter the date in this format (1-2-2023) ")

        TODO("Not yet implemented")
    }
}

class GenerateMonthlySummaryCommand: Command{
    override fun execute() {
        TODO("Not yet implemented")
    }
}
class GenerateCategorySummaryCommand: Command{
    override fun execute() {
        TODO("Not yet implemented")
    }
}