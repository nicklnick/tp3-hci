package com.example.fitness_first.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import com.example.fitness_first.ui.components.*
import com.example.fitness_first.ui.theme.*
import kotlin.math.roundToInt

/* Credits: https://proandroiddev.com/how-to-master-swipeable-and-nestedscroll-modifiers-in-compose-bb0635d6a760 */
enum class States {
    EXPANDED,
    COLLAPSED
}

@ExperimentalMaterialApi
@Composable
fun FullHeightBottomSheet() {
    val swipeableState = rememberSwipeableState(initialValue = States.EXPANDED)
    val scrollState = rememberScrollState()

    BoxWithConstraints {
        val constraintsScope = this
        val maxHeight = with(LocalDensity.current) {
            constraintsScope.maxHeight.toPx()
        }

        val connection = remember {
            object : NestedScrollConnection {

                override fun onPreScroll(
                    available: Offset,
                    source: NestedScrollSource
                ): Offset {
                    val delta = available.y
                    return if (delta < 0) {
                        swipeableState.performDrag(delta).toOffset()
                    } else {
                        Offset.Zero
                    }
                }

                override fun onPostScroll(
                    consumed: Offset,
                    available: Offset,
                    source: NestedScrollSource
                ): Offset {
                    val delta = available.y
                    return swipeableState.performDrag(delta).toOffset()
                }

                override suspend fun onPreFling(available: Velocity): Velocity {
                    return if (available.y < 0 && scrollState.value == 0) {
                        swipeableState.performFling(available.y)
                        available
                    } else {
                        Velocity.Zero
                    }
                }

                override suspend fun onPostFling(
                    consumed: Velocity,
                    available: Velocity
                ): Velocity {
                    swipeableState.performFling(velocity = available.y)
                    return super.onPostFling(consumed, available)
                }

                private fun Float.toOffset() = Offset(0f, this)
            }
        }

        Box(
            Modifier
                .swipeable(
                    state = swipeableState,
                    orientation = Orientation.Vertical,
                    anchors = mapOf(
                        0f to States.EXPANDED,
                        maxHeight to States.COLLAPSED,
                    )
                )
                .nestedScroll(connection)
                .offset {
                    IntOffset(
                        0,
                        swipeableState.offset.value.roundToInt()
                    )
                }
        ) {
            Column(
                Modifier
                    .fillMaxHeight()
            ) {
                Scaffold(
                    topBar = {TopBarRoutineDetails("arms", 3.5f, 30)},
                    modifier = Modifier.background(Secondary)
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
                            /**
                             * TODO: Aca tendriamos que poner las series card list de la rutina que clickearon
                             */
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun TopBarRoutineDetails(title: String, difficulty: Float, duration: Int) {
    Column(
        modifier = Modifier.background(Secondary)
    ){
        Spacer(modifier = Modifier.padding(top = 8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            SimpleChip("Difficulty: $difficulty", Tertiary)
            SimpleChip("$duration'", Tertiary)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconFAB(
                icon = Icons.Default.Share,
                func = { shareRoutine() },
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

            IconFAB(
                icon = Icons.Default.Favorite,
                func = { favRoutine() },
                modifier = Modifier,
                Quaternary,
                Primary
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RatingBar()
        }
        Spacer(modifier = Modifier.padding(bottom = 4.dp))
    }
}

private fun shareRoutine() {
    // TODO: hacer!
}

private fun favRoutine() {
    // TODO: hacer!
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun RoutineDetailsScreen() {
    FitnessfirstTheme {
        FullHeightBottomSheet()
    }
}