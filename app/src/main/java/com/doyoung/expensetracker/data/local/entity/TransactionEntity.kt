package com.doyoung.expensetracker.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val type: String,
    val amount: Long,
    val category: String,
    val memo: String,
    val date: Long
)
