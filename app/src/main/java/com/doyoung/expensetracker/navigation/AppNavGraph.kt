package com.doyoung.expensetracker.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.doyoung.expensetracker.ui.components.BottomNavBar
import com.doyoung.expensetracker.ui.components.TopBar
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import com.doyoung.expensetracker.ui.add_expense.AddExpenseScreen
import com.doyoung.expensetracker.ui.add_income.AddIncomeScreen
import com.doyoung.expensetracker.ui.home.HomeScreen
import com.doyoung.expensetracker.ui.statistics.StatisticsScreen
import com.doyoung.expensetracker.ui.transaction_list.TransactionListScreen

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
            composable("transactions") {
                TransactionListScreen()
            }
            composable("statistics") {
                StatisticsScreen()
            }
            composable("add_income") {
                AddIncomeScreen(onBackClick = { navController.popBackStack() })
            }
            composable("add_expense") {
                AddExpenseScreen(onBackClick = { navController.popBackStack() })
            }

        }
    }
}
