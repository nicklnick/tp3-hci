import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fitness_first.MainViewModel
import com.example.fitness_first.R
import com.example.fitness_first.ui.components.*
import com.example.fitness_first.ui.screens.LoadingScreen
import com.example.fitness_first.ui.theme.Secondary
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    NavigateToCategoryScreen: (route: String) -> Unit,
    navController: NavHostController,
    viewModel: MainViewModel,
    NavigateToAllRoutinesScreen: () -> Unit,
    NavigateToRoutineDetails: (route: String) -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val uiState = viewModel.uiState
//    viewModel.getRoutines()


    Box(){
//        Image(
//            painter = painterResource(id = R.drawable.bkg5),
//            contentDescription = null,
//            contentScale = ContentScale.FillBounds,
//            modifier = Modifier.fillMaxSize()
//        )
        Scaffold(
            topBar = {topBar(
                {scope.launch {
                    scaffoldState.drawerState.open()
                }},
                navController,
                viewModel,
                //NavigateToAllRoutinesScreen
            )
            },
            bottomBar = { BottomBar(navController = navController, viewModel) },
            scaffoldState = scaffoldState,
            drawerContent = { NavigationDrawer(navController, viewModel)},
            backgroundColor = Color.Transparent
            ){
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column {
                    if(viewModel.uiState.isFetching){
                        LoadingScreen()
                    }else{
                        Text(
                            text = stringResource(R.string.categories),
                            fontSize = MaterialTheme.typography.h5.fontSize,
                            fontWeight = FontWeight.Bold,
                            color = Secondary,
                            modifier = Modifier.padding(start = 20.dp, top = 30.dp)
                        )
                        CategoryRow(
                            categories = listOf(
                                Categories.Bicep,
                                Categories.Tricep,
                                Categories.Abs,
                                Categories.Chest,
                                Categories.Back,
                                Categories.Legs,
                                Categories.Shoulders,
                                Categories.FullBody
                            ),
                            NavigateToCategoryScreen,
                            viewModel
                        )
                        Text(
                            text = stringResource(R.string.favourites),
                            fontSize = MaterialTheme.typography.h5.fontSize,
                            fontWeight = FontWeight.Bold,
                            color = Secondary,
                            modifier = Modifier.padding(start = 20.dp, bottom = 10.dp)
                        )
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 5.dp),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            items(
                                viewModel.uiState.routines.orEmpty()
                            ){ routine ->
                                if( routine.liked ){
                                    CompactRoutineCard(
                                        label = routine.name,
                                        clickEvent ={
                                            viewModel.getRoutine(routine.id)
                                            viewModel.getReviews(routine.id)
                                            NavigateToRoutineDetails(routine.id.toString())
                                        },
                                        category = routine.category.name
                                    )
                                }

                            }
                        }
                        Text(
                            text = stringResource(R.string.discover),
                            fontSize = MaterialTheme.typography.h5.fontSize,
                            fontWeight = FontWeight.Bold,
                            color = Secondary,
                            modifier = Modifier.padding(start = 20.dp, bottom = 10.dp, top = 10.dp)
                        )
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 5.dp),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            items(
                                viewModel.uiState.routines.orEmpty()
                            ){ routine ->
                                if( !routine.fromCUser ){
                                    CompactRoutineCard(
                                        label = routine.name,
                                        clickEvent ={
                                            viewModel.getRoutine(routine.id)
                                            viewModel.getReviews(routine.id)
                                            NavigateToRoutineDetails(routine.id.toString())
                                        },
                                        category = routine.category.name
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