package com.doyoung.expensetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.doyoung.expensetracker.ui.theme.ExpenseTrackerTheme
import com.doyoung.expensetracker.navigation.AppNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpenseTrackerTheme {
                AppNavGraph()
            }
        }
    }
}
