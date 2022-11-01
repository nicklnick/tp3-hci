package com.example.fitness_first.ui.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// Mi idea era que se llame a una funcion clickEvent pero no me deja
@Composable
fun GenericSmallButton(label: String, context: Context) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { clickEvent(context = context) },
            enabled = true,
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(text = label.uppercase(), fontWeight = FontWeight.Bold, color = Color.Black)
        }
    }
}

/* Event triggered @click */
private fun clickEvent(context: Context) {
    showToast(text = "Clicked!", context = context)
}

/* TODO: dummy remove! */
fun showToast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}
