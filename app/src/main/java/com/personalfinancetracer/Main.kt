import com.personalfinancetracer.datasource.FileTransactionStorage
import com.personalfinancetracer.finance.app.FinanceApp
import com.personalfinancetracer.finance.app.cli.UserInputImpl
import com.personalfinancetracer.finance.app.services.TransactionsServicesImpl

fun main() {
    val userInput = UserInputImpl()
    val storage = FileTransactionStorage()
    val services = TransactionsServicesImpl(storage)

    val financeApp = FinanceApp(userInput, services)
    financeApp.run()
}