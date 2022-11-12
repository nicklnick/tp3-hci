package com.example.fitness_first.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.fitness_first.ui.theme.Secondary

@Composable
fun topBar(
    menuFunc: () -> Unit,
    navController: NavHostController
){
    TopAppBar(
        modifier = Modifier.height(128.dp),
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = menuFunc) {
                    Icon(
                        Icons.Filled.Menu,
                        contentDescription = "menu",
                        tint = Secondary,
                        modifier = Modifier.size(38.dp)
                    )
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        Icons.Filled.Person,
                        contentDescription = "settings",
                        tint = Secondary,
                        modifier = Modifier.size(38.dp)
                    )
                }

            }
            SearchBar(navController)
        }
    }
}