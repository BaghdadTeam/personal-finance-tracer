package com.personalfinancetracer.feature.report
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


class MonthlyCategorySummary {
    fun summary(reader: FileReader, category: String, month: String): Map<String, Int> {
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

        // idk but there should be something like deposit - withdeaw ,you may not need it
        // println("the Current for $category is $withDraw")

    }

    fun isDateInRange(inputDate : String, inputMonth:String) : Boolean {

        val month = java.time.Month.valueOf(inputMonth.uppercase(Locale.getDefault()))

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.parse(inputDate, formatter)

        return if (date in LocalDate.of(date.year, month, 1)..LocalDate.of(
                date.year,
                month,
                date.lengthOfMonth()
            )
        ) {
            true
        } else {
            false
        }


}}