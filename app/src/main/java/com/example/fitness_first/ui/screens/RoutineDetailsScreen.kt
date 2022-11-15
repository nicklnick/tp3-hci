package com.example.fitness_first.ui.screens

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.fitness_first.MainViewModel
import com.example.fitness_first.R
import com.example.fitness_first.data.model.Review
import com.example.fitness_first.ui.components.*
import com.example.fitness_first.ui.theme.Primary
import com.example.fitness_first.ui.theme.Quaternary
import com.example.fitness_first.ui.theme.Secondary
import com.example.fitness_first.ui.theme.Tertiary

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RoutineDetailsScreen(id: Int, exec: () -> Unit, viewModel: MainViewModel) {

    if (viewModel.uiState.message != null)
        Text(text = "Routine not found")

    if (viewModel.uiState.isFetching) {
        LoadingScreen(Secondary)
    }
    else {
        if(viewModel.uiState.currentRoutine == null) {
            Row(
               modifier = Modifier.fillMaxSize(),
               verticalAlignment = Alignment.CenterVertically,
               horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Waiting for current routine...")
            }
        }
        else {
            loadRoutineDetails(viewModel,exec)
        }
    }
}


@Composable
fun TopBarRoutineDetails(title: String, difficulty: String, rating: Int, liked: Boolean, likeFunc: () -> Unit, viewModel: MainViewModel) {

    // Sharing routine with intent functionality
    val context = LocalContext.current
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, "https://fitness-first.com/routine/$title")
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)


    Column(
        modifier = Modifier.background(Secondary)
    ) {
        Spacer(modifier = Modifier.padding(top = 12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            SimpleChip("Difficulty: $difficulty", Tertiary)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconFAB(
                icon = Icons.Default.Share,
                func = { context.startActivity(shareIntent) },
                modifier = Modifier,
                Quaternary,
                Primary
            )

            Text(
                text = title,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            val tint by animateColorAsState(
                targetValue = if(liked) Primary else Color.DarkGray
            )
            IconFAB(
                icon = Icons.Default.Favorite,
                func = likeFunc,
                modifier = Modifier,
                backgroundColor = Quaternary,
                contentColor = tint
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RatingBar(
                rating = rating
            )
        }
        Spacer(modifier = Modifier.padding(bottom = 4.dp))
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun loadRoutineDetails(viewModel: MainViewModel, exec: () -> Unit) {
    val scrollState = rememberScrollState()

    // For liking and disliking current routine
    val favList = viewModel.uiState.favouriteRoutines.orEmpty()
    val currentRoutine = viewModel.uiState.currentRoutine

    Column(
        Modifier
            .fillMaxHeight()
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.bkg5),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
            Scaffold(
                topBar = {
                    TopBarRoutineDetails(
                        title = viewModel.uiState.currentRoutine!!.name,
                        difficulty = viewModel.uiState.currentRoutine!!.difficulty!!,
                        rating = getAverageRating(viewModel.uiState.reviews!!),
                        liked = favList.find { it.id == currentRoutine!!.id } != null,
                        likeFunc = {
                            if (favList.find { it.id == currentRoutine!!.id } == null) {
                                viewModel.markFavourite(currentRoutine!!.id)
                            } else {
                                viewModel.deleteFavourite(currentRoutine!!.id)
                            }
                        },
                        viewModel
                    )
                },
                modifier = Modifier.background(Secondary),
                backgroundColor = Color.Transparent
            ) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .background(Secondary)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        for (cycle in viewModel.uiState.cycleDataList) {
                            SeriesCard(
                                title = cycle.cycleName,
                                repetitions = cycle.cycleRepetitions,
                                cycleExerciseList = cycle.cycleExercises
                            )
                        }
                        GenericSmallOutlinedButton("Start!",{
                            viewModel.setupExecution()
                            exec()
                        })
                    }
                }
            }
        }
    }
}

fun getAverageRating(reviews: List<Review>) : Int {
    var sum = 0
    if(reviews.isEmpty())
        return 0
    for(review in reviews) {
        sum += review.score
    }

    return (sum / reviews.size)
}