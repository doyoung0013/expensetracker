package com.doyoung.expensetracker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Text

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            Text("홈 화면")
        }
        composable("add_income") {
            Text("수입 추가 화면")
        }
        composable("add_expense") {
            Text("지출 추가 화면")
        }
    }
}
