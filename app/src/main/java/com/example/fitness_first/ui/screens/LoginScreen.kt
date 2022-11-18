package com.example.fitness_first.ui.screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.example.fitness_first.MainViewModel
import com.example.fitness_first.ui.components.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(backFunc: () -> Unit, loginFunc: () -> Unit, viewModel: MainViewModel) {
    if (LocalContext.current.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_LARGE) {
        LoginScreenTablet(backFunc = backFunc, loginFunc = loginFunc, viewModel = viewModel)
    } else {
        LoginScreenPhone(backFunc = backFunc, loginFunc = loginFunc, viewModel = viewModel)
    }
}
