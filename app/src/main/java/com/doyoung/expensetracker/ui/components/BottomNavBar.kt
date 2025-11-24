package com.doyoung.expensetracker.ui.components

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

data class BottomNavItem(
    val route: String,
    val label: String
)

private val bottomNavItems = listOf(
    BottomNavItem("home", "홈"),
    BottomNavItem("transactions", "내역"),
    BottomNavItem("statistics", "통계")
)

@Composable
fun BottomNavBar(
    currentRoute: String,
    onNavigate: (String) -> Unit
) {
    NavigationBar {
        bottomNavItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = { onNavigate(item.route) },
                label = { Text(item.label) },
                icon = {}
            )
        }
    }
}
