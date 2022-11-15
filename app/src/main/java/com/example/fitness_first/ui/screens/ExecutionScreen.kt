package com.example.fitness_first.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.example.fitness_first.MainViewModel
import com.example.fitness_first.ui.components.IconFAB
import com.example.fitness_first.R
import com.example.fitness_first.ui.theme.Primary
import com.example.fitness_first.ui.theme.Quaternary
import com.example.fitness_first.ui.theme.Secondary
import com.example.fitness_first.ui.theme.Tertiary

@Composable
fun ExecutionScreen(id: Int, viewModel: MainViewModel) {
    val configuration = LocalConfiguration.current
    viewModel.getRoutine(id)

    when(configuration.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> {
            showVerticalLayout(viewModel.uiState.currentRoutine!!.name)
        }
        else -> {
            showLandscapeLayout(viewModel.uiState.currentRoutine!!.name)
        }
    }
}

@Composable
private fun showVerticalLayout(routineTitle: String) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.bkg4),
            contentDescription = "back",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Text and back button button
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 15.dp)
            ) {
                IconFAB(
                    icon = Icons.Filled.KeyboardArrowLeft,
                    {},
                    Modifier.size(40.dp),
                    Quaternary,
                    Primary
                )
                Text(
                    text = routineTitle,
                    modifier = Modifier.padding(start = 20.dp),
                    fontSize = 40.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.fillMaxWidth())
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
                Row {
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
                    .fillMaxHeight(0.8f)
                    .padding(start = 20.dp, end = 20.dp),
                border = BorderStroke(2.dp, Primary),
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
                        border = BorderStroke(1.dp, Primary),
                        shape = RoundedCornerShape(30.dp),
                        backgroundColor = Secondary.copy(alpha = 0.6f),
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text("Series 1", fontSize = 30.sp, fontWeight = FontWeight.Bold)

                            Spacer(modifier = Modifier.size(100.dp))

                            Card(
                                border = BorderStroke(1.dp, Primary),
                                shape = RoundedCornerShape(30.dp),
                                backgroundColor = Secondary.copy(alpha = 0.6f),
                                modifier = Modifier.fillMaxHeight(),
                                elevation = 20.dp
                            ) {
                                Text(
                                    "2/3",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(
                                        start = 10.dp,
                                        end = 10.dp,
                                        top = 5.dp
                                    )
                                )

                            }
                        }
                    }

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.6f)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    "Pull Ups",
                                    fontSize = 35.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(20.dp)
                                )

                                Card(
                                    backgroundColor = Tertiary,
                                    elevation = 20.dp,
                                ) {
                                    Text(
                                        "20 reps",
                                        fontSize = 35.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(
                                            start = 20.dp,
                                            end = 20.dp,
                                            top = 10.dp,
                                            bottom = 10.dp
                                        )
                                    )
                                }
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
                                    .size(150.dp)
                                    .padding(20.dp),
                                Tertiary,
                                Secondary
                            )
                        }
                    }

                }
            }

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    modifier = Modifier
                        .height(100.dp)
                        .width(300.dp),
                    border = BorderStroke(2.dp, Primary),
                    backgroundColor = Quaternary,
                    shape = RoundedCornerShape(topStart = 20.dp, bottomStart = 20.dp),
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(start = 20.dp)
                    ) {

                        Text(
                            stringResource(id = R.string.exec_next),
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

@Composable
private fun showLandscapeLayout(routineTitle: String) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = com.example.fitness_first.R.drawable.bkg4),
            contentDescription = "back",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxHeight()
        ) {

            // Text and back button button
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 15.dp)
            ) {
                IconFAB(
                    icon = Icons.Filled.KeyboardArrowLeft,
                    {},
                    Modifier.size(40.dp),
                    Quaternary,
                    Primary
                )
                Text(
                    text = routineTitle,
                    modifier = Modifier.padding(start = 20.dp),
                    fontSize = 40.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.fillMaxWidth(0.2f))
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
                Row {
                    Spacer(modifier = Modifier.width(.1f * 165.dp))         // MMM MEDIO DUDOSO
                    Text(
                        "21%",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.DarkGray
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                /* ------ Exercise and controls -------- */
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .fillMaxHeight()
                        .padding(bottom = 20.dp, start = 35.dp, end = 20.dp, top = 5.dp),
                    border = BorderStroke(2.dp, Primary),
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
                            border = BorderStroke(1.dp, Primary),
                            shape = RoundedCornerShape(30.dp),
                            backgroundColor = Secondary.copy(alpha = 0.6f),
                        ) {
                            Row(
                                modifier = Modifier.fillMaxSize(),
                                horizontalArrangement = Arrangement.End,
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Text(
                                    text = "Series 1",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.fillMaxWidth(0.40f))

                                Card(
                                    border = BorderStroke(1.dp, Primary),
                                    shape = RoundedCornerShape(30.dp),
                                    backgroundColor = Secondary.copy(alpha = 0.6f),
                                    modifier = Modifier.fillMaxHeight(),
                                    elevation = 20.dp
                                ) {
                                    Text(
                                        "2/3",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(
                                            start = 10.dp,
                                            end = 10.dp,
                                            top = 5.dp
                                        )
                                    )

                                }
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth(0.7f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    "Pull Ups",
                                    fontSize = 35.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(20.dp)
                                )

                                Card(
                                    backgroundColor = Tertiary,
                                    elevation = 20.dp,
                                ) {
                                    Text(
                                        "20 reps",
                                        fontSize = 35.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(
                                            start = 20.dp,
                                            end = 20.dp,
                                            top = 10.dp,
                                            bottom = 10.dp
                                        )
                                    )
                                }
                            }

                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
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
                            }
                        }
                    }
                }

                /* ------ Description -------- */
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.75f)
                            .padding(bottom = 10.dp, end = 20.dp),
                        border = BorderStroke(1.dp, Primary),
                        shape = RoundedCornerShape(30.dp),
                        backgroundColor = Quaternary
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(40.dp),
                                border = BorderStroke(1.dp, Primary),
                                shape = RoundedCornerShape(30.dp),
                                backgroundColor = Secondary.copy(alpha = 0.6f),
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = stringResource(R.string.exec_description),
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }

                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Quaternary),
                            ) {
                                Text(
                                    text = "Text",
                                    fontSize = 18.sp,
                                    modifier = Modifier.padding(20.dp)
                                )
                            }
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(0.7f)
                                .padding(bottom = 15.dp),
                            border = BorderStroke(2.dp, Primary),
                            backgroundColor = Quaternary,
                            shape = RoundedCornerShape(bottomStart = 20.dp, topStart = 20.dp),
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier.padding(start = 20.dp, top = 2.dp, bottom = 5.dp)
                            ) {

                                Text(
                                    stringResource(id = R.string.exec_next),
                                )

                                Text(
                                    "Rest 30s",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}