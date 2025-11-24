package com.doyoung.expensetracker.ui.statistics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.doyoung.expensetracker.data.model.CategoryStat

private val BgLight = Color(0xFFF5F7FA)
private val PrimaryBlack = Color(0xFF111827)
private val CaptionGray = Color(0xFF9CA3AF)

@Composable
fun StatisticsScreen(
    viewModel: StatisticsViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsState().value

    when {
        state.isLoading -> {
            LoadingIndicator()
        }

        state.error != null -> {
            ErrorText(state.error)
        }

        state.stats != null -> {
            StatsContent(stats = state.stats!!)
        }
    }
}

@Composable
private fun StatsContent(stats: com.doyoung.expensetracker.data.model.Statistics) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BgLight)
            .padding(16.dp)
    ) {

        item {
            SummaryCard(
                totalIncome = stats.totalIncome,
                totalExpense = stats.totalExpense
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "카테고리별 통계",
                style = MaterialTheme.typography.titleMedium,
                color = PrimaryBlack,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }

        items(stats.categoryStats) { stat ->
            CategoryStatRow(stat)
        }
    }
}

@Composable
private fun SummaryCard(totalIncome: Long, totalExpense: Long) {

    val balance = totalIncome - totalExpense

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {

            Text(
                text = "총 수입",
                style = MaterialTheme.typography.bodyMedium,
                color = CaptionGray
            )
            Text(
                text = "₩ $totalIncome",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "총 지출",
                style = MaterialTheme.typography.bodyMedium,
                color = CaptionGray
            )
            Text(
                text = "-₩ $totalExpense",
                style = MaterialTheme.typography.titleLarge.copy(color = Color(0xFFD32F2F))
            )

            Spacer(modifier = Modifier.height(16.dp))

            Divider()

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "순 잔액",
                style = MaterialTheme.typography.bodyMedium,
                color = CaptionGray
            )
            Text(
                text = "₩ $balance",
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Composable
private fun CategoryStatRow(stat: CategoryStat) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stat.category,
            style = MaterialTheme.typography.bodyMedium,
            color = PrimaryBlack
        )
        Text(
            text = "₩ ${stat.totalAmount}",
            style = MaterialTheme.typography.bodyMedium.copy(color = PrimaryBlack)
        )
    }

    Divider(thickness = 0.5.dp, color = CaptionGray.copy(alpha = 0.2f))
}

@Composable
private fun LoadingIndicator() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorText(msg: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "오류 발생: $msg", color = Color.Red)
    }
}


