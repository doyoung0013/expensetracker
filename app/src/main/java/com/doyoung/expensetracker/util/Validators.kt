package com.doyoung.expensetracker.util

object Validators {

    fun isValidAmount(input: String): Boolean =
        input.toLongOrNull()?.let { it > 0 } ?: false

    fun isNonBlank(input: String): Boolean = input.isNotBlank()
}
