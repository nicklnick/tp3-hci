package com.example.fitness_first.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitness_first.ui.theme.LightBlue
import com.example.fitness_first.ui.theme.Primary
import com.example.fitness_first.ui.theme.Tertiary

@Composable
fun CategoryCard(text: String, icon: ImageVector, func: ()->Unit){
    Button(
        onClick = func,
        modifier = Modifier
            .width(140.dp)
            .height(110.dp)
            .padding(0.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.DarkGray,
            backgroundColor = Tertiary
        ),
        border = BorderStroke(2.dp, Primary),
        contentPadding = PaddingValues(0.dp)

    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = func,
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp)
                    .padding(0.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.DarkGray,
                    backgroundColor = Tertiary
                ),
                border = BorderStroke(2.dp, Primary),
                contentPadding = PaddingValues(0.dp)

            ){
                Icon(imageVector = icon,"My Profile")
            }
            Text(
                text,
                fontSize = 24.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(0.dp)
            )
        }

    }
}