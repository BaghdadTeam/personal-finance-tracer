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
        val transactionMap = mapOf(
            "id" to sampleTransaction.id.toString(),
            "date" to sampleTransaction.date.time.toString(),
            "category" to sampleTransaction.category.name,
            "type" to sampleTransaction.type.name,
            "amount" to sampleTransaction.amount.toString()
        )

        val res = JsonUtil.deserializeTransaction(transactionMap)

        assert(res.id == sampleTransaction.id)
        assert(res.date == sampleTransaction.date)
        assert(res.category == sampleTransaction.category)
        assert(res.type == sampleTransaction.type)
        assert(res.amount == sampleTransaction.amount)
    }

    @Test
    fun `deserialize JSON to transaction list`() {
        val listOfTransactions = listOf(
            sampleTransaction,
            sampleTransaction.copy(id = UUID.randomUUID(), amount = 200.0)
        )
        val jsonString = JsonUtil.serializeTransactionList(listOfTransactions)
        val res = JsonUtil.deserializeTransactionList(jsonString)

        assert(res.size == listOfTransactions.size)
        assert(res[0].id == listOfTransactions[0].id)
        assert(res[0].date == listOfTransactions[0].date)
        assert(res[1].category == listOfTransactions[1].category)
        assert(res[1].type == listOfTransactions[1].type)
        assert(res[1].amount == listOfTransactions[1].amount)
    }

    @Test
    fun `serializing an empty list` () {
        val res = JsonUtil.serializeTransactionList(emptyList<Transaction>())
        assert(res == "[]")
    }

    @Test
    fun `deserializing an empty file` () {
        val res = JsonUtil.deserializeTransactionList("[]")
        println("asdasd $res")
        assert(res.isEmpty())
    }
}