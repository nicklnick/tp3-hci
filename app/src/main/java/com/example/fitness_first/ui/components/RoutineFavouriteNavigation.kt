package com.example.fitness_first.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.fitness_first.ui.theme.Primary
import com.example.fitness_first.ui.theme.Tertiary


@Composable
fun RoutineFavouriteNavigation(selected : String, nonSelected : String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxHeight()
    ) {
        SelectedButton(label = "My Routines") {

        }
        NonSelectedButton(label = "Favourites") {

        }
    }
}

@Composable
fun SelectedButton(label: String, clickEvent: () -> Unit) {
    Button(
        onClick = clickEvent,
        enabled = true,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.DarkGray,
            backgroundColor = Tertiary
        ),
        border = BorderStroke(2.dp, Primary),
        contentPadding = PaddingValues(0.dp)

    ) {
        Text(text = label.uppercase(), fontWeight = FontWeight.Bold, color = Color.Black)
    }
}

@Composable
fun NonSelectedButton(label: String, clickEvent: () -> Unit) {
    Button(
        onClick = clickEvent,
        enabled = true,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.DarkGray,
            backgroundColor = Tertiary
        ),

        ) {
        Text(text = label.uppercase(), fontWeight = FontWeight.Bold, color = Color.Black)
    }
}