package com.personalfinancetracer.utils

import com.personalfinancetracer.models.Category
import com.personalfinancetracer.models.Transaction
import com.personalfinancetracer.models.TransactionType
import org.json.JSONArray
import org.json.JSONObject
import java.util.Date
import java.util.UUID

object JsonUtil {

    /**
     * Serializes a transaction object into a JSON string.
     *
     * @param transaction The transaction object to serialize.
     * @return A JSON string representation of the transaction.
     */
    fun serializeTransaction(transaction: Transaction): String {
        return JSONObject().apply {
            put("id", transaction)
            put("date", transaction.date.time)
            put("category", transaction.category.name)
            put("type", transaction.type.name)
            put("amount", transaction.amount)
        }.toString()
    }

    /**
     * Deserializes a JSON string into a transaction object.
     *
     * @param jsonString The JSON string to deserialize.
     * @return The deserialized transaction object.
     * @throws NotImplementedError This method needs to be implemented.
     */
    fun deserializeTransaction(jsonString: String): Transaction {
        val obj = JSONObject(jsonString)
        return Transaction(
            id = UUID.fromString(obj.getString("id")),
            date = Date(obj.getString("date")),
            category = Category.valueOf(obj.getString("category")),
            type = TransactionType.valueOf(obj.getString("type")),
            amount = obj.getDouble("amount")
        )
    }

    /**
     * Serializes a list of transaction objects into a JSON string.
     *
     * @param transactions The list of transaction objects to serialize.
     * @return A JSON string representation of the list of transactions.
     */
    fun serializeTransactionList(transactions: List<Transaction>): String {
        val jsonArray = JSONArray()
        transactions.forEach { transaction ->
            jsonArray.put(JSONObject(serializeTransaction(transaction)))
        }
        return jsonArray.toString()
    }

    /**
     * Deserializes a JSON string into a list of transaction objects.
     *
     * @param jsonString The JSON string to deserialize.
     * @return A list of deserialized transaction objects.
     * @throws NotImplementedError This method needs to be implemented.
     */
    fun deserializeTransactionList(jsonString: String): List<Transaction> {
        val jsonArray = JSONArray(jsonString)
        val transactions = mutableListOf<Transaction>()
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            transactions.add(deserializeTransaction(jsonObject.toString()))
        }
        return transactions
    }
}