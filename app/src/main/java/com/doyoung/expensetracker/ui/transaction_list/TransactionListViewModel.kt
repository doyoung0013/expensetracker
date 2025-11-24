package com.doyoung.expensetracker.ui.transaction_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doyoung.expensetracker.domain.GetTransactionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionListViewModel @Inject constructor(
    private val getTransactionsUseCase: GetTransactionsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(TransactionListUiState(isLoading = true))
    val uiState: StateFlow<TransactionListUiState> = _uiState

    init {
        loadTransactions()
    }

    private fun loadTransactions() {
        viewModelScope.launch {
            getTransactionsUseCase()
                .onStart {
                    _uiState.value = TransactionListUiState(isLoading = true)
                }
                .catch { e ->
                    _uiState.value = TransactionListUiState(
                        transactions = emptyList(),
                        isLoading = false,
                        error = e.message ?: "Unknown error"
                    )
                }
                .collect { list ->
                    _uiState.value = TransactionListUiState(
                        transactions = list,
                        isLoading = false,
                        error = null
                    )
                }
        }
    }
}
