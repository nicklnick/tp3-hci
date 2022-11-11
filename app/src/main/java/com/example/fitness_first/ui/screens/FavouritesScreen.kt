import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.fitness_first.AppNavHost
import com.example.fitness_first.ui.components.BottomBar
import com.example.fitness_first.ui.components.BottomBarScreen
import com.example.fitness_first.ui.components.topBar
import com.example.fitness_first.ui.theme.Secondary

@Composable
fun FavouritesScreen(
    navController: NavHostController
) {
    Scaffold(
        topBar = {topBar()},
        bottomBar = { BottomBar(navController = navController) }
    ){
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
                    text = "Favourites",
                    fontSize = MaterialTheme.typography.h4.fontSize,
                    fontWeight = FontWeight.Bold,
                    color = Secondary,
                    modifier = Modifier.padding(start = 10.dp, top = 5.dp)
                )
            }

        }
    }

}

//@Composable
//@Preview
//fun FavouritesScreenPreview() {
//    FavouritesScreen()
//}