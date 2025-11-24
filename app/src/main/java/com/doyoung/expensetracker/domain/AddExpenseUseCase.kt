package com.doyoung.expensetracker.domain

import com.doyoung.expensetracker.data.model.Transaction
import com.doyoung.expensetracker.data.model.TransactionType
import com.doyoung.expensetracker.data.repository.ExpenseRepository
import java.time.LocalDate
import javax.inject.Inject

class AddExpenseUseCase @Inject constructor(
    private val repository: ExpenseRepository
) {
    suspend operator fun invoke(
        amount: Long,
        category: String,
        memo: String,
        date: LocalDate
    ) {
        val tx = Transaction(
            type = TransactionType.EXPENSE,
            amount = amount,
            category = category,
            memo = memo,
            date = date
        )
        repository.addTransaction(tx)
    }
}
