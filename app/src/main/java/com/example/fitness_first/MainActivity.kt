package com.example.fitness_first

import android.content.Context
import android.net.wifi.WifiManager
import android.os.Bundle
import android.provider.Settings
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
                ) {
                    Settings.System.getInt(contentResolver, Settings.Global.AIRPLANE_MODE_ON, 0) == 1
                }

            }
        }
    }
}
