package com.personalfinancetracer.datasource

import com.personalfinancetracer.models.Category
import com.personalfinancetracer.models.Transaction
import com.personalfinancetracer.models.TransactionType
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.File
import java.util.Date
import java.util.UUID

class FileTransactionStorageTest {

    private lateinit var tempFile: File
    private lateinit var storage: FileTransactionStorage
    private lateinit var transaction: Transaction

    @Before
    fun setup() {
        tempFile = File.createTempFile("test_transactions", ".txt")
        storage = FileTransactionStorage(tempFile)
        transaction = Transaction(
            UUID.randomUUID(),
            Date(),
            Category.FOOD,
            TransactionType.EXPENSE,
            49.99
        )
    }

    @After
    fun cleanup() {
        tempFile.delete()
    }

    @Test
    fun `saveTransaction saves a transaction to the file`() {
        val result = storage.saveTransaction(transaction)
        assert(result)
        val savedTransactions = storage.getAllTransactions()
        assert(savedTransactions.isNotEmpty())
    }
    
}