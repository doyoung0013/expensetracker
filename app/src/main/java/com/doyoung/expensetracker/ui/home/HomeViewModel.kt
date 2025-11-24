package com.doyoung.expensetracker.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doyoung.expensetracker.domain.GetTransactionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeState(
    val isLoading: Boolean = true,
    val balance: Long = 0L,
    val error: String? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTransactionsUseCase: GetTransactionsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    init {
        loadBalance()
    }

    private fun loadBalance() {
        viewModelScope.launch {
            getTransactionsUseCase()
                .catch { e ->
                    _state.update {
                        it.copy(isLoading = false, error = e.message)
                    }
                }
                .collect { list ->
                    val income = list.filter { it.type.name == "INCOME" }.sumOf { it.amount }
                    val expense = list.filter { it.type.name == "EXPENSE" }.sumOf { it.amount }
                    val balance = income - expense

                    _state.update {
                        it.copy(isLoading = false, balance = balance)
                    }
                }
        }
    }
}
