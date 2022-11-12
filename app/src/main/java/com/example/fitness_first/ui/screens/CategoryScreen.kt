import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fitness_first.ui.components.BottomBar
import com.example.fitness_first.ui.components.NavigationDrawer
import com.example.fitness_first.ui.components.TopBarWFilter
import com.example.fitness_first.ui.components.topBar
import com.example.fitness_first.ui.screens.showFilters
import com.example.fitness_first.ui.screens.sortSheet
import com.example.fitness_first.ui.theme.Quaternary
import com.example.fitness_first.ui.theme.Secondary
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoryScreen(
    muscle: String,
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
            {scope.launch {
                scaffoldState.drawerState.open()
            }},
            onClickFilter = { showFilters(scope = scope, sheetState = sheetState) },
            navController
        )
        },
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
                        text = "$muscle Routines",
                        fontSize = MaterialTheme.typography.h4.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = Secondary,
                        modifier = Modifier.padding(start = 10.dp, top = 5.dp)
                    )
                }

            }
        }

    }

}