package com.personalfinancetracer.utils

import com.personalfinancetracer.models.Category
import com.personalfinancetracer.models.Transaction
import com.personalfinancetracer.models.TransactionType
import org.junit.Test
import java.time.LocalDate
import java.util.Date
import java.util.UUID

class JsonUtilTest {

    val sampleTransaction = Transaction(
        id = UUID.randomUUID(),
        date = Date(),
        amount = 100.0,
        category = Category.FOOD,
        type = TransactionType.EXPENSE
    )

    @Test
    fun `serialize transaction converts the transaction to map`() {
        val res = JsonUtil.serializeTransaction(sampleTransaction)

        assert(res["id"] == sampleTransaction.id)
        assert(res["date"] == sampleTransaction.date.time)
        assert(res["amount"] == sampleTransaction.amount)
        assert(res["category"] == sampleTransaction.category.name)
        assert(res["type"] == sampleTransaction.type.name)
    }

    @Test
    fun `serialize transaction list transactions to map`() {
        val transactions = listOf(
            sampleTransaction,
            sampleTransaction.copy(id = UUID.randomUUID(), amount = 200.0)
        )
        val res = JsonUtil.serializeTransactionList(transactions)
        assert(res.startsWith("["))
        assert(res.endsWith("]"))
        assert(res.contains(sampleTransaction.id.toString()))
    }

    @Test
    fun `deserialize JSON to transaction`() {
        TODO("Implement it after implementing the Transaction class and its serializer")

    }

    @Test
    fun `deserialize JSON to transaction list`() {
        TODO("Implement it after implementing the Transaction class and its serializer")

    }
}