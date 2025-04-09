package com.personalfinancetracer.feature.report

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


class MonthlyCategorySummary{
    fun summary(reader : fileReader , category : String , month : String) : Map<String , Int>{
        // we may have to make it in a diffrent class
        val categoryRows = mutableListOf<Map<String,Any>>()

        var deposit = 0
        var withDraw = 0

        for(row in reader){
            if (isDateInRange(row.Date , month)){
                if (row.category == category){
                    if (row.type == "Deposit"){
                        deposit += row.Amount
                    }else{

                        withDraw += row.Amount
                    }
                }
            }
        }

        return mapOf("Deposit" to deposit , "WithDraw" to withDraw)

        // idk but there should be something like deposit - withdeaw ,you may not need it
        // println("the Current for $category is $withDraw")


    }

    fun isDateInRange(date : String, month:String) : Boolean{

        val month = java.time.Month.valueOf(month.uppercase(Locale.getDefault()))

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.parse(date, formatter)

        return if (date in LocalDate.of(date.year, month, 1) .. LocalDate.of(date.year,month,31)){
             true
        }else{
             false
        }

    }
}