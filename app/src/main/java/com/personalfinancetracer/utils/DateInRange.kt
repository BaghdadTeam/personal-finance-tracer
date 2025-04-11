package com.personalfinancetracer.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

fun isDateInRange(inputDate : Date, inputMonth:String) : Boolean {

    val month = java.time.Month.valueOf(inputMonth.uppercase(Locale.getDefault()))

    val formatter = DateTimeFormatter.ofPattern("yyyy[-M][-d]")
    val date = LocalDate.parse("${inputDate.year}-${inputDate.month}-${inputDate.date}", formatter)

    val initialDate = LocalDate.of(date.year, month, 1)
    val finalDate = LocalDate.of(date.year, month, initialDate.lengthOfMonth())

    return if (date in initialDate .. finalDate)
        {
            true
        } else {
            false
        }
}