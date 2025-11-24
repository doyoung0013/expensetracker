package com.doyoung.expensetracker.data.model

data class CategoryStat(
    val category: String,
    val totalAmount: Long
)

data class Statistics(
    val totalIncome: Long,
    val totalExpense: Long,
    val categoryStats: List<CategoryStat>
)
