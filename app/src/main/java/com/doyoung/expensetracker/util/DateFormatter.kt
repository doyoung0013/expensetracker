package com.doyoung.expensetracker.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateFormatter {
    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")

    fun format(date: LocalDate): String = date.format(dateFormatter)
}
