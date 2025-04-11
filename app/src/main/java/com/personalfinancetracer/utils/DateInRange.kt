package com.personalfinancetracer.utils

import java.time.YearMonth
import java.time.ZoneId
import java.util.Date
import java.util.Locale

fun isDateInRange(inputDate : Date, inputMonth:String) : Boolean {
    try {
    val month = java.time.Month.valueOf(inputMonth.uppercase(Locale.getDefault()))

    val localDate = inputDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

    val yearMonth = YearMonth.of(localDate.year, month)
    val initialDate = yearMonth.atDay(1)
    val finalDate = yearMonth.atEndOfMonth()

    return localDate in initialDate..finalDate
        }catch (e: Exception) {
            println("Invalid Month Name")
            return false
        }
}
