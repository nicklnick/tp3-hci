import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fitness_first.MainViewModel
import com.example.fitness_first.R
import com.example.fitness_first.ui.components.*
import com.example.fitness_first.ui.screens.LoadingScreen
import com.example.fitness_first.ui.theme.Secondary
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(
    NavigateToCategoryScreen: (route: String) -> Unit,
    navController: NavHostController,
    viewModel: MainViewModel,
    NavigateToRoutineDetails: (route: String) -> Unit,
    checkAirplaneMode: () -> Boolean,
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val uiState = viewModel.uiState


    var checked by remember { mutableStateOf(false) }
    if (!checked) {
        val errorMsg = stringResource(R.string.err_airplanemode)
        if (checkAirplaneMode()) {
            scope.launch {
                scaffoldState.snackbarHostState.showSnackbar(errorMsg)
            }
        }
        checked = true
    }

    Box {
        Scaffold(
            topBar = {
                topBar(
                    {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    },
                    navController,
                    viewModel,
                )
            },
            bottomBar = { BottomBar(navController = navController, viewModel) },
            scaffoldState = scaffoldState,
            drawerContent = { NavigationDrawer(navController, viewModel) },
            backgroundColor = Color.Transparent,
            snackbarHost = { SnackbarHost(it) { data -> ErrorSnackBar(data = data) } },
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column {
                    if (viewModel.uiState.isFetching) {
                        LoadingScreen()
                    } else {
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

                        if (viewModel.uiState.routines.orEmpty().any { it.liked }) {
                            LazyRow(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 5.dp),
//                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                contentPadding = PaddingValues(start = 16.dp, bottom = 32.dp)
                            ) {
                                items(
                                    viewModel.uiState.routines.orEmpty()
                                ) { routine ->
                                    if (routine.liked) {
                                        CompactRoutineCard(
                                            label = routine.name,
                                            clickEvent = {
                                                viewModel.getRoutine(routine.id)
                                                viewModel.getReviews(routine.id)
                                                NavigateToRoutineDetails(routine.id.toString())
                                            },
                                            category = routine.category.name
                                        )
                                    }

                                }
                            }
                        } else {
                            Text(
                                text = stringResource(R.string.no_favourites),
                                fontSize = MaterialTheme.typography.h5.fontSize,
                                fontWeight = FontWeight.Medium,
                                color = Secondary,
                                modifier = Modifier.padding(start = 10.dp, top = 5.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                        Text(
                            text = stringResource(R.string.discover),
                            fontSize = MaterialTheme.typography.h5.fontSize,
                            fontWeight = FontWeight.Bold,
                            color = Secondary,
                            modifier = Modifier.padding(start = 20.dp, bottom = 10.dp)
                        )
                        if (viewModel.uiState.routines.orEmpty().any { !it.fromCUser }) {
                            LazyRow(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 5.dp),
//                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                contentPadding = PaddingValues(start = 16.dp, bottom = 32.dp)

                            ) {
                                items(
                                    viewModel.uiState.routines.orEmpty()
                                ) { routine ->
                                    if (!routine.fromCUser) {
                                        CompactRoutineCard(
                                            label = routine.name,
                                            clickEvent = {
                                                viewModel.getRoutine(routine.id)
                                                viewModel.getReviews(routine.id)
                                                NavigateToRoutineDetails(routine.id.toString())
                                            },
                                            category = routine.category.name
                                        )
                                    }

                                }
                            }
                        } else {
                            Text(
                                text = stringResource(R.string.no_other_user_routines),
                                fontSize = MaterialTheme.typography.h5.fontSize,
                                fontWeight = FontWeight.Medium,
                                color = Secondary,
                                modifier = Modifier.padding(start = 10.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}