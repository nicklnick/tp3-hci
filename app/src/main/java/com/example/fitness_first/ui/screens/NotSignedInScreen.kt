package com.example.fitness_first.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitness_first.R
import com.example.fitness_first.ui.components.GenericLongButton
import com.example.fitness_first.ui.components.GenericSmallButton
import com.example.fitness_first.ui.components.GenericSmallOutlinedButton
import com.example.fitness_first.ui.theme.Primary
import com.example.fitness_first.ui.theme.Quaternary

@Composable
fun NotSignedInScreen(toLoginScreen: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.bkg4),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                backgroundColor = Color.White,
                modifier = Modifier.fillMaxWidth(0.9f).fillMaxHeight(0.3f),
                border = BorderStroke(2.dp, Primary)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Text(
                        "You don't seem to be logged in. You must do so if you wish to see this routine.",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(10.dp)
                    )
                    GenericSmallButton("Login", toLoginScreen)
                }
            }
        }
    }

}