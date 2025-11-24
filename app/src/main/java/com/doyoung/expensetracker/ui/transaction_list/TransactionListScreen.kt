package com.doyoung.expensetracker.ui.transaction_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.doyoung.expensetracker.data.model.Transaction
import com.doyoung.expensetracker.data.model.TransactionType
import java.time.LocalDate

// -------------------------------------------------------
// 색상
// -------------------------------------------------------
private val BgLight = Color(0xFFF5F7FA)
private val CardWhite = Color(0xFFFFFFFF)
private val CaptionGray = Color(0xFF9CA3AF)
private val DividerGray = Color(0xFFE5E7EB)
private val PrimaryGreenDark = Color(0xFF2E7D32)
private val SecondaryGreen = Color(0xFF81C784)
private val AmountText = Color(0xFF111827)
private val ExpenseRed = Color(0xFFD32F2F)
private val IncomeBlue = Color(0xFF1565C0)


// -------------------------------------------------------
// UI 모델 (날짜 헤더 + 행)
// -------------------------------------------------------
sealed interface TransactionListItem {
    data class DateHeader(val label: String) : TransactionListItem
    data class Row(
        val id: Long,
        val title: String,
        val description: String,
        val amountText: String,
        val isExpense: Boolean
    ) : TransactionListItem
}


// -------------------------------------------------------
// 메인 화면 (DB 연동 버전)
// -------------------------------------------------------

@Composable
fun TransactionListScreen(
    viewModel: TransactionListViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    // 로딩 / 에러 처리
    when {
        state.isLoading -> {
            LoadingIndicator()
            return
        }
        state.error != null -> {
            ErrorText(state.error!!)
            return
        }
    }

    val transactions = state.transactions

    // ✅ 상단 요약용: 이번 달 지출 합계
    val totalExpense = transactions
        .filter { it.type == TransactionType.EXPENSE }
        .sumOf { it.amount }

    // ✅ 리스트용 UI 아이템 변환 (날짜별 그룹 → DateHeader + Row)
    val uiItems: List<TransactionListItem> = buildUiItems(transactions)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BgLight)
    ) {

        // 상단 요약
        item {
            SummaryHeader(
                title = "이번 달 지출",
                amount = formatCurrency(totalExpense)
            )
        }

        item { Spacer(modifier = Modifier.height(8.dp)) }

        // 카드로 전체 리스트 감싸기 (Card 안에는 Column만, LazyColumn 없음 → Crash X)
        item {
            Card(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = CardWhite),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(modifier = Modifier.padding(vertical = 8.dp)) {

                    uiItems.forEach { item ->
                        when (item) {
                            is TransactionListItem.DateHeader -> {
                                DateHeader(label = item.label)
                            }

                            is TransactionListItem.Row -> {
                                TransactionRow(
                                    title = item.title,
                                    description = item.description,
                                    amountText = item.amountText,
                                    isExpense = item.isExpense
                                )
                            }
                        }
                    }
                }
            }
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }
    }
}


// -------------------------------------------------------
// UI 상태 표시
// -------------------------------------------------------

@Composable
private fun LoadingIndicator() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BgLight),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorText(msg: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BgLight),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "오류 발생: $msg", color = Color.Red)
    }
}


// -------------------------------------------------------
// Summary Header
// -------------------------------------------------------

@Composable
private fun SummaryHeader(
    title: String,
    amount: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(BgLight)
            .padding(horizontal = 20.dp, vertical = 24.dp)
    ) {

        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = AmountText
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = amount,
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.SemiBold
            ),
            color = AmountText
        )
    }
}


// -------------------------------------------------------
// 날짜 헤더
// -------------------------------------------------------

@Composable
private fun DateHeader(label: String) {
    Text(
        text = label,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        style = MaterialTheme.typography.bodySmall,
        color = CaptionGray
    )
}


// -------------------------------------------------------
// 개별 거래 행
// -------------------------------------------------------

@Composable
private fun TransactionRow(
    title: String,
    description: String,
    amountText: String,
    isExpense: Boolean
) {
    Column {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // 왼쪽 동그라미 아이콘
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .background(SecondaryGreen.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title.firstOrNull()?.toString() ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    color = PrimaryGreenDark
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // 제목 + 설명
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium,
                    color = AmountText
                )
                if (description.isNotBlank()) {
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodySmall,
                        color = CaptionGray,
                        maxLines = 1
                    )
                }
            }

            // 금액
            Text(
                text = amountText,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = if (isExpense) ExpenseRed else IncomeBlue
            )
        }

        // 구분선
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .background(DividerGray)
        )
    }
}


// -------------------------------------------------------
// 헬퍼 함수들 (Transaction → UI 모델 변환 등)
// -------------------------------------------------------

// Transaction 리스트를 날짜별로 그룹핑해서
// DateHeader + Row 리스트로 평탄화
private fun buildUiItems(transactions: List<Transaction>): List<TransactionListItem> {
    if (transactions.isEmpty()) return emptyList()

    val sorted = transactions.sortedByDescending { it.date } // 최신 날짜 위로
    val grouped: Map<LocalDate, List<Transaction>> = sorted.groupBy { it.date }

    val result = mutableListOf<TransactionListItem>()

    grouped.forEach { (date, list) ->
        val dateLabel = "${date.dayOfMonth}일 ${date.dayOfWeekKorean()}"
        result += TransactionListItem.DateHeader(dateLabel)

        list.forEach { tx ->
            val isExpense = tx.type == TransactionType.EXPENSE
            val amountText = (if (isExpense) "-" else "+") + formatCurrency(tx.amount)

            result += TransactionListItem.Row(
                id = tx.id,
                title = tx.category,      // 카테고리를 제목처럼
                description = tx.memo,    // 메모를 설명으로
                amountText = amountText,
                isExpense = isExpense
            )
        }
    }

    return result
}

// 1,000 단위 콤마 + "원"
private fun formatCurrency(amount: Long): String =
    "%,d원".format(amount)

// 요일 한글 변환
private fun LocalDate.dayOfWeekKorean(): String =
    when (this.dayOfWeek.value) {
        1 -> "월요일"
        2 -> "화요일"
        3 -> "수요일"
        4 -> "목요일"
        5 -> "금요일"
        6 -> "토요일"
        else -> "일요일"
    }
