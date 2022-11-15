package com.example.fitness_first.ui.screens

import android.view.MotionEvent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.fitness_first.MainViewModel
import com.example.fitness_first.R
import com.example.fitness_first.data.model.Review
import com.example.fitness_first.ui.components.GenericSmallButton
import com.example.fitness_first.ui.components.GenericSmallOutlinedButton
import com.example.fitness_first.ui.theme.Secondary

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RoutineReviewScreen(id: Int, func: () -> Unit, viewModel: MainViewModel ) {
    Box(){
        Image(
            painter = painterResource(id = R.drawable.bkg5),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = stringResource(R.string.good_job),
                    fontSize = MaterialTheme.typography.h4.fontSize,
                    fontWeight = FontWeight.Bold,
                    color = Secondary
                )
                Text(
                    text = stringResource(R.string.killed_it),
                    fontSize = MaterialTheme.typography.h4.fontSize,
                    fontWeight = FontWeight.Bold,
                    color = Secondary
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                if(viewModel.uiState.isFetching){
                    LoadingScreen()
                }else{
                    val userRev = viewModel.uiState.reviews!!.find { it.userId!! == viewModel.uiState.currentUser!!.id && it.routineId == viewModel.uiState.currentRoutine!!.id}
                    if( userRev != null){
                        Text(
                            text = stringResource(R.string.review_thank),
                            fontSize = MaterialTheme.typography.h5.fontSize,
                            fontWeight = FontWeight.Medium,
                            color = Secondary
                        )
                        RatingBar2(rating = userRev.score, viewModel = viewModel)
                    }else{
                        Text(
                            text = stringResource(R.string.review_ask),
                            fontSize = MaterialTheme.typography.h5.fontSize,
                            fontWeight = FontWeight.Medium,
                            color = Secondary
                        )
                        RatingBar2(rating = 0, viewModel = viewModel)
                    }
                }
            }
            GenericSmallButton(
                label = stringResource(R.string.go_home),
                clickEvent = func
            )
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun RatingBar2(
    modifier: Modifier = Modifier,
    rating: Int,
    viewModel: MainViewModel
) {
    var ratingState by remember {
        mutableStateOf(rating)
    }

    var selected by remember {
        mutableStateOf(false)
    }
    val size by animateDpAsState(
        targetValue = if (selected) 72.dp else 64.dp,
        spring(Spring.DampingRatioMediumBouncy)
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        if(rating == 0){
            for (i in 1..5) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_round_star_24),
                    contentDescription = "star",
                    modifier = modifier
                        .width(size)
                        .height(size)
                        .pointerInteropFilter {
                            when (it.action) {
                                MotionEvent.ACTION_DOWN -> {
                                    selected = true
                                    ratingState = i
                                }
                                MotionEvent.ACTION_UP -> {
                                    selected = false
                                }
                            }
                            true
                        },
                    tint = if (i <= ratingState) Color(0xFFFFD700) else Color(0xFFA2ADB1)
                )
            }
        }else{
            for (i in 1..5) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_round_star_24),
                    contentDescription = "star",
                    modifier = modifier
                        .width(size)
                        .height(size),
                    tint = if (i <= ratingState) Color(0xFFFFD700) else Color(0xFFA2ADB1)
                )
            }
        }

    }
    GenericSmallOutlinedButton(
        label = stringResource(R.string.publish_review),
        enabled = ratingState > 0 && viewModel.uiState.reviews!!.find { it.userId!! == viewModel.uiState.currentUser!!.id && it.routineId == viewModel.uiState.currentRoutine!!.id} == null,
        clickEvent = { viewModel.addReview(
            viewModel.uiState.currentRoutine!!.id,
            Review(ratingState, viewModel.uiState.currentUser!!.id,viewModel.uiState.currentRoutine!!.id, "")
        )}
//        clickEvent = {
//            viewModel.addReview(
//                7,
//                Review(
//                    ratingState,
//                    1,
//                    7,
//                    ""
//                )
//            )
//        }
    )

}