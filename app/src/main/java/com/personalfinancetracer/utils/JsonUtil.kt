package com.personalfinancetracer.utils

import org.json.JSONArray
import org.json.JSONObject

object JsonUtil {

    /**
     * Serializes a transaction object into a JSON string.
     *
     * @param T The type of the transaction object.
     * @param transaction The transaction object to serialize.
     * @return A JSON string representation of the transaction.
     */
    fun <T>serializeTransaction(transaction: T): String {
        return JSONObject().apply {
            // Assuming T has a method to convert itself to a JSONObject
            // This is a placeholder, you need to implement the actual serialization logic
            // put("transaction", transaction.toJson())
        }.toString()
    }

    /**
     * Deserializes a JSON string into a transaction object.
     *
     * @param T The type of the transaction object.
     * @param jsonString The JSON string to deserialize.
     * @return The deserialized transaction object.
     * @throws NotImplementedError This method needs to be implemented.
     */
    fun <T>deserializeTransaction(jsonString: String): T {
        val obj = JSONObject(jsonString)
        TODO("Change the return type to Transaction and implement the deserialization logic")
        return obj as T
    }

    /**
     * Serializes a list of transaction objects into a JSON string.
     *
     * @param T The type of the transaction objects.
     * @param transactions The list of transaction objects to serialize.
     * @return A JSON string representation of the list of transactions.
     */
    fun <T>serializeTransactionList(transactions: List<T>): String {
        val jsonArray = JSONArray()
        transactions.forEach { transaction ->
            jsonArray.put(JSONObject(serializeTransaction(transaction)))
        }
        return jsonArray.toString()
    }

    /**
     * Deserializes a JSON string into a list of transaction objects.
     *
     * @param T The type of the transaction objects.
     * @param jsonString The JSON string to deserialize.
     * @return A list of deserialized transaction objects.
     * @throws NotImplementedError This method needs to be implemented.
     */
    fun <T>deserializeTransactionList(jsonString: String): List<T> {
        val jsonArray = JSONArray(jsonString)
        val transactions = mutableListOf<T>()
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            transactions.add(deserializeTransaction(jsonObject.toString()))
        }
        return transactions
    }
}