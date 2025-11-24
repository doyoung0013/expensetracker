package com.doyoung.expensetracker.ui.add_income

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doyoung.expensetracker.domain.AddIncomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class AddIncomeViewModel @Inject constructor(
    private val addIncomeUseCase: AddIncomeUseCase
) : ViewModel() {

    fun addIncome(
        amount: Long,
        category: String,
        memo: String,
        date: LocalDate,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            addIncomeUseCase(
                amount = amount,
                category = category,
                memo = memo,
                date = date
            )
            onSuccess()
        }
    }
}
