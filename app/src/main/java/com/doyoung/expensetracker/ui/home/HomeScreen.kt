package com.doyoung.expensetracker.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.doyoung.expensetracker.ui.components.PrimaryButton
import com.doyoung.expensetracker.ui.theme.PrimaryGreenDark
import com.doyoung.expensetracker.ui.theme.TextSecondary

@Composable
fun HomeScreen(
    onAddIncomeClick: () -> Unit,
    onAddExpenseClick: () -> Unit,
    onViewTransactionsClick: () -> Unit,
    onViewStatisticsClick: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // 잔액 카드
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {

                Text(text = "이번 달 잔액")

                when {
                    state.isLoading -> Text("로딩 중...")

                    state.error != null -> Text("에러: ${state.error}")

                    else -> {
                        Text(
                            text = "₩ ${state.balance}",
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }
                }
            }
        }

        PrimaryButton(text = "수입 추가", onClick = onAddIncomeClick)
        Spacer(modifier = Modifier.height(10.dp))

        PrimaryButton(text = "지출 추가", onClick = onAddExpenseClick)
        Spacer(modifier = Modifier.height(10.dp))

        PrimaryButton(text = "전체 내역 보기", onClick = onViewTransactionsClick)
        Spacer(modifier = Modifier.height(10.dp))

        PrimaryButton(text = "통계 보기", onClick = onViewStatisticsClick)
    }
}
