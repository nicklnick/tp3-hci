import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.fitness_first.MainViewModel
import com.example.fitness_first.R
import com.example.fitness_first.ui.components.BottomBar
import com.example.fitness_first.ui.components.DetailedRoutineButton
import com.example.fitness_first.ui.components.NavigationDrawer
import com.example.fitness_first.ui.components.TopBarWFilter
import com.example.fitness_first.ui.screens.showFilters
import com.example.fitness_first.ui.screens.sortSheet
import com.example.fitness_first.ui.theme.Quaternary
import com.example.fitness_first.ui.theme.Secondary
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoryScreen(
    muscle: String,
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
                            text = stringResource(R.string.cat_name,muscle),
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
                        }else{
                            val categoryList = viewModel.uiState.categoryRoutines.orEmpty()
                            val list = viewModel.uiState.routines.orEmpty()
                            val favList = viewModel.uiState.favouriteRoutines.orEmpty()
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
                                    if( categoryList.find { it.id == list[index].id  } != null){
                                        DetailedRoutineButton(
                                            name = list[index].name.toString(),
                                            category = list[index].category.name.toString(),
                                            liked = favList.find { it.id == list[index].id } != null,
                                            func = {
                                                viewModel.getRoutine(list[index].id)
//TODO
//                                                NavigateToRoutineDetails(list[index].id.toString())
                                            },
                                            likeFunc = {
                                                if(favList.find { it.id == list[index].id } == null){
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

}