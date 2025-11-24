package com.doyoung.expensetracker.ui.statistics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StatisticsScreen() {

    Column(modifier = Modifier.padding(16.dp)) {

        // 전체 요약
        Card(
            modifier = Modifier
                .padding(bottom = 16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "이번 달 총 지출: ₩ 240,000")
                Text(text = "이번 달 총 수입: ₩ 520,000")
                Text(text = "순 잔액: ₩ 280,000")
            }
        }

        // 카테고리별 비율 (더미)
        Text("카테고리별 지출 비율")
        Text("식비: 40%")
        Text("교통: 20%")
        Text("카페: 10%")
        Text("기타: 30%")
    }
}
