package com.example.fitness_first.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitness_first.ui.components.GenericInputField
import com.example.fitness_first.ui.components.GenericLongButton
import com.example.fitness_first.ui.components.IconFAB
import com.example.fitness_first.ui.theme.FitnessfirstTheme
import com.example.fitness_first.ui.theme.Quaternary
import com.example.fitness_first.ui.theme.Sand

@Composable
fun RegisterScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Sand
    ){
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(),
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 20.dp)
            ) {
                IconFAB(icon = Icons.Filled.ArrowBack, {})
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(650.dp),
                shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
                backgroundColor = Quaternary
            ) {
                Column(
                    modifier = Modifier
                        .width(100.dp)
                        .padding(30.dp)
                ) {
                    Text(
                        text = "Register",
                        fontSize = 50.sp,
                        color = Color.DarkGray
                    )
                    GenericInputField(label = "Email", value = "")

                    GenericInputField(label = "Username", value = "")

                    GenericInputField(label = "Password", value = "")

                    GenericLongButton("Continue", {})
                }


            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    FitnessfirstTheme {
        RegisterScreen()
    }
}