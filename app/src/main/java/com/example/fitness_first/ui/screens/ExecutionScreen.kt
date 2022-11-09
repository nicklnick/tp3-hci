package com.example.fitness_first.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitness_first.ui.components.IconFAB
import com.example.fitness_first.ui.theme.FitnessfirstTheme
import com.example.fitness_first.ui.theme.Primary
import com.example.fitness_first.ui.theme.Quaternary
import com.example.fitness_first.ui.theme.Tertiary

@Composable
fun ExecutionScreen() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ){
//        Image(
//            painter = painterResource(id = R.drawable.bkg4),
//            contentDescription = "back",
//            contentScale = ContentScale.FillHeight
//        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 5.dp)
            ){
                IconFAB(icon = Icons.Filled.ArrowBack, {} , Modifier.size(40.dp))
                Text(
                    "Routine-Name",
                    modifier = Modifier.padding(start = 20.dp),
                    fontSize = 40.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold
                )
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp),
                border = BorderStroke(2.dp,Primary),
                backgroundColor = Quaternary,
                shape = RoundedCornerShape(20.dp),
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp),
                        border = BorderStroke(1.dp,Primary),
                        shape = RoundedCornerShape(30.dp),
                        backgroundColor = Tertiary,
                    ){
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text("Series 1", fontSize = 20.sp, )
                        }
                    }
                }
            }


        }
    }
}


@Preview(showBackground = true)
@Composable
fun ExecutionScreenPreview() {
    FitnessfirstTheme {
        ExecutionScreen()
    }
}