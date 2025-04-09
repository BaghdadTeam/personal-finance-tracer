package com.personalfinancetracer.feature.summary

import com.personalfinancetracer.feature.report.FileReader


class CategorySummary() {
    fun summary(reader: FileReader, category: String): Map<String, Int> {
        // we may have to make it in a diffrent class

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

        // idk but there should be something like deposit - withdeaw ,you may not need it
        // println("the Current for $category is $withDraw")


    }


}