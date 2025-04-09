package com.personalfinancetracer.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

fun isDateInRange(inputDate : String, inputMonth:String) : Boolean {

    val month = java.time.Month.valueOf(inputMonth.uppercase(Locale.getDefault()))

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date = LocalDate.parse(inputDate, formatter)

    return if (date in LocalDate.of(date.year, month, 1)..LocalDate.of(date.year, month, date.lengthOfMonth()))
    {
        true
    } else {
        false
    }
}
