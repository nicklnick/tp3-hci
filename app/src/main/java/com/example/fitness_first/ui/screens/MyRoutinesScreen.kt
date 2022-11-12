package com.example.fitness_first.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fitness_first.R
import com.example.fitness_first.ui.components.BottomBar
import com.example.fitness_first.ui.components.DetailedRoutineButton
import com.example.fitness_first.ui.components.NavigationDrawer
import com.example.fitness_first.ui.components.TopBarWFilter
import com.example.fitness_first.ui.theme.Quaternary
import com.example.fitness_first.ui.theme.Secondary
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class BasicRoutineData
constructor(name: String, category: String, liked: Boolean, func: (route: String) -> Unit = {}) {
    val name = name
    val category = category
    val liked = liked
    val func = func
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyRoutinesScreen(
    NavigateToRoutineDetails: (route: String) -> Unit,
    routineData: List<BasicRoutineData>,
    navController: NavHostController
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


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBarWFilter(
            { scaffoldScope.launch {
                scaffoldState.drawerState.open()
            }},
            onClickFilter = { showFilters(scope = scope, sheetState = sheetState)}
        ) },
        bottomBar = { BottomBar(navController = navController) },
        drawerContent = { NavigationDrawer(navController) },
    ){
        BottomSheetScaffold(
            scaffoldState = bottomScaffoldState,
            sheetContent = { sortSheet() },
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
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(space = 8.dp)
                    ) {
                        items(routineData) {
                        /* TODO: aca deberias poder hacer detailedRoutineButton(data) */
                            data -> DetailedRoutineButton(
                                name = data.name,
                                category = data.category,
                                liked = data.liked,
                                func = { NavigateToRoutineDetails(data.name) } ,
                            ) { }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun sortSheet() {
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
                stringResource(R.string.date_up),
                stringResource(R.string.date_down),
                stringResource(R.string.rating_up),
                stringResource(R.string.rating_down),
                stringResource(R.string.diff_up),
                stringResource(R.string.diff_down),
            )
            for (filter in filters) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(text = filter, fontSize = 24.sp)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
private fun showFilters(scope: CoroutineScope, sheetState: BottomSheetState) {
    scope.launch {
        if (sheetState.isCollapsed) {
            sheetState.expand()
        } else {
            sheetState.collapse()
        }
    }
}
