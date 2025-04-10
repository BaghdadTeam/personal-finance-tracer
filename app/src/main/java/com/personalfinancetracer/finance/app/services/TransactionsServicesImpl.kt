package com.personalfinancetracer.finance.app.services

import com.personalfinancetracer.datasource.FileTransactionStorage
import com.personalfinancetracer.finance.app.FinanceApp
import com.personalfinancetracer.finance.app.TransactionsServices
import com.personalfinancetracer.finance.app.cli.UserInputImpl
import com.personalfinancetracer.models.Category
import com.personalfinancetracer.models.Transaction
import com.personalfinancetracer.models.TransactionType
import java.util.UUID


class TransactionsServicesImpl(): TransactionsServices {

    override fun addTransactionService(
        amount: String,
        category: Category,
        transactionType: TransactionType
    ) {


        val newTransaction =
            Transaction(category = category, amount = amount.toDouble(), type = transactionType)
        println("${newTransaction.category} , ${newTransaction.amount} , ${newTransaction.type}")
        if (FileTransactionStorage().saveTransaction(newTransaction))
            println("New Transaction Added : Details $amount , $category , $transactionType")


    }


    override fun editTransactionService(ID: String) {
        val transactions = FileTransactionStorage().getAllTransactions()
        val transaction = transactions.find { it.id.toString() == ID }
        if (transaction != null) {
            val amount = UserInputImpl().readAmount()

            val transactionType = UserInputImpl().choiceTransactionType()
            val category = UserInputImpl().choiceCategory()
            if (amount > FinanceApp().balance && transactionType == TransactionType.EXPENSE) println("Insufficient Balance")
            else {
                val updatedTransaction =
                    Transaction(
                        id = UUID.fromString(ID),
                        category = category,
                        amount = amount,
                        type = transactionType
                    )

                FileTransactionStorage().editTransaction(ID, updatedTransaction)
                if (transaction.type == TransactionType.EXPENSE) FinanceApp().balance -= amount
                else FinanceApp().balance += transaction.amount

            }

        } else println("Transaction Not Found")

    }



    override fun viewTransactionService(ID: String) {

        val transactions = FileTransactionStorage().getAllTransactions()
        val transaction = transactions.find { it.id.toString() == ID }
        if (transaction != null) {
            println("Transaction Details : Amount ${transaction.amount} , Type ${transaction.type} , Category ${transaction.category}")
        } else println("Transaction Not Found")
    }



    override fun deleteTransactionService(ID: String) {
        val transactions = FileTransactionStorage().getAllTransactions()
        val transaction = transactions.find { it.id.toString() == ID }
        if (transaction != null) {
            FileTransactionStorage().deleteTransaction(ID)
            println("Transaction Deleted Successfully")

            // update balance
            if (transaction.type == TransactionType.EXPENSE)
                FinanceApp().balance -= transaction.amount
            else FinanceApp().balance += transaction.amount
        } else {
            println("Transaction Not Found")
        }

    }


    override fun getAllTransactionsService() {
        val transactions = FileTransactionStorage().getAllTransactions()

        if (transactions.isEmpty()) println("No Transactions Found")
        else {
            println("${transactions.size} Transactions Found")
            transactions.forEachIndexed { index, transaction ->
                println("Transaction Details : ${index + 1}  - ID ${transaction.id} |" +
                        " Amount ${transaction.amount} | Type ${transaction.type} |" +
                        " Category ${transaction.category} | ${transaction.date}")
            }

        }
    }

}
