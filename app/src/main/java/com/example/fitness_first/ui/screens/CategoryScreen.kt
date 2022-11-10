import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitness_first.ui.theme.Secondary
import java.util.Locale.Category

@Composable
fun CategoryScreen(muscle: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Column(

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

//@Composable
//@Preview
//fun CategoryScreenPreview() {
//    CategoryScreen()
//}