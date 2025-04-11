package com.personalfinancetracer.summaryReport

import com.personalfinancetracer.datasource.FileTransactionStorage
import com.personalfinancetracer.finance.app.services.TransactionSummaryServices
import com.personalfinancetracer.models.Category
import com.personalfinancetracer.models.Transaction
import com.personalfinancetracer.models.TransactionType
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.File
import java.util.Date

class TransactionSummaryTest {

    private lateinit var tempFile: File
    private lateinit var storage: FileTransactionStorage
    private lateinit var transactionSummary: TransactionSummaryServices
    private lateinit var transaction: Transaction
    private val testFileName = "test_transactions.txt"

    @Before
    fun setup() {
        tempFile = File.createTempFile("test_transactions", ".txt")
        storage = FileTransactionStorage(tempFile)
        transactionSummary = TransactionSummaryServices(storage) // Inject the mock

    }



    @After
    fun cleanup() {
        tempFile.delete()
    }

    @Test
    fun `getBalanceReport emptyTransactions`() {
        // Test the scenario where the list of transactions is empty.
        //Expected outcome: Income, Expense, and Balance should all be 0.0.
        // TODO implement test
        val balanceReport = transactionSummary.getBalanceReport()

        assertEquals(0.0, balanceReport["Income"]!!, 0.0)
        assertEquals(0.0, balanceReport["Balance"]!!, 0.0)
        assertEquals(0.0, balanceReport["Expense"]!!, 0.0)


    }

    @Test
    fun `getBalanceReport onlyIncome`() {
        transaction = Transaction(
            date = Date(),
            amount = 100.0,
            category = Category.SALARY,
            type = TransactionType.INCOME)
        storage.saveTransaction(transaction)
        val balanceReport = transactionSummary.getBalanceReport()

        assertEquals(100.0, balanceReport["Income"]!!, 0.001)
        assertEquals(100.0, balanceReport["Balance"]!!, 0.001)
        assertEquals(0.0, balanceReport["Expense"]!!, 0.0)

    }



    @Test
    fun `getBalanceReport onlyExpense`() {
        transaction = Transaction(
            date = Date(),
            amount = 100.0,
            category = Category.SALARY,
            type = TransactionType.EXPENSE)
        storage.saveTransaction(transaction)
        val balanceReport = transactionSummary.getBalanceReport()

        assertEquals(0.0, balanceReport["Income"]!!, 0.0)
        assertEquals(-100.0, balanceReport["Balance"]!!, 0.0)
        assertEquals(100.0, balanceReport["Expense"]!!, 0.0)
    }

    @Test
    fun `getBalanceReport mixedTransactions`() {
        transaction = Transaction(
            date = Date(),
            amount = 1000.0,
            category = Category.SALARY,
            type = TransactionType.INCOME)
        storage.saveTransaction(transaction)

        transaction = Transaction(
            date = Date(),
            amount = 100.0,
            category = Category.SALARY,
            type = TransactionType.EXPENSE)
        storage.saveTransaction(transaction)

        val balanceReport = transactionSummary.getBalanceReport()

        assertEquals(1000.0, balanceReport["Income"]!!, 0.001)
        assertEquals(900.0, balanceReport["Balance"]!!, 0.001)
        assertEquals(100.0, balanceReport["Expense"]!!, 0.001)
    }

    @Test
    fun `getBalanceReport largeAmounts`() {
        transaction = Transaction(
            date = Date(),
            amount = 1_000_000.0,
            category = Category.SALARY,
            type = TransactionType.INCOME)
        storage.saveTransaction(transaction)

        transaction = Transaction(
            date = Date(),
            amount = 100_000.0,
            category = Category.SALARY,
            type = TransactionType.EXPENSE)
        storage.saveTransaction(transaction)

        val balanceReport = transactionSummary.getBalanceReport()

        assertEquals(1_000_000.0, balanceReport["Income"]!!, 0.001)
        assertEquals(900_000.0, balanceReport["Balance"]!!, 0.001)
        assertEquals(100_000.0, balanceReport["Expense"]!!, 0.001)
    }


    @Test
    fun `getMonthlySummary emptyTransactions`() {
        val balanceReport = transactionSummary.getMonthlySummary("April")

        assertEquals(0.0, balanceReport["Income"]!!, 0.0)
        assertEquals(0.0, balanceReport["Balance"]!!, 0.0)
        assertEquals(0.0, balanceReport["Expense"]!!, 0.0)
    }

    @Test
    fun `getMonthlySummary validMonth`() {
        transaction = Transaction(
            date = Date(),
            amount = 1000.0,
            category = Category.SALARY,
            type = TransactionType.INCOME)
        storage.saveTransaction(transaction)


        val balanceReport = transactionSummary.getMonthlySummary("April")

        assertEquals(1000.0, balanceReport["Income"]!!, 0.001)
        assertEquals(1000.0, balanceReport["Balance"]!!, 0.001)
        assertEquals(0.0, balanceReport["Expense"]!!, 0.001)
    }

    @Test
    fun `getMonthlySummary noTransactionsForMonth`() {
        transaction = Transaction(
            date = Date(),
            amount = 1000.0,
            category = Category.SALARY,
            type = TransactionType.INCOME)
        storage.saveTransaction(transaction)


        val balanceReport = transactionSummary.getMonthlySummary("January")

        assertEquals(0.0, balanceReport["Income"]!!, 0.001)
        assertEquals(0.0, balanceReport["Balance"]!!, 0.001)
        assertEquals(0.0, balanceReport["Expense"]!!, 0.001)
    }

    @Test
    fun `getMonthlySummary onlyExpenseForMonth`() {
        transaction = Transaction(
            date = Date(),
            amount = 1000.0,
            category = Category.SALARY,
            type = TransactionType.EXPENSE)
        storage.saveTransaction(transaction)


        val balanceReport = transactionSummary.getMonthlySummary("April")

        assertEquals(0.0, balanceReport["Income"]!!, 0.001)
        assertEquals(-1000.0, balanceReport["Balance"]!!, 0.001)
        assertEquals(1000.0, balanceReport["Expense"]!!, 0.001)
    }

    @Test
    fun `getMonthlySummary mixedTransactionsForMonth`() {
        transaction = Transaction(
            date = Date(),
            amount = 1000.0,
            category = Category.SALARY,
            type = TransactionType.INCOME)
        storage.saveTransaction(transaction)
        transaction = Transaction(
            date = Date(),
            amount = 1000.0,
            category = Category.SALARY,
            type = TransactionType.EXPENSE)
        storage.saveTransaction(transaction)

        val balanceReport = transactionSummary.getMonthlySummary("April")

        assertEquals(1000.0, balanceReport["Income"]!!, 0.001)
        assertEquals(0.0, balanceReport["Balance"]!!, 0.001)
        assertEquals(1000.0, balanceReport["Expense"]!!, 0.001)
    }

    @Test
    fun `getMonthlySummary invalidMonthFormat`() {
        val balanceReport = transactionSummary.getMonthlySummary("ashfklasdfh")

        assertEquals(0.0, balanceReport["Income"]!!, 0.001)
        assertEquals(0.0, balanceReport["Balance"]!!, 0.001)
        assertEquals(0.0, balanceReport["Expense"]!!, 0.001)

    }

    @Test
    fun `getMonthlySummary largeAmountsForMonth`() {
        transaction = Transaction(
            date = Date(),
            amount = 1_000_000.0,
            category = Category.SALARY,
            type = TransactionType.INCOME)
        storage.saveTransaction(transaction)

        transaction = Transaction(
            date = Date(),
            amount = 900_000.0,
            category = Category.SALARY,
            type = TransactionType.EXPENSE)
        storage.saveTransaction(transaction)


        val balanceReport = transactionSummary.getMonthlySummary("April")

        assertEquals(1_000_000.0, balanceReport["Income"]!!, 0.001)
        assertEquals(100_000.0, balanceReport["Balance"]!!, 0.001)
        assertEquals(900_000.0, balanceReport["Expense"]!!, 0.001)
    }


}