package com.personalfinancetracer.feature.report

import categorySummary

class CategorySummary{
     fun summary(reader : fileReader , category : String) : Map<String , Int>{
        // we may have to make it in a diffrent class
        val categoryRows = mutableListOf<Map<String,Any>>()

        var deposit = 0
        var withDraw = 0

        for(row in reader){
            if (row.category == category){
                if (row.type == "Deposit"){
                    deposit += row.Amount
                }else{

                    withDraw += row.Amount
                }
            }
        }

        return mapOf("Deposit" to deposit , "WithDraw" to withDraw)

        // idk but there should be something like deposit - withdeaw ,you may not need it
        // println("the Current for $category is $withDraw")


    }
}