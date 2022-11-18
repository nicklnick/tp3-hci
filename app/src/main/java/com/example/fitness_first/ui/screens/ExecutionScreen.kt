package com.example.fitness_first.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.example.fitness_first.MainViewModel
import com.example.fitness_first.R
import com.example.fitness_first.ui.components.IconFAB
import com.example.fitness_first.ui.theme.Primary
import com.example.fitness_first.ui.theme.Quaternary
import com.example.fitness_first.ui.theme.Secondary
import com.example.fitness_first.ui.theme.Tertiary


@Composable
fun ExecutionScreen(id: Int, prev: () -> Unit, finish: () -> Unit, viewModel: MainViewModel) {
    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> {
            showVerticalLayout(viewModel.uiState.currentRoutine!!.name, prev, finish, viewModel)
        }
        else -> {
            showLandscapeLayout(viewModel.uiState.currentRoutine!!.name, prev, finish, viewModel)
        }
    }
}

@Composable
private fun showVerticalLayout(routineTitle: String, prev: () -> Unit, finish: () -> Unit, viewModel: MainViewModel) {
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 15.dp)
            ) {
                IconFAB(
                    icon = Icons.Filled.KeyboardArrowLeft,
                    prev,
                    Modifier.size(40.dp),
                    Quaternary,
                    Primary
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(0.8f),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = routineTitle,
                        modifier = Modifier.padding(start = 20.dp),
                        fontSize = 30.sp,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                }
            }

            // Progress bar
            Column {
                LinearProgressIndicator(
                    modifier = Modifier
                        .height(25.dp)
                        .width(200.dp)
                        .clip(CircleShape),
                    backgroundColor = Color.DarkGray,
                    progress = (viewModel.uiState.exerciseCount.toFloat() / viewModel.uiState.routineSize.toFloat())
                )
                Row {
                    Spacer(modifier = Modifier.width((viewModel.uiState.exerciseCount.toFloat() / viewModel.uiState.routineSize.toFloat()) * 165.dp))         // MMM MEDIO DUDOSO
                    Text(
                        "${((viewModel.uiState.exerciseCount.toFloat() / viewModel.uiState.routineSize.toFloat()) * 100).toInt()}%",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
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
                            Row(
                                modifier = Modifier.fillMaxWidth(0.8f),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                            ) {
                                Text(
                                    viewModel.uiState.currentExecSeries!!.cycleName,
                                    fontSize = 30.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            Card(
                                border = BorderStroke(1.dp, Primary),
                                shape = RoundedCornerShape(30.dp),
                                backgroundColor = Secondary.copy(alpha = 0.6f),
                                modifier = Modifier.fillMaxHeight(),
                                elevation = 20.dp
                            ) {
                                Text(
                                    "${viewModel.uiState.seriesRepCount} / ${viewModel.uiState.currentExecSeries!!.cycleRepetitions}",
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
                                    viewModel.uiState.currentExecExercise!!.exercise.name,
                                    fontSize = 35.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(20.dp),
                                    overflow = TextOverflow.Ellipsis
                                )

                                Card(
                                    backgroundColor = Tertiary,
                                    elevation = 20.dp,
                                ) {
                                    if (viewModel.uiState.currentExecExercise!!.duration != 0)
                                        Text(
                                            "${viewModel.uiState.currentTimeExercise} ${stringResource(R.string.exec_seconds)}",
                                            fontSize = 35.sp,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(
                                                start = 20.dp,
                                                end = 20.dp,
                                                top = 10.dp,
                                                bottom = 10.dp
                                            )
                                        )
                                    else
                                        Text(
                                            "${viewModel.uiState.currentExecExercise!!.repetitions} ${stringResource(R.string.exec_reps)}",
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
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(5.dp)
                        ) {
                            if (!viewModel.isFirstExercise()) {
                                IconFAB(
                                    icon = Icons.Filled.KeyboardArrowLeft,
                                    func = { viewModel.previousExercise() },
                                    modifier = Modifier
                                        .size(100.dp)
                                        .offset(x = 20.dp),
                                    Tertiary,
                                    Secondary
                                )
                            }


                            if (viewModel.canPauseExecution()) {
                                if (viewModel.uiState.pausedExec) {
                                    IconFAB(
                                        icon = Icons.Filled.PlayArrow,
                                        func = { viewModel.unpauseExecution() },
                                        modifier = Modifier
                                            .size(100.dp)
                                            .offset(x = 130.dp),
                                        Tertiary,
                                        Secondary
                                    )
                                } else {
                                    IconFAB(
                                        icon = ImageVector.vectorResource(R.drawable.pause_icons),
                                        func = { viewModel.pauseExecution() },
                                        modifier = Modifier
                                            .size(100.dp)
                                            .offset(x = 130.dp),
                                        Tertiary,
                                        Secondary
                                    )
                                }
                            }

                            IconFAB(
                                icon = Icons.Filled.KeyboardArrowRight,
                                func = {
                                    viewModel.nextExercise()
                                    if (viewModel.uiState.execFinished) {
                                        finish()
                                    }
                                },
                                modifier = Modifier
                                    .size(100.dp)
                                    .offset(x = 240.dp),
                                Tertiary,
                                Secondary
                            )
                        }
                    }

                }
            }

            if (viewModel.hasNextExercise()) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        modifier = Modifier
                            .height(120.dp)
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
                                viewModel.uiState.nextExecExercise!!.exercise.name,
                                fontSize = 35.sp,
                                fontWeight = FontWeight.Bold,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            } else
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp), color = Color.Transparent
                )
        }
    }
}

@Composable
private fun showLandscapeLayout(routineTitle: String,prev: () -> Unit, finish: () -> Unit, viewModel: MainViewModel) {
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
                    prev,
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
                    progress = (viewModel.uiState.exerciseCount.toFloat() / viewModel.uiState.routineSize.toFloat())
                )
                Row {
                    Spacer(modifier = Modifier.width((viewModel.uiState.exerciseCount.toFloat() / viewModel.uiState.routineSize.toFloat()) * 165.dp))            // MMM MEDIO DUDOSO
                    Text(
                        "${((viewModel.uiState.exerciseCount.toFloat() / viewModel.uiState.routineSize.toFloat()) * 100).toInt()}%",
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
                        .fillMaxWidth(0.8f)
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
                                    text = viewModel.uiState.currentExecSeries!!.cycleName,
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
                                        "${viewModel.uiState.seriesRepCount} / ${viewModel.uiState.currentExecSeries!!.cycleRepetitions}",
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
                                    .fillMaxWidth(0.5f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    viewModel.uiState.currentExecExercise!!.exercise.name,
                                    fontSize = 35.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(20.dp),
                                    overflow = TextOverflow.Ellipsis
                                )

                                Card(
                                    backgroundColor = Tertiary,
                                    elevation = 20.dp,
                                ) {
                                    if (viewModel.uiState.currentExecExercise!!.duration != 0)
                                        Text(
                                            "${viewModel.uiState.currentTimeExercise} ${stringResource(R.string.exec_seconds)}" ,
                                            fontSize = 35.sp,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(
                                                start = 20.dp,
                                                end = 20.dp,
                                                top = 10.dp,
                                                bottom = 10.dp
                                            )
                                        )
                                    else
                                        Text(
                                            "${viewModel.uiState.currentExecExercise!!.repetitions} ${stringResource(R.string.exec_reps)}",
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
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                ) {
                                    if (!viewModel.isFirstExercise()) {
                                        IconFAB(
                                            icon = Icons.Filled.KeyboardArrowLeft,
                                            func = { viewModel.previousExercise() },
                                            modifier = Modifier
                                                .size(75.dp)
                                                .offset(x = 0.dp),
                                            Tertiary,
                                            Secondary
                                        )
                                    }


                                    if (viewModel.canPauseExecution()) {
                                        if (viewModel.uiState.pausedExec) {
                                            IconFAB(
                                                icon = Icons.Filled.PlayArrow,
                                                func = { viewModel.unpauseExecution() },
                                                modifier = Modifier
                                                    .size(75.dp)
                                                    .offset(x = 85.dp),
                                                Tertiary,
                                                Secondary
                                            )
                                        } else {
                                            IconFAB(
                                                icon = ImageVector.vectorResource(R.drawable.pause_icons),
                                                func = { viewModel.pauseExecution() },
                                                modifier = Modifier
                                                    .size(75.dp)
                                                    .offset(x = 85.dp),
                                                Tertiary,
                                                Secondary
                                            )
                                        }
                                    }

                                    IconFAB(
                                        icon = Icons.Filled.KeyboardArrowRight,
                                        func = {
                                            viewModel.nextExercise()
                                            if (viewModel.uiState.execFinished) {
                                                finish()
                                            }
                                        },
                                        modifier = Modifier
                                            .size(75.dp)
                                            .offset(x = 170.dp),
                                        Tertiary,
                                        Secondary
                                    )
                                }

//                                }
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
                            .fillMaxWidth(0.9f)
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
                                        fontWeight = FontWeight.Bold,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }

                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Quaternary),
                            ) {
                                Text(
                                    text = viewModel.uiState.currentExecExercise!!.exercise.detail!!,
                                    fontSize = 18.sp,
                                    modifier = Modifier.padding(20.dp)
                                )
                            }
                        }
                    }
                    if (viewModel.hasNextExercise()) {
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
                                    modifier = Modifier.padding(
                                        start = 20.dp,
                                        top = 2.dp,
                                        bottom = 5.dp
                                    )
                                ) {

                                    Text(
                                        stringResource(id = R.string.exec_next),
                                    )

                                    Text(
                                        viewModel.uiState.nextExecExercise!!.exercise.name,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }
                    } else
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp), color = Color.Transparent
                        )
                }
            }
        }
    }
}