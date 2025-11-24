package com.doyoung.expensetracker.data.model

import com.doyoung.expensetracker.data.local.entity.TransactionEntity
import java.time.LocalDate
import java.time.ZoneId

enum class TransactionType {
    INCOME, EXPENSE
}

data class Transaction(
    val id: Long = 0L,
    val type: TransactionType,
    val amount: Long,
    val category: String,
    val memo: String,
    val date: LocalDate
)

fun TransactionEntity.toDomain(): Transaction {
    val localDate = LocalDate.ofEpochDay(this.date / (24 * 60 * 60 * 1000))
    return Transaction(
        id = id,
        type = if (type == "INCOME") TransactionType.INCOME else TransactionType.EXPENSE,
        amount = amount,
        category = category,
        memo = memo,
        date = localDate
    )
}

fun Transaction.toEntity(): TransactionEntity {
    val millis = date
        .atStartOfDay(ZoneId.systemDefault())
        .toInstant()
        .toEpochMilli()

    return TransactionEntity(
        id = id,
        type = type.name,
        amount = amount,
        category = category,
        memo = memo,
        date = millis
    )
}
