package com.personalfinancetracer.feature.summary

import com.personalfinancetracer.utils.isDateInRange
import com.personalfinancetracer.feature.report.FileReader

//idk if i should make reader : FileReader as a var or give it in constructor

class FullSummary(override var reader: FileReader) : Summary{

    override fun getCategorySummary(category: String): Map<String, Int> {
        var deposit = 0
        var withDraw = 0

        for (row in reader.readFile()) {
            if (row["Category"].toString().lowercase() == category.lowercase()) {
                if (row["Type"].toString().lowercase() == "Deposit".lowercase()) {
                    deposit += row["Amount"].toString().toInt()
                } else {

                    withDraw += row["Amount"].toString().toInt()
                }
            }
        }

        return mapOf("Deposit" to deposit, "WithDraw" to withDraw)

    }

    override fun getMonthlySummary(month: String): Map<String, Int> {
        var deposit = 0
        var withDraw = 0

        for (row in reader.readFile()) {
            if (isDateInRange(row["Date"].toString(), month)) {
                if (row["Type"].toString().lowercase() == "Deposit".lowercase()) {
                    deposit += row["Amount"].toString().toInt()
                } else {
                    withDraw += row["Amount"].toString().toInt()
                }
            }
        }

        return mapOf("Deposit" to deposit, "WithDraw" to withDraw)
    }

    override fun getCategoryMonthlySummary(
        category: String,
        month: String
    ): Map<String, Int> {

        var deposit = 0
        var withDraw = 0

        for (row in reader.readFile()) {
            if (isDateInRange(row["Date"].toString(), month)) {
                if (row["Category"].toString().lowercase() == category.lowercase()) {
                    if (row["Type"].toString().lowercase() == "Deposit".lowercase()) {
                        deposit += row["Amount"].toString().toInt()
                    } else {

                        withDraw += row["Amount"].toString().toInt()
                    }
                }
            }
        }

        return mapOf("Deposit" to deposit, "WithDraw" to withDraw)

    }

}