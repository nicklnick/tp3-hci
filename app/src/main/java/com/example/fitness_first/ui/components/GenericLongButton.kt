package com.example.fitness_first.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitness_first.ui.theme.Primary

@Composable
fun LongButton(text: String, func: ()->Unit){
    Button(
        onClick = {func},
        modifier = Modifier.width(130.dp).height(20.dp).padding(0.dp),
        shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.DarkGray,
            backgroundColor = Primary
        ),
        contentPadding = PaddingValues(
            start = 0.dp,
            top = 0.dp,
            end = 0.dp,
            bottom = 0.dp,
        )

    ){
        Text(
            text,
            fontSize = 10.sp,
            modifier = Modifier.padding(0.dp)
        )
    }
}