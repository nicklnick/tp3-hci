package com.example.fitness_first.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun SimpleChip(text: String, color: Color) {
    Surface(
        shape = CircleShape,
        color = color,
        border = BorderStroke(width = 1.dp, color = Color.LightGray),
        modifier = Modifier.padding(horizontal = 2.dp)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
            text = text,
            textAlign = TextAlign.Center,
        )
    }
}
