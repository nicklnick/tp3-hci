package com.example.fitness_first.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitness_first.R
import com.example.fitness_first.ui.components.GenericInputField
import com.example.fitness_first.ui.components.GenericLongButton
import com.example.fitness_first.ui.components.IconFAB
import com.example.fitness_first.ui.theme.FitnessfirstTheme
import com.example.fitness_first.ui.theme.Primary
import com.example.fitness_first.ui.theme.Quaternary
import com.example.fitness_first.ui.theme.Sand

@Composable
fun RegisterScreen(backFunc: () -> Unit, registerFunc: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ){
//        Image(
//            painter = painterResource(id = R.drawable.bkg3),
//            contentDescription = null,
//            contentScale = ContentScale.FillHeight
//        )
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(),
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, start = 30.dp)
            ) {
                IconFAB(icon = Icons.Filled.KeyboardArrowLeft, { backFunc() }, Modifier.size(80.dp), Quaternary, Primary )
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
                        text = stringResource(R.string.reg_title),
                        fontSize = 50.sp,
                        color = Color.DarkGray
                    )
                    GenericInputField(label = stringResource(R.string.reg_email), value = "")

                    GenericInputField(label = stringResource(R.string.reg_user), value = "")

                    GenericInputField(label = stringResource(R.string.reg_password), value = "")

                    GenericLongButton(stringResource(R.string.reg_continue),  { registerFunc() } )
                }


            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    FitnessfirstTheme {
        RegisterScreen({}, {})
    }
}