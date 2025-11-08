package com.alexmls.circularstocktracker

import ProductRoot
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.alexmls.circularstocktracker.ui.theme.CircularStockTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CircularStockTrackerTheme {
                ProductRoot()
            }
        }
    }
}
