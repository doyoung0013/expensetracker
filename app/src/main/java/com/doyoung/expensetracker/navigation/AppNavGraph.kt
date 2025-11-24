package com.doyoung.expensetracker.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.doyoung.expensetracker.ui.components.BottomNavBar
import com.doyoung.expensetracker.ui.components.TopBar
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import com.doyoung.expensetracker.ui.home.HomeScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val backStack = navController.currentBackStackEntryAsState()
    val current = backStack.value?.destination?.route ?: "home"

    Scaffold(
        topBar = {
            TopBar(
                title = when (current) {
                    "home" -> "가계부 홈"
                    "transactions" -> "전체 내역"
                    "statistics" -> "통계"
                    else -> ""
                }
            )
        },
        bottomBar = {
            BottomNavBar(
                currentRoute = current,
                onNavigate = { route ->
                    navController.navigate(route) {
                        popUpTo("home") { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = androidx.compose.ui.Modifier.padding(innerPadding)
        ) {
            composable("home") {
                HomeScreen(
                    onAddIncomeClick = { navController.navigate("add_income") },
                    onAddExpenseClick = { navController.navigate("add_expense") },
                    onViewTransactionsClick = { navController.navigate("transactions") },
                    onViewStatisticsClick = { navController.navigate("statistics") }
                )
            }
            composable("transactions") { Text("전체 내역 화면") }
            composable("statistics") { Text("통계 화면") }
        }
    }
}
