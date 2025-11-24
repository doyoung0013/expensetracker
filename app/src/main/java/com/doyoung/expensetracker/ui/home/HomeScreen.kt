package com.doyoung.expensetracker.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onAddIncomeClick: () -> Unit,
    onAddExpenseClick: () -> Unit,
    onViewTransactionsClick: () -> Unit,
    onViewStatisticsClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 잔액 카드
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "이번 달 잔액")
                Text(text = "₩ 1,234,567")
            }
        }

        // 버튼들
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            onClick = onAddIncomeClick
        ) {
            Text(text = "수입 추가")
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            onClick = onAddExpenseClick
        ) {
            Text(text = "지출 추가")
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            onClick = onViewTransactionsClick
        ) {
            Text(text = "전체 내역 보기")
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onViewStatisticsClick
        ) {
            Text(text = "통계 보기")
        }
    }
}
