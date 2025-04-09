package com.personalfinancetracer.utils

import org.json.JSONArray
import org.json.JSONObject

object JsonUtil {

    fun <T>serializeTransaction(transaction: T): String{
        return JSONObject().apply {
            // Assuming T has a method to convert itself to a JSONObject
            // This is a placeholder, you need to implement the actual serialization logic
            // put("transaction", transaction.toJson())
        }.toString()
    }

    fun <T>deserializeTransaction(jsonString: String): T {
        val obj = JSONObject(jsonString)
        TODO("Change the return type to Transaction and implement the deserialization logic")
        return obj as T
    }

    fun <T>serializeTransactionList(transactions: List<T>): String {
        val jsonArray = JSONArray()
        transactions.forEach { transaction ->
            jsonArray.put(JSONObject(serializeTransaction(transaction)))
        }
        return jsonArray.toString()
    }

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