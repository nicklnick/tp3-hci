package com.example.fitness_first.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GenericInputField(label: String, value: String, onValueChanged: (String) -> Unit, showText: Boolean) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        visualTransformation = if (showText) VisualTransformation.None else PasswordVisualTransformation(),
        label = { Text(label, fontSize = 20.sp) },
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .padding(top = 20.dp, bottom = 20.dp)
            .fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White
        ),
    )
}