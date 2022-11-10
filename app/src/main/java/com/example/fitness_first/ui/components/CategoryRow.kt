import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.fitness_first.ui.components.Categories
import com.example.fitness_first.ui.components.CategoryCard

@Composable
fun CategoryRow(
    categories: List<Categories>,
    NavigateToCategoryScreen: (route: String) -> Unit
){
    LazyRow(
        contentPadding = PaddingValues(
            start = 16.dp,
            top = 12.dp,
            end = 6.dp,
            bottom = 32.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(categories){ item ->
            CategoryCard(
                text = stringResource(item.title),
                icon = painterResource(item.icon),
                func = {NavigateToCategoryScreen(item.route)},
            )
        }
    }
}