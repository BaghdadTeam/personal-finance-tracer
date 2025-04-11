package com.personalfinancetracer.finance.app.services

import com.personalfinancetracer.datasource.FileTransactionStorage

import com.personalfinancetracer.models.Category
import com.personalfinancetracer.models.Transaction
import com.personalfinancetracer.models.TransactionType
import java.util.UUID

class TransactionsServicesImpl(private val storage: FileTransactionStorage): TransactionsServices {


    private val allTransactions = storage.getAllTransactions()

    /**
     * Adds a new transaction.
     */
    override fun addTransactionService(
        amount: String,
        category: Category,
        transactionType: TransactionType
    ) {


        val newTransaction =
            Transaction(category = category, amount = amount.toDouble(), type = transactionType)
        println("${newTransaction.category} , ${newTransaction.amount} , ${newTransaction.type}")
        if (storage.saveTransaction(newTransaction))
            println("New Transaction Added : Details $amount , $category , $transactionType")


    }


    /**
     * Edits a transaction by its ID.
     */

    override fun editTransactionService(
        id: String,
        balance: Double,
        amount: Double,
        category: Category,
        transactionType: TransactionType
    ): Double {
        val updatedBalance: Double
        val transaction = allTransactions.find { it.id.toString() == id }
        if (transaction != null) {

            if (amount > balance && transactionType == TransactionType.EXPENSE) println("Insufficient Balance")
            else {
                val updatedTransaction =
                    Transaction(
                        id = UUID.fromString(id),
                        category = category,
                        amount = amount,
                        type = transactionType


                    )


                storage.editTransaction(id, updatedTransaction)
                if (transaction.type == TransactionType.EXPENSE) {
                    updatedBalance = balance - amount
                    return updatedBalance
                } else {
                    updatedBalance = balance + amount
                    return updatedBalance
                }

            }

        } else
            println("Transaction Not Found")
        return balance

    }

    /**
     * Views a transaction by its ID.
     */
    override fun viewTransactionService(id: String):Transaction? {

        val transaction = allTransactions.find { it.id.toString() == id }
        return transaction
    }


    /**
     * Deletes a transaction by its ID.
     */
    override fun deleteTransactionService(id: String, balance: Double): Double {
        var updatedBalance = balance

        val transaction = allTransactions.find { it.id.toString() == id }
        if (transaction != null) {
            storage.deleteTransaction(id)
            println("Transaction Deleted Successfully")

            // update balance

            if (transaction.type == TransactionType.INCOME)
                updatedBalance -= transaction.amount
            else updatedBalance += transaction.amount
            return updatedBalance
        } else {
            println("Transaction Not Found")
            return balance
        }


    }


    /**
     * Gets all transactions.
     */

    override fun getAllTransactionsService() {
        if (allTransactions.isEmpty()) println("No Transactions Found")
        else {
            println("${allTransactions.size} Transactions Found")
            allTransactions.forEachIndexed { index, transaction ->
                println(
                    "Transaction Details : ${index + 1}  - ID ${transaction.id} |" +
                            " Amount ${transaction.amount} | Type ${transaction.type} |" +
                            " Category ${transaction.category} | ${transaction.date}"
                )
            }

        }
    }

}
