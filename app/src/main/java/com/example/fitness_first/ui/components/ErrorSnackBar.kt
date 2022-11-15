package com.example.fitness_first.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarData
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitness_first.ui.theme.ErrorColor

@Composable
fun ErrorSnackBar(data: SnackbarData) {
    Snackbar(
        backgroundColor = Color.White,
        contentColor = ErrorColor,
        modifier = Modifier.padding(5.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Card(
            backgroundColor = Color.White,
            border = BorderStroke(5.dp, ErrorColor),
            contentColor = ErrorColor,
            shape = RoundedCornerShape(10.dp),
        ){
            Text(
                data.message,
                fontSize = 25.sp,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}