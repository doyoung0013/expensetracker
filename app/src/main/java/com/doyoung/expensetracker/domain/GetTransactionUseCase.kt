package com.doyoung.expensetracker.domain

import com.doyoung.expensetracker.data.model.Transaction
import com.doyoung.expensetracker.data.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTransactionsUseCase @Inject constructor(
    private val repository: ExpenseRepository
) {
    operator fun invoke(): Flow<List<Transaction>> = repository.getTransactions()
}
