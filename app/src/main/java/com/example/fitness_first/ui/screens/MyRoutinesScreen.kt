package com.example.fitness_first.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.navigation.NavHostController
import com.example.fitness_first.MainViewModel
import com.example.fitness_first.R
import com.example.fitness_first.ui.components.*
import com.example.fitness_first.ui.theme.Quaternary
import com.example.fitness_first.ui.theme.Secondary
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyRoutinesScreen(
    NavigateToRoutineDetails: (route: String) -> Unit,
    navController: NavHostController,
    viewModel: MainViewModel
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )
    val bottomScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )
    val scaffoldScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    Box(){
//        Image(
//            painter = painterResource(id = R.drawable.bkg5),
//            contentDescription = null,
//            contentScale = ContentScale.FillBounds,
//            modifier = Modifier.fillMaxSize()
//        )

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { TopBarWFilter(
                { scaffoldScope.launch {
                    scaffoldState.drawerState.open()
                }},
                onClickFilter = { showFilters(scope = scope, sheetState = sheetState)},
                navController,
                viewModel
            ) },
            bottomBar = { BottomBar(navController = navController) },
            drawerContent = { NavigationDrawer(navController,viewModel) },
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
                        text = stringResource(R.string.my_routines),
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
                        val userList = viewModel.uiState.userRoutines.orEmpty()
                        val list = viewModel.uiState.routines.orEmpty()
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(space = 8.dp)
                        ) {
                            items(
                                count = list.size,
                                key = { index ->
                                    list[index].id.toString()
                                }
                            ){ index ->
                                if( userList.find { it.id == list[index].id  } != null){
                                    DetailedRoutineButton(
                                        name = list[index].name.toString(),
                                        category = list[index].category.name.toString(),
                                        liked = true,
                                        func = {
                                            viewModel.getRoutine(list[index].id)

                                            NavigateToRoutineDetails(list[index].id.toString())
                                           },
                                        likeFunc = {viewModel.deleteFavourite(list[index].id)}
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
}

@Composable
public fun sortSheet(
    viewModel: MainViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(start = 8.dp, top = 4.dp)
        ,
        contentAlignment = Alignment.TopStart
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = stringResource(R.string.sort_by),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            }
            val filters = listOf(
                FilterOptions.Date_Up,
                FilterOptions.Date_Down,
                FilterOptions.Rating_Up,
                FilterOptions.Rating_Down,
                FilterOptions.Difficulty_Up,
                FilterOptions.Difficulty_Down,

//                stringResource(R.string.date_up),
//                stringResource(R.string.date_down),
//                stringResource(R.string.rating_up),
//                stringResource(R.string.rating_down),
//                stringResource(R.string.diff_up),
//                stringResource(R.string.diff_down),
            )
            for (filter in filters) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = stringResource(filter.text),
                        fontSize = 24.sp,
                        modifier = Modifier.clickable { viewModel.getRoutinesWFilter(filter.order, filter.dir) }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
public fun showFilters(scope: CoroutineScope, sheetState: BottomSheetState) {
    scope.launch {
        if (sheetState.isCollapsed) {
            sheetState.expand()
        } else {
            sheetState.collapse()
        }
    }
}
