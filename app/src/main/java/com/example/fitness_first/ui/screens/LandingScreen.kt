package com.example.fitness_first.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitness_first.MainViewModel
import com.example.fitness_first.R
import com.example.fitness_first.ui.components.GenericSmallButton
import com.example.fitness_first.ui.components.GenericSmallOutlinedButton
import com.example.fitness_first.util.getViewModelFactory

@Composable
fun LandingScreen(signupFunc: () -> Unit, loginFunc: () -> Unit, loggedInFunc: () -> Unit, viewModel: MainViewModel){
    if(viewModel.uiState.isAuthenticated) {
        loggedInFunc()
    }
    Surface(
        modifier = Modifier.fillMaxSize()
    ){
        Image(
            painter = painterResource(id = R.drawable.bkg3),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
        )
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(),
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(300.dp)
                    .padding(top = 20.dp)
                )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 70.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
//                GenericSmallButton(label = stringResource(id = R.string.land_signup),  signupFunc )

                GenericSmallOutlinedButton(label = stringResource(id = R.string.land_login), loginFunc  )
            }
        }
    }
}