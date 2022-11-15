import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fitness_first.MainViewModel
import com.example.fitness_first.R
import com.example.fitness_first.ui.components.*
import com.example.fitness_first.ui.theme.Secondary
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    NavigateToCategoryScreen: (route: String) -> Unit,
    navController: NavHostController,
    viewModel: MainViewModel,
    NavigateToAllRoutinesScreen: () -> Unit
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
            bottomBar = { BottomBar(navController = navController) },
            scaffoldState = scaffoldState,
            drawerContent = { NavigationDrawer(navController, viewModel)},
            backgroundColor = Color.Transparent
            ){
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column {
                    Text(
                        text = "Categories",
                        fontSize = MaterialTheme.typography.h5.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = Secondary,
                        modifier = Modifier.padding(start = 20.dp, top = 5.dp)
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
                        text = "Recent",
                        fontSize = MaterialTheme.typography.h5.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = Secondary,
                        modifier = Modifier.padding(start = 20.dp)
                    )
                    Text(
                        text = "Favourites",
                        fontSize = MaterialTheme.typography.h5.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = Secondary,
                        modifier = Modifier.padding(start = 20.dp)
                    )
//                    GenericSmallButton(
//                        label = "go to review",
//                        clickEvent = {
//                            viewModel.getCurrentUser()
//                            viewModel.getReviews(7)
//                            navController.navigate("routine/7/review")
//                        }
//                    )
                }
            }
        }
    }
}