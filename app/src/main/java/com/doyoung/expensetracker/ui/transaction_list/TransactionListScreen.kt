package com.doyoung.expensetracker.ui.transaction_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class TransactionUiModel(
    val id: Long,
    val type: String,
    val amount: String,
    val category: String,
    val date: String
)

@Composable
fun TransactionListScreen() {

    // 더미 데이터 3개
    val dummyList = listOf(
        TransactionUiModel(1, "수입", "₩ 50,000", "알바", "2025-11-01"),
        TransactionUiModel(2, "지출", "₩ 13,000", "식비", "2025-11-01"),
        TransactionUiModel(3, "지출", "₩ 5,000", "카페", "2025-11-02"),
    )

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(dummyList) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(text = "${item.type} · ${item.category}")   // 수입 · 알바
                    Text(text = item.amount)                          // ₩ 50,000
                    Text(text = item.date)                            // 2025-11-01
                }
            }
        }
    }
}
