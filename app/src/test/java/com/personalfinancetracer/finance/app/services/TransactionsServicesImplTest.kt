package com.personalfinancetracer.finance.app.services

import com.personalfinancetracer.datasource.FileTransactionStorage
import com.personalfinancetracer.models.Category
import com.personalfinancetracer.models.Transaction
import com.personalfinancetracer.models.TransactionType
import org.junit.Test


class TransactionsServicesImplTest {
 private val services = TransactionsServicesImpl(FileTransactionStorage())

 @Test
 fun `addTransactionService added Transaction case`() {
  val validTransaction =
   Transaction(amount = 100.0, category = Category.FOOD, type = TransactionType.EXPENSE)
   services.addTransactionService(
   validTransaction.amount.toString(),
   validTransaction.category,
   validTransaction.type
  )
  val savedTransaction = services.viewTransactionService(validTransaction.id.toString())
  if (savedTransaction != null) {

   assert(savedTransaction.id == validTransaction.id)
   assert(savedTransaction.amount == validTransaction.amount)
   assert(savedTransaction.category == validTransaction.category)
   assert(savedTransaction.type == validTransaction.type)
  }}



@Test
 fun `editTransactionService Valid Transaction`() {
 val validTransaction =
  Transaction(amount = 100.0, category = Category.FOOD, type = TransactionType.EXPENSE)
 services.addTransactionService(
  validTransaction.amount.toString(),
  validTransaction.category,
  validTransaction.type
 )

 var savedTransaction = services.viewTransactionService(validTransaction.id.toString())
 savedTransaction = savedTransaction?.copy(amount = 3000.0, category = Category.RENT)
 if (savedTransaction != null) {
  services.editTransactionService(
   savedTransaction?.id.toString(),
   200.0,
   category = savedTransaction.category,
   amount = savedTransaction.amount,
   transactionType = savedTransaction.type
  )
 }
 val updatedTransaction = services.viewTransactionService(validTransaction.id.toString())
 if (updatedTransaction != null)
 {
  assert(savedTransaction?.id == updatedTransaction.id)
  assert(savedTransaction?.amount == updatedTransaction.amount)
  assert(
   savedTransaction?.category == updatedTransaction.category
  )
  assert(savedTransaction?.type == updatedTransaction.type)


 }}

 @Test
 fun `editTransactionService Invalid id `(){


  val validTransaction =
   Transaction(amount = 100.0, category = Category.FOOD, type = TransactionType.EXPENSE)
  services.addTransactionService(
   validTransaction.amount.toString(),
   validTransaction.category,
   validTransaction.type
  )

  val savedTransaction = services.viewTransactionService(validTransaction.id.toString())
  if (savedTransaction != null) {
   services.editTransactionService(
    balance = 10000.0,
    id = "invalidID",
    category = savedTransaction.category,
    amount = 200.0,
    transactionType = savedTransaction.type
   )
  }
  val updatedTransaction = services.viewTransactionService(validTransaction.id.toString())
  if (updatedTransaction != null) {
   assert(updatedTransaction.amount != 200.0)
   assert(updatedTransaction.category != Category.FOOD)
   assert(updatedTransaction.type != TransactionType.EXPENSE)

  }

 }
 @Test
 fun `deleteTransactionService inValid ID Case`() {
  val validTransaction =
   Transaction(amount = 100.0, category = Category.FOOD, type = TransactionType.EXPENSE)
  services.addTransactionService(
   validTransaction.amount.toString(),
   validTransaction.category,
   validTransaction.type
  )
  services.deleteTransactionService(
  "123451234",
   balance = 0.0
  )
  val savedTransaction = services.viewTransactionService(validTransaction.id.toString())
  if (savedTransaction != null) {
   assert(savedTransaction.id == validTransaction.id)
  }

 }


 @Test
 fun `deleteTransactionService Valid Case`() {
  val validTransaction =
   Transaction(amount = 100.0, category = Category.FOOD, type = TransactionType.EXPENSE)
  services.addTransactionService(
   validTransaction.amount.toString(),
   validTransaction.category,
   validTransaction.type
  )
  services.deleteTransactionService(
   validTransaction.id.toString(),
   balance = 0.0
  )
  val savedTransaction = services.viewTransactionService(validTransaction.id.toString())
  assert(savedTransaction == null)


 }
 @Test
 fun `viewTransactionService Valid ID`() {
  val validTransaction =
   Transaction(amount = 100.0, category = Category.FOOD, type = TransactionType.EXPENSE)
  services.addTransactionService(
   validTransaction.amount.toString(),
   validTransaction.category,
   validTransaction.type
  )
  val savedTransaction = services.viewTransactionService(validTransaction.id.toString())
  if (savedTransaction != null) {
   assert(savedTransaction.id == validTransaction.id)

  }
 }

 @Test
 fun `viewTransactionService Invalid ID`() {
  val viewTransaction = services.viewTransactionService("invalidId")
  assert(viewTransaction == null)
 }

 @Test
 fun `getAllTransactionsService Valid Case`() {
  services.addTransactionService(
   amount = "200.0",
   category = Category.FOOD,
   transactionType = TransactionType.EXPENSE
  )
  services.addTransactionService(
   amount = "1000.0",
   category = Category.FOOD,
   transactionType = TransactionType.EXPENSE
  )
  val allTransactions = services.getAllTransactionsService()

assert(allTransactions.isNotEmpty())
  }
 }












