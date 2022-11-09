import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.fitness_first.ui.theme.FitnessfirstTheme
import com.example.fitness_first.ui.theme.Primary
import com.example.fitness_first.ui.theme.Quaternary
import com.example.fitness_first.ui.theme.Secondary

@Composable
fun SimpleButton(){
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
        shape = CircleShape,
        modifier = Modifier.size(50.dp)
    ) {
        Image(
            painterResource(id = android.R.drawable.ic_menu_sort_by_size),
            contentDescription = "Filter Icon",
        )
    }
}

@Composable
fun content(){
    DropDownMenuWithToggleButton(
        buttonScope = { text, onClick ->
            
            TextButton(
                onClick = onClick,
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = Primary,
                    contentColor = Color.Black
                )

            ) {
                Text(text)
            }

        },
        dropDownMenu = { expanded, dropDownMenuItems, onDropDownMenuItemSelect, onDismissRequest ->
            DropDownMenu( expanded, dropDownMenuItems, onDropDownMenuItemSelect, onDismissRequest )
        }

    )
}

@Composable
fun DropDownMenuWithToggleButton(
    buttonScope: @Composable (text: String, onClick: ()-> Unit) -> Unit,
    dropDownMenu: @Composable (
        expanded: Boolean,
        dropDownMenuItems: List<String>,
        onDropDownMenuItemSelect: (selectedIndex: Int) -> Unit,
        onDismissRequest: () -> Unit
    ) -> Unit
){

    var expanded by remember {
        mutableStateOf(false)
    }

    val items = listOf("Sort","Date Up", "Date Down", "Rating Up", "Rating Down", "Difficulty Up", "Difficulty Down")

    var selectedIndex by remember {
        mutableStateOf(0)
    }
    
    Box{
        buttonScope(
            text = items[selectedIndex],
            onClick = {
                expanded = true
            }
        )

        dropDownMenu(
            expanded = expanded,
            dropDownMenuItems = items,
            onDropDownMenuItemSelect = {
                selectedIndex = it
                expanded = false
            },
            onDismissRequest = {
                expanded = false
            }
        )
    }
}

@Composable
fun DropDownMenu(
    expanded: Boolean,
    dropDownMenuItems: List<String>,
    onDropDownMenuItemSelect: (selectedIndex: Int) -> Unit,
    onDismissRequest: () -> Unit
) {
    DropdownMenu(
        offset = DpOffset(0.dp, 20.dp),
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = Modifier
            .wrapContentSize()
            .background(Color.LightGray)
            .padding(20.dp)
    ) {

        dropDownMenuItems.forEachIndexed { index, text ->
            DropdownMenuItem(
                onClick = { onDropDownMenuItemSelect(index) },
            ) {
                Text(text = text, color = Color.DarkGray)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun defaultPreview(){
    FitnessfirstTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}