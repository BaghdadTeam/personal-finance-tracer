package com.personalfinancetracer.utils

import com.personalfinancetracer.models.Category
import com.personalfinancetracer.models.Transaction
import com.personalfinancetracer.models.TransactionType
import java.util.Date
import java.util.UUID

object JsonUtil {

    /**
     * Serializes a transaction object into a Map<String, Any>.
     *
     * @param transaction The transaction object to serialize.
     * @return A Map<String, Any> representation of the transaction.
     */
    fun serializeTransaction(transaction: Transaction): Map<String, Any> {
        return mapOf(
            "id" to transaction.id,
            "date" to transaction.date.time,
            "category" to transaction.category.name,
            "type" to transaction.type.name,
            "amount" to transaction.amount
        )
    }

    /**
     * Deserializes a Map<String, Any> into a transaction object.
     *
     * @param transactionMap The Map<String, Any> to deserialize.
     * @return The deserialized transaction object.
     */
    fun deserializeTransaction(transactionMap: Map<String, String>): Transaction {
        val id = UUID.fromString(transactionMap["id"] as String)
        val category = Category.valueOf(transactionMap["category"] as String)
        val type = TransactionType.valueOf(transactionMap["type"] as String)
        val amount = (transactionMap["amount"] as String).toDouble()

        val date = transactionMap["date"]!!.toLong()
        return Transaction(id, Date(date), category, type, amount)
    }

    /**
     * Serializes a list of transaction objects into a JSON-like string.
     *
     * @param transactions The list of transaction objects to serialize.
     * @return A JSON-like string representation of the list of transactions.
     */
    fun serializeTransactionList(transactions: List<Transaction>): String {
        val jsonArray = transactions.joinToString(prefix = "[", postfix = "]") { transaction ->
            serializeTransaction(transaction).toString()
        }
        return jsonArray
    }

    /**
     * Deserializes a JSON-like string into a list of transaction objects.
     *
     * @param jsonString The JSON-like string to deserialize.
     * @return A list of deserialized transaction objects.
     */
    fun deserializeTransactionList(jsonString: String): List<Transaction> {
        val objects = jsonString.removePrefix("[").removeSuffix("]")
        if (objects.isEmpty()) return emptyList()
        val jsonArray = jsonString.removePrefix("{").removeSuffix("}").split("}, {")
        if (jsonArray.isEmpty()) return emptyList()
        return jsonArray.map { json ->
            val jsonObject = json.split(",").associate {
                val (key, value) = it.split("=")
                key.trim() to value
            }
            deserializeTransaction(jsonObject)
        }
    }
}
