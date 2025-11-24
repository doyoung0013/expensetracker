package com.doyoung.expensetracker.di

import android.app.Application
import androidx.room.Room
import com.doyoung.expensetracker.data.local.database.AppDatabase
import com.doyoung.expensetracker.data.local.dao.TransactionDao
import com.doyoung.expensetracker.data.repository.ExpenseRepository
import com.doyoung.expensetracker.data.repository.ExpenseRepositoryImpl
import com.doyoung.expensetracker.util.Constants
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindExpenseRepository(
        impl: ExpenseRepositoryImpl
    ): ExpenseRepository
}

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        app: Application
    ): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            Constants.DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTransactionDao(
        db: AppDatabase
    ): TransactionDao = db.transactionDao()
}
