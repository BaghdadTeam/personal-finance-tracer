package com.personalfinancetracer.feature.summary

import com.personalfinancetracer.utils.isDateInRange
import com.personalfinancetracer.feature.report.FileReader

class FullSummary(override var reader: FileReader) : Summary{
    override fun getCategorySummary(category: String): Map<String, Int> {
        var deposit = 0
        var withDraw = 0

        for (row in reader.readFile()) {
            if (row["Category"] == category) {
                if (row["type"] == "Deposit") {
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
                if (row["type"].toString().lowercase() == "Deposit".lowercase()) {
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
        // we may have to make it in a different class

        var deposit = 0
        var withDraw = 0

        for (row in reader.readFile()) {
            if (isDateInRange(row["Date"].toString(), month)) {
                if (row["Category"].toString().lowercase() == category.lowercase()) {
                    if (row["type"].toString().lowercase() == "Deposit".lowercase()) {
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