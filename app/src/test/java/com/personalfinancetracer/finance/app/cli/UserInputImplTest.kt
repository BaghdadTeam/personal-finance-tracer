package com.personalfinancetracer.finance.app.cli

import org.junit.Test


import java.io.ByteArrayInputStream
import java.io.InputStream


class UserInputImplTest {


    @Test
    fun `readAmount valid positive input`() {
        provideInput("100.5\n")
        val input = UserInputImpl().readAmount()
        assert(input == 100.5)
    }

    @Test
    fun `readAmount invalid amount input`() {
        provideInput("-50.0\n")
        val input = UserInputImpl().readAmount()
        assert(input == null)
    }

    @Test
    fun `readAmount null input`() {
        provideInput("\n")
        val input = UserInputImpl().readAmount()
        assert(input == null)
    }

    @Test
    fun `readAmount invalid non numeric input`() {
        provideInput("abc\n")
        val input = UserInputImpl().readAmount()
        assert(input == null)
    }


    @Test
    fun `choiceCategory valid input`() {
        provideInput("1\n")
        val input = UserInputImpl().choiceCategory()
        assert(input != null)
    }


    @Test
    fun `choiceCategory invalid non integer input`() {
        provideInput("abc\n")
        val input = UserInputImpl().choiceCategory()
        assert(input == null)
    }

    @Test
    fun `choiceCategory input out of range  too low `() {
        provideInput("-1\n")
        val input = UserInputImpl().choiceCategory()
        assert(input == null)
    }

    @Test
    fun `choiceCategory input out of range  too high `() {
        provideInput("100\n")
        val input = UserInputImpl().choiceCategory()
        assert(input == null)
    }

    @Test
    fun `choiceTransactionType valid input`() {
        provideInput("1\n")
        val input = UserInputImpl().choiceTransactionType()
        assert(input != null)
    }


    @Test
    fun `choiceTransactionType null input`() {
        provideInput("\n")
        val input = UserInputImpl().choiceTransactionType()
        assert(input == null)
    }

    @Test
    fun `choiceTransactionType invalid non integer input`() {
        provideInput("abc\n")
        val input = UserInputImpl().choiceTransactionType()
        assert(input == null)
    }

    @Test
    fun `choiceTransactionType input out of range  too low `() {
        provideInput("-5\n")
        val input = UserInputImpl().choiceTransactionType()
        assert(input == null)
    }

    @Test
    fun `choiceTransactionType input out of range  too high `() {
        provideInput("999\n")
        val input = UserInputImpl().choiceTransactionType()
        assert(input == null)
    }

    @Test
    fun `readID valid input`() {
        provideInput("TXN123\n")
        val input = UserInputImpl().readID()
        assert(input == "TXN123")
    }

    @Test
    fun `readID empty input`() {
        provideInput("\n")
        val input = UserInputImpl().readID()
        assert(input == "")
    }


    @Test
    fun `readID special characters input`() {
        provideInput("!@#\$%^&*()\n")
        val input = UserInputImpl().readID()
        assert(input == "!@#\$%^&*()")
    }

    @Test
    fun `readID numeric input`() {
        provideInput("12345\n")
        val input = UserInputImpl().readID()
        assert(input == "12345")
    }

    private fun provideInput(input: String) {
        val testIn: InputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(testIn)
    }

}
