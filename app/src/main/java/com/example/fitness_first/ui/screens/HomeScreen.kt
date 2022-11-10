import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fitness_first.AppNavHost
import com.example.fitness_first.ui.components.BottomBarScreen
import com.example.fitness_first.ui.components.Categories
import com.example.fitness_first.ui.components.CategoryCard
import com.example.fitness_first.ui.components.SearchBar
import com.example.fitness_first.ui.theme.FitnessfirstTheme
import com.example.fitness_first.ui.theme.Quaternary
import com.example.fitness_first.ui.theme.Secondary


@Composable
fun HomeScreen(
    NavigateToCategoryScreen: (route: String) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Column(

        ) {
//            SearchBar()
            Text(
                text = "Categories",
                fontSize = MaterialTheme.typography.h5.fontSize,
                fontWeight = FontWeight.Bold,
                color = Secondary,
                modifier = Modifier.padding(start = 10.dp, top = 5.dp)
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
            )
            Text(
                text = "Recent",
                fontSize = MaterialTheme.typography.h5.fontSize,
                fontWeight = FontWeight.Bold,
                color = Secondary,
                modifier = Modifier.padding(start = 10.dp)
            )
            Text(
                text = "Favourites",
                fontSize = MaterialTheme.typography.h5.fontSize,
                fontWeight = FontWeight.Bold,
                color = Secondary,
                modifier = Modifier.padding(start = 10.dp)

            )
        }

    }
}


@Composable
@Preview
fun HomeScreenPreview() {
    FitnessfirstTheme() {
        HomeScreen({})
    }
}