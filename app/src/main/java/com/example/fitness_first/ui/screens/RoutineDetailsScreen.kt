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
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.invalidateGroupsWithKey
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.fitness_first.MainViewModel
import com.example.fitness_first.R
import com.example.fitness_first.ui.components.*
import com.example.fitness_first.ui.theme.Primary
import com.example.fitness_first.ui.theme.Quaternary
import com.example.fitness_first.ui.theme.Secondary
import com.example.fitness_first.ui.theme.Tertiary

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RoutineDetailsScreen(id: Int, exec: () -> Unit, viewModel: MainViewModel, navController: NavController) {

    if(!viewModel.uiState.isAuthenticated){
        NotSignedInScreen {
            navController.navigate("login")
        }
    }
    else if(viewModel.uiState.currentRoutine == null){
        if (viewModel.uiState.isFetching){
            LoadingScreen(Secondary)
        }
        else if (viewModel.uiState.message != null)
            RoutineNotFound()
        else{
            viewModel.getRoutine(id)
        }
    }
    else {
        loadRoutineDetails(viewModel,exec, id, navController)
    }
}


@Composable
fun TopBarRoutineDetails(title: String, difficulty: String, rating: Int, liked: Boolean, likeFunc: () -> Unit, id:Int, navController: NavController) {

    // Sharing routine with intent functionality
    val context = LocalContext.current
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, "https://fitness-first.com/routine/$id")
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)


    Column(
        modifier = Modifier.background(Secondary)
    ) {
        Spacer(modifier = Modifier.padding(top = 12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconFAB(
                icon = Icons.Filled.KeyboardArrowLeft,
                func = {
                    if(!navController.popBackStack())
                        navController.navigate(NavItem.Home.route)
                },
                modifier = Modifier.size(40.dp),
                backgroundColor = Quaternary,
                contentColor = Primary
            )

            SimpleChip(stringResource(R.string.difficulty) + difficulty, Tertiary)

            Spacer(Modifier.size(40.dp))
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
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
                targetValue = if (liked) Primary else Color.DarkGray
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
fun loadRoutineDetails(viewModel: MainViewModel, exec: () -> Unit, id: Int, navController: NavController) {
    val scrollState = rememberScrollState()

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
                        rating = if (viewModel.uiState.currentRoutine!!.score == null) 0 else viewModel.uiState.currentRoutine!!.score!!,
                        liked = viewModel.uiState.currentRoutine!!.liked,
                        likeFunc = {
                            viewModel.likeOrUnlike(viewModel.uiState.currentRoutine!!)
                            viewModel.uiState.routines!!.find { viewModel.uiState.currentRoutine!!.id == it.id }!!.liked =
                                viewModel.uiState.currentRoutine!!.liked
                        },
                        id,
                        navController
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
                        if (viewModel.uiState.cycleDataList.isNotEmpty() && viewModel.uiState.cycleDataList.all { it.cycleExercises.isNotEmpty() }) {
                            GenericSmallOutlinedButton(stringResource(id = R.string.routDetails_start),
                                {
                                    viewModel.setupExecution()
                                    exec()
                                })
                        }
                    }
                }
            }
        }
    }
}
