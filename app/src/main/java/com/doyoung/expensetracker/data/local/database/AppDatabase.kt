package com.doyoung.expensetracker.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.doyoung.expensetracker.data.local.dao.TransactionDao
import com.doyoung.expensetracker.data.local.entity.TransactionEntity

@Database(
    entities = [TransactionEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}
