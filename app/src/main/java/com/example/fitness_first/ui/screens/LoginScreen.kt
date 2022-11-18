package com.example.fitness_first.ui.screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitness_first.MainViewModel
import com.example.fitness_first.R
import com.example.fitness_first.data.model.Category
import com.example.fitness_first.ui.components.*
import com.example.fitness_first.ui.theme.Primary
import com.example.fitness_first.ui.theme.Quaternary
import kotlin.math.abs

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(backFunc: () -> Unit, loginFunc: () -> Unit, viewModel: MainViewModel) {
    if(LocalContext.current.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >=  Configuration.SCREENLAYOUT_SIZE_LARGE){
        LoginScreenTablet(backFunc = backFunc, loginFunc = loginFunc, viewModel = viewModel)
    }
    else{
        LoginScreenPhone(backFunc = backFunc, loginFunc = loginFunc, viewModel = viewModel)
    }
}
