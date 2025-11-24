package com.doyoung.expensetracker.data.repository

import com.doyoung.expensetracker.data.local.dao.TransactionDao
import com.doyoung.expensetracker.data.model.Transaction
import com.doyoung.expensetracker.data.model.Statistics
import com.doyoung.expensetracker.data.model.toDomain
import com.doyoung.expensetracker.data.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

interface ExpenseRepository {
    suspend fun addTransaction(transaction: Transaction)
    suspend fun deleteTransaction(id: Long)
    fun getTransactions(): Flow<List<Transaction>>
    fun getBalance(): Flow<Long>
    fun getStatistics(): Flow<Statistics>
}

@Singleton
class ExpenseRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao
) : ExpenseRepository {

    override suspend fun addTransaction(transaction: Transaction) {
        transactionDao.insert(transaction.toEntity())
    }

    override suspend fun deleteTransaction(id: Long) {
        transactionDao.deleteById(id)
    }

    override fun getTransactions(): Flow<List<Transaction>> {
        return transactionDao.getAll().map { list -> list.map { it.toDomain() } }
    }

    override fun getBalance(): Flow<Long> {
        return transactionDao.getBalanceFlow().map { it ?: 0L }
    }

    override fun getStatistics(): Flow<Statistics> {
        return transactionDao.getAll().map { entities ->
            var income = 0L
            var expense = 0L
            val byCategory = mutableMapOf<String, Long>()

            entities.forEach { e ->
                if (e.type == "INCOME") income += e.amount else expense += e.amount
                byCategory[e.category] = (byCategory[e.category] ?: 0L) + e.amount
            }

            Statistics(
                totalIncome = income,
                totalExpense = expense,
                categoryStats = byCategory.map { (k, v) ->
                    com.doyoung.expensetracker.data.model.CategoryStat(k, v)
                }
            )
        }
    }
}
