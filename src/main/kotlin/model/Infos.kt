package model

data class Account(val name: String, val transactions: MutableList<Transaction> = mutableListOf())


data class Transaction(val date: Double,
                       val description: String,
                       val value: Double,
                       val category: String)