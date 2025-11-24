package com.doyoung.expensetracker.domain

import com.doyoung.expensetracker.data.model.Statistics
import com.doyoung.expensetracker.data.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStatisticsUseCase @Inject constructor(
    private val repository: ExpenseRepository
) {
    operator fun invoke(): Flow<Statistics> = repository.getStatistics()
}
