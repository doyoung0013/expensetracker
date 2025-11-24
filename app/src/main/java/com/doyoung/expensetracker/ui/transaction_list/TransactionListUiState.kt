package com.doyoung.expensetracker.ui.transaction_list

import com.doyoung.expensetracker.data.model.Transaction

data class TransactionListUiState(
    val transactions: List<Transaction> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
