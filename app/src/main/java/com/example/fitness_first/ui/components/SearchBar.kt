package com.example.fitness_first.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.fitness_first.ui.theme.Secondary
import com.example.fitness_first.ui.theme.Tertiary

@Composable
fun SearchBar() {
    var query by remember { mutableStateOf(TextFieldValue("")) }
    TextField(
        modifier = Modifier.padding(1.dp).fillMaxWidth(0.8f),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Tertiary,
            focusedLabelColor = Secondary,
            trailingIconColor = Secondary,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
        ),
        shape = CircleShape,
        trailingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "searchBar") },
        value = query,
        onValueChange = {
            query = it
        },
        label = {
            Text(
                text = "Search",
                modifier = Modifier.padding(horizontal = 5.dp),
                textAlign = TextAlign.Center)
        },
        singleLine = true
    )
}
