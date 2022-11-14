package com.example.fitness_first.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitness_first.R
import com.example.fitness_first.ui.theme.Primary
import com.example.fitness_first.ui.theme.Tertiary


@Composable
fun UserInfoCard(text: String){
    Card(
        backgroundColor = Color.White,
        modifier = Modifier
            .height(70.dp)
            .width(350.dp)
            .padding(6.dp),
        contentColor = Color.DarkGray,
        shape = RoundedCornerShape(30.dp),
        border = BorderStroke(3.dp, Primary)
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text, fontSize = 20.sp)
        }
    }
}