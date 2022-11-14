import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        Image(
            painter = painterResource(id = R.drawable.bkg5),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        Scaffold(
            topBar = {topBar(
                {scope.launch {
                    scaffoldState.drawerState.open()
                }},
                navController,
                viewModel
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
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Categories",
                            fontSize = MaterialTheme.typography.h5.fontSize,
                            fontWeight = FontWeight.Bold,
                            color = Secondary,
                            modifier = Modifier.padding(start = 20.dp, top = 5.dp)
                        )
                        Text(
                            text = stringResource(R.string.seeAll),
                            fontSize = 20.sp,
                            color =  Secondary,
                            style = TextStyle(textDecoration = TextDecoration.Underline),
                            modifier = Modifier.clickable { NavigateToAllRoutinesScreen() }.padding(end = 15.dp)

                        )
                    }

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
                }
            }
        }
    }

}