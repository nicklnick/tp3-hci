package com.example.fitness_first.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
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
        modifier = Modifier
            .height(128.dp)
            .fillMaxWidth(),
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),

            ) {
                IconButton(onClick = menuFunc) {
                    Icon(
                        Icons.Filled.Menu,
                        contentDescription = "menu",
                        tint = Secondary,
                        modifier = Modifier.size(38.dp)
                    )
                }
                OutlinedButton(
                    modifier = Modifier.size(50.dp),
                    onClick = { navController.navigate("profile") },
                    shape = CircleShape,
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(
                        Icons.Filled.Person,
                        contentDescription = "settings",
                        tint = Secondary,
                        modifier = Modifier.size(38.dp)
                    )
                }

            }
            Divider(color = Color.Gray, thickness = 2.dp, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 10.dp))
            SearchBar(navController)
        }
    }
}