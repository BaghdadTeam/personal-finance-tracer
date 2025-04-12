package com.personalfinancetracer.finance.app.cli

import com.personalfinancetracer.models.Category
import com.personalfinancetracer.models.TransactionType

interface UserInput {
    fun readAmount():Double?
    fun choiceCategory(): Category?
    fun choiceTransactionType(): TransactionType?
    fun readID():String
    fun readMonth():String


}