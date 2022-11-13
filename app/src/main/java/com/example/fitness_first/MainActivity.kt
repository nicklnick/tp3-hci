package com.example.fitness_first

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.fitness_first.ui.theme.FitnessfirstTheme
import com.example.fitness_first.util.getViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitnessfirstTheme {
                AppNavHost(
                    rememberNavController(),
                    "landing",
                    viewModel(factory = getViewModelFactory())
                )
            }
        }
    }
}
