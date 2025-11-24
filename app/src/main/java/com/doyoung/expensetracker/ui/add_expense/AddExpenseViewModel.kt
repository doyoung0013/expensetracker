package com.doyoung.expensetracker.ui.add_expense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doyoung.expensetracker.domain.AddExpenseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class AddExpenseViewModel @Inject constructor(
    private val addExpenseUseCase: AddExpenseUseCase
) : ViewModel() {

    fun addExpense(
        amount: Long,
        category: String,
        memo: String,
        date: LocalDate,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            addExpenseUseCase(
                amount = amount,
                category = category,
                memo = memo,
                date = date
            )
            onSuccess()
        }
    }
}
