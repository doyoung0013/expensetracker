package com.doyoung.expensetracker.ui.transaction_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

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


// -------------------------------------------------------
// UI 모델
// -------------------------------------------------------
sealed interface TransactionListItem {
    data class DateHeader(val label: String) : TransactionListItem
    data class Row(
        val id: Long,
        val name: String,
        val description: String,
        val amount: String
    ) : TransactionListItem
}


// -------------------------------------------------------
// 메인 화면 (완성본)
// -------------------------------------------------------

@Composable
fun TransactionListScreen() {

    val items: List<TransactionListItem> = listOf(
        TransactionListItem.DateHeader("30일 화요일"),
        TransactionListItem.Row(
            id = 1,
            name = "(주)유니컴즈",
            description = "통신비 · 010PAY 체크카드",
            amount = "-810원"
        ),
        TransactionListItem.DateHeader("26일 금요일"),
        TransactionListItem.Row(
            id = 2,
            name = "삼성화재해상보험",
            description = "삼성화재보험 · 010PAY 체크카드",
            amount = "-107,634원"
        ),
        TransactionListItem.Row(
            id = 3,
            name = "삼성화재해상보험",
            description = "보험료 · 010PAY 체크카드",
            amount = "-13,741원"
        ),
        TransactionListItem.DateHeader("25일 목요일"),
        TransactionListItem.Row(
            id = 4,
            name = "한국전력전기요금",
            description = "전기요금 · 010PAY 체크카드",
            amount = "-10,780원"
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BgLight)
    ) {

        // 상단 요약 영역
        item {
            SummaryHeader(
                title = "이번 달 고정지출",
                amount = "283,815원"
            )
        }

        // 여백
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }

        // 카드 + 내부 리스트
        item {
            Card(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = CardWhite),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {

                    items(items) { item ->
                        when (item) {

                            is TransactionListItem.DateHeader -> {
                                DateHeader(label = item.label)
                            }

                            is TransactionListItem.Row -> {
                                TransactionRow(
                                    name = item.name,
                                    description = item.description,
                                    amount = item.amount
                                )
                            }
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
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

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = AmountText
            )
            Text(
                text = "편집",
                style = MaterialTheme.typography.bodyMedium,
                color = PrimaryGreenDark
            )
        }

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
    name: String,
    description: String,
    amount: String
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
                    text = name.firstOrNull()?.toString() ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    color = PrimaryGreenDark
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // 이름 + 설명
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyMedium,
                    color = AmountText
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = CaptionGray,
                    maxLines = 1
                )
            }

            // 금액
            Text(
                text = amount,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = AmountText
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
