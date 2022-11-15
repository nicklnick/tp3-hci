package com.example.fitness_first.ui.screens

import android.annotation.SuppressLint
import android.os.SystemClock.sleep
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.fitness_first.MainViewModel
import com.example.fitness_first.R
import com.example.fitness_first.ui.components.BottomBar
import com.example.fitness_first.ui.components.DetailedRoutineButton
import com.example.fitness_first.ui.components.NavigationDrawer
import com.example.fitness_first.ui.components.TopBarWFilter
import com.example.fitness_first.ui.theme.Quaternary
import com.example.fitness_first.ui.theme.Secondary
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AllRoutinesScreen(
    navController: NavHostController,
    viewModel: MainViewModel,
    NavigateToRoutineDetails: (route: String) -> Unit
){
    val scope = rememberCoroutineScope()
    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )
    val bottomScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )
    val scaffoldState = rememberScaffoldState()
    Box{
//        Image(
//            painter = painterResource(id = R.drawable.bkg5),
//            contentDescription = null,
//            contentScale = ContentScale.FillBounds,
//            modifier = Modifier.fillMaxSize()
//        )
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { TopBarWFilter(
                {scope.launch {
                    scaffoldState.drawerState.open()
                }},
                onClickFilter = { showFilters(scope = scope, sheetState = sheetState) },
                navController,
                viewModel
            )
            },
            bottomBar = { BottomBar(navController = navController) },
            drawerContent = { NavigationDrawer(navController, viewModel) },
            backgroundColor = Color.Transparent
        ){
            BottomSheetScaffold(
                scaffoldState = bottomScaffoldState,
                sheetContent = { sortSheet(viewModel) },
                sheetBackgroundColor = Quaternary,
                sheetPeekHeight = 0.dp
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.allRoutines),
                            fontSize = MaterialTheme.typography.h4.fontSize,
                            fontWeight = FontWeight.Bold,
                            color = Secondary,
                            modifier = Modifier.padding(start = 10.dp, top = 5.dp)
                        )
                        if(viewModel.uiState.isFetching){
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Loading...",
                                    fontSize = 16.sp
                                )
                            }
                        }
                        else{
                            val list = viewModel.uiState.routines.orEmpty()
                            val favList = viewModel.uiState.favouriteRoutines.orEmpty()
                            LazyColumn(
                                modifier = Modifier.fillMaxWidth().fillMaxHeight(0.91f).padding(bottom = 5.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(space = 8.dp)
                            ){
                                items(
                                    count = list.size,
                                    key = { index ->
                                        list[index].id.toString()
                                    }
                                ){
                                        index -> DetailedRoutineButton(
                                    name = list[index].name.toString(),
                                    category = list[index].category.name.toString(),
                                    liked = favList.find { list[index].id == it.id  } != null,
                                    func = {
                                        viewModel.getRoutine(list[index].id)
                                        viewModel.getReviews(list[index].id)
                                        NavigateToRoutineDetails(list[index].id.toString())
                                    },
                                    likeFunc = {
                                        if(favList.find { list[index].id == it.id  } == null){
                                            viewModel.markFavourite(list[index].id)
                                        }else{
                                            viewModel.deleteFavourite(list[index].id)
                                        }
                                    }
                                )
                                }
                            }
                        }
                    }
                }
            }

        }

    }
}