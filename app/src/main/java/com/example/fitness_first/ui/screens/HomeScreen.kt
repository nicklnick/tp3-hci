import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitness_first.ui.components.Categories
import com.example.fitness_first.ui.components.CategoryCard
import com.example.fitness_first.ui.components.SearchBar
import com.example.fitness_first.ui.theme.FitnessfirstTheme
import com.example.fitness_first.ui.theme.Secondary


@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column {
            SearchBar()
            Text(
                text = "Categories",
                fontSize = MaterialTheme.typography.h5.fontSize,
                fontWeight = FontWeight.Bold,
                color = Secondary,
            )
//            LazyRow(){
//                items(Categories){ item ->
//                    CategoryCard(text = item., icon = )
//                }
//            }
//            CategoryCard(
//                text = "Bicep",
//                icon = Icons.Filled.Person,
//            )
            CategoryRow(categories = listOf(
                Categories.Bicep,
                Categories.Tricep,
                Categories.Abs,
                Categories.Chest,
                Categories.Back,
                Categories.Legs,
                Categories.Shoulders,
                Categories.FullBody
            ))
            Text(
                text = "Recent",
                fontSize = MaterialTheme.typography.h5.fontSize,
                fontWeight = FontWeight.Bold,
                color = Secondary
            )
            Text(
                text = "Favourites",
                fontSize = MaterialTheme.typography.h5.fontSize,
                fontWeight = FontWeight.Bold,
                color = Secondary
            )
        }

    }
}

@Composable
@Preview
fun HomeScreenPreview() {
    FitnessfirstTheme() {
        HomeScreen()
    }
}