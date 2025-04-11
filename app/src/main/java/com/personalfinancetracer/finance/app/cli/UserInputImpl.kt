package com.personalfinancetracer.finance.app.cli

import com.personalfinancetracer.models.Category
import com.personalfinancetracer.models.TransactionType
import kotlin.system.exitProcess

class UserInputImpl : UserInput {

    override fun readAmount(): Double? {
        println("Enter the amount of the transaction:")
        println("0 - Exit")
        val userChoice = readln().toDoubleOrNull()
        if (userChoice == 0.0) exitProcess(0)
        return userChoice
    }



    override fun choiceCategory(): Category? {
        println("Please Choice Category : ")
        for (category in Category.entries) {
            println("${category.ordinal + 1} - ${category.name}")
        }

        println("0 - Quit")

        val userChoice = readln().toIntOrNull()
        if (userChoice == 0) exitProcess(0)

        // check if user choice is valid if not recall the function
        if (userChoice == null || userChoice - 1 !in Category.entries.indices) return null
        return Category.entries[userChoice - 1]
    }



    override fun choiceTransactionType(): TransactionType? {
        println("Please Choice Transaction Type : ")
        for (transactionType in TransactionType.entries) {
            println("${transactionType.ordinal + 1} - ${transactionType.name}")
        }
        println("0 - Quit")
        val userChoice = readln().toIntOrNull()
        if (userChoice == 0) exitProcess(0)

        // check if user choice is valid if not recall the function
        if (userChoice == null || userChoice - 1 !in TransactionType.entries.indices) return null
        return TransactionType.entries[userChoice - 1]
    }

    override fun readID(): String {
        println("Please Enter the ID of the transaction:")
        println("0 - Exit")
        val userInput = readln()
        if (userInput == "0") exitProcess(0)
        return userInput

    }

//    override fun readMonth(): String {
//        println("Please Enter the Month of the transaction:")
//        println("0 - Exit")
//        val userInput = readln()
//        if (userInput == "0") exitProcess(0)
//        return userInput
//    }


}