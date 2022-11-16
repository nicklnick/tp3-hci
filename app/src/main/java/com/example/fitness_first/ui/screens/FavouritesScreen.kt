import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import com.example.fitness_first.ui.screens.LoadingScreen
import com.example.fitness_first.ui.screens.showFilters
import com.example.fitness_first.ui.screens.sortSheet
import com.example.fitness_first.ui.theme.Quaternary
import com.example.fitness_first.ui.theme.Secondary
import com.example.fitness_first.ui.theme.Tertiary
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FavouritesScreen(
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
                            text = stringResource(R.string.favourites),
                            fontSize = MaterialTheme.typography.h4.fontSize,
                            fontWeight = FontWeight.Bold,
                            color = Secondary,
                            modifier = Modifier.padding(start = 10.dp, top = 5.dp)
                        )
                        if(viewModel.uiState.isFetching){
                            LoadingScreen()
                        }
                        else{
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(0.91f)
                                    .padding(bottom = 5.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
//                                verticalArrangement = Arrangement.spacedBy(space = 8.dp)
                            ){
                                items(
                                    viewModel.uiState.routines.orEmpty()
                                ) { routine ->
                                    if (routine.liked) {
                                        DetailedRoutineButton(
                                            name = routine.name.toString(),
                                            category = routine.category.name.toString(),
                                            liked = routine.liked,
                                            func = {
                                                viewModel.getRoutine(routine.id)
                                                viewModel.getReviews(routine.id)

                                                NavigateToRoutineDetails(routine.id.toString())
                                            },
                                            likeFunc = {
                                                viewModel.likeOrUnlike(routine)
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