import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fitness_first.ui.components.BottomBar
import com.example.fitness_first.ui.components.NavigationDrawer
import com.example.fitness_first.ui.components.topBar
import com.example.fitness_first.ui.theme.Secondary
import kotlinx.coroutines.launch

@Composable
fun CategoryScreen(
    muscle: String,
    navController: NavHostController
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {topBar {
            scope.launch {
                scaffoldState.drawerState.open()
            }
        }
        },
        bottomBar = { BottomBar(navController = navController) },
        scaffoldState = scaffoldState,
        drawerContent = { NavigationDrawer(navController) },
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
        ) {
            Column {
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