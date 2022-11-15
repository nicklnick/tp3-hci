package com.example.fitness_first.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.fitness_first.MainViewModel
import com.example.fitness_first.R
import com.example.fitness_first.ui.components.UserInfoCard
import com.example.fitness_first.ui.theme.Primary


@Composable
fun MyProfileScreen(viewModel: MainViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ){
        Image(
            painter = painterResource(id = R.drawable.bkg4),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ){

            Card(
                backgroundColor = Color.White,
                modifier = Modifier
                    .size(100.dp)
                    .padding(6.dp),
                contentColor = Color.DarkGray,
                shape = CircleShape,
                border = BorderStroke(3.dp, Primary)
            ){
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Account",
                    modifier = Modifier.size(50.dp)
                )
            }

            if(viewModel.uiState.currentUser != null){
                UserInfoCard(stringResource(id = R.string.profile_name)+ ": " + viewModel.uiState.currentUser!!.firstName)
                UserInfoCard(stringResource(id = R.string.profile_surname)+ ": " + viewModel.uiState.currentUser!!.lastName)
                UserInfoCard(stringResource(id = R.string.profile_username)+ ": " + viewModel.uiState.currentUser!!.username)
                UserInfoCard(stringResource(id = R.string.profile_email)+ ": " + viewModel.uiState.currentUser!!.email)
            }
            else{
                UserInfoCard(stringResource(id = R.string.profile_name)+ ": ")
                UserInfoCard(stringResource(id = R.string.profile_surname)+ ": ")
                UserInfoCard(stringResource(id = R.string.profile_username)+ ": ")
                UserInfoCard(stringResource(id = R.string.profile_email)+ ": ")
            }

        }
    }

}