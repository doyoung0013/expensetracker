package com.doyoung.expensetracker.ui.add_expense

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddExpenseScreen(
    onBackClick: () -> Unit
) {
    val amount = remember { mutableStateOf("") }
    val category = remember { mutableStateOf("") }
    val memo = remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = amount.value,
            onValueChange = { amount.value = it },
            label = { Text("금액") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = category.value,
            onValueChange = { category.value = it },
            label = { Text("카테고리") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )

        OutlinedTextField(
            value = memo.value,
            onValueChange = { memo.value = it },
            label = { Text("메모") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )

        Button(
            onClick = onBackClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(text = "저장 (더미) 후 돌아가기")
        }
    }
}
