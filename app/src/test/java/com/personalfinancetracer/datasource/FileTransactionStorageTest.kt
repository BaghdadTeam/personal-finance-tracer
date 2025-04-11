package com.personalfinancetracer.datasource

//class FileTransactionStorageTest {
//
//    private val testFileName = "test_transactions.txt"
//
//    @Before
//    fun setup() {
//        File(testFileName).delete()
//    }
//
//    @After
//    fun cleanup() {
//        File(testFileName).delete()
//    }
//
//    @Test
//    fun `test save transactions to file`() {
//
//        val file = File(testFileName)
//        assert(file.exists())
//        assert(file.readText().contains("Groceries"))
//        assert(file.readText().contains("Utilities"))
//
//        TODO("Implement the file deserialization functionality to verify the contents")
//    }
//
//    @Test
//    fun `load from non-existent file returns empty list`() {
//        val file = File(testFileName)
//        assert(!file.exists())
//    }
//}