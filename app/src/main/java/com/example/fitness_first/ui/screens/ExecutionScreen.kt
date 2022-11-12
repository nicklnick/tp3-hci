package com.example.fitness_first.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.example.fitness_first.ui.components.IconFAB
import com.example.fitness_first.ui.theme.Primary
import com.example.fitness_first.ui.theme.Quaternary
import com.example.fitness_first.ui.theme.Secondary
import com.example.fitness_first.ui.theme.Tertiary

@Composable
fun ExecutionScreen(routineTitle: String) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ){
//        Image(
//            painter = painterResource(id = com.example.fitness_first.R.drawable.bkg4),
//            contentDescription = "back",
//            contentScale = ContentScale.FillHeight
//        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            // Text and back button button
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 15.dp)
            ){
                IconFAB(
                    icon = Icons.Filled.KeyboardArrowLeft,
                    {} ,
                    Modifier.size(40.dp),
                    Quaternary,
                    Primary
                )
                Text(
                    routineTitle,
                    modifier = Modifier.padding(start = 20.dp),
                    fontSize = 40.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold
                )
            }

            // Progress bar
            Column {
                LinearProgressIndicator(
                    modifier = Modifier
                        .height(25.dp)
                        .width(200.dp)
                        .clip(CircleShape),
                    backgroundColor = Color.DarkGray,
                    progress = .1f
                )
                Row(){
                    Spacer(modifier = Modifier.width(.1f * 165.dp))         // MMM MEDIO DUDOSO
                    Text(
                        "21%",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.DarkGray
                    )
                }
            }

            // Exercise and controls
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(650.dp)
                    .padding(bottom = 100.dp, start = 20.dp, end = 20.dp),
                border = BorderStroke(2.dp,Primary),
                backgroundColor = Quaternary,
                shape = RoundedCornerShape(20.dp),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp),
                        border = BorderStroke(1.dp,Primary),
                        shape = RoundedCornerShape(30.dp),
                        backgroundColor = Secondary.copy(alpha = 0.6f),
                    ){
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text("Series 1", fontSize = 20.sp, )

                            Spacer(modifier = Modifier.size(100.dp))
                            
                            Card(
                                border = BorderStroke(1.dp,Primary),
                                shape = RoundedCornerShape(30.dp),
                                backgroundColor = Secondary.copy(alpha = 0.6f),
                                modifier = Modifier.fillMaxHeight(),
                                elevation = 20.dp
                            ){
                                Text(
                                    "2/3",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 5.dp)
                                )

                            }
                        }
                    }

                    Column {
                        Text(
                            "Pull Ups",
                            fontSize = 35.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(20.dp)
                        )

                        Card(
                            backgroundColor = Tertiary,
                            elevation = 20.dp,
                        ){
                            Text(
                                "20 reps",
                                fontSize = 35.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)
                            )
                        }
                    }

                    Row {
                        // TODO: condicional si mostrar o no el boton de pausa
//                        IconFAB(
//                            icon = Icons.Filled.,
//                            func = { /*TODO*/ },
//                            modifier = Modifier
//                                .size(125.dp)
//                                .padding(20.dp),
//                            Tertiary,
//                            Secondary
//                        )
                        IconFAB(
                            icon = Icons.Filled.KeyboardArrowRight,
                            func = { /*TODO*/ },
                            modifier = Modifier
                                .size(125.dp)
                                .padding(20.dp),
                            Tertiary,
                            Secondary
                        )
                    }

                    Card(
                        modifier = Modifier
                            .height(100.dp)
                            .width(300.dp),
                        border = BorderStroke(2.dp,Primary),
                        backgroundColor = Quaternary,
                        shape = RoundedCornerShape(20.dp),
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(start = 20.dp)
                        ) {

                            Text(
                                stringResource(id = com.example.fitness_first.R.string.exec_next),
                                fontSize = 20.sp
                            )

                            Text(
                                "Rest 30s",
                                fontSize = 35.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}