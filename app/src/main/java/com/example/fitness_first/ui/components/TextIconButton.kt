package com.example.fitness_first.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitness_first.ui.theme.Tertiary

@Composable
fun TextIconButton(text: String, icon: ImageVector, func: ()->Unit){
    Button(
        onClick = {func},
        modifier = Modifier
            .width(130.dp)
            .height(35.dp)
            .padding(0.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.DarkGray,
            backgroundColor = Tertiary
        ),
        contentPadding = PaddingValues(
            start = 0.dp,
            top = 0.dp,
            end = 0.dp,
            bottom = 0.dp,
        )

    ){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(imageVector = icon,"My Profile")
            Text(
                text,
                fontSize = 10.sp,
                modifier = Modifier.absolutePadding(left = 6.dp),
                color = Color.DarkGray,
            )
        }

    }
}