package com.example.fitness_first.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fitness_first.ui.theme.Secondary

@Composable
fun TopBarWFilter(onClickMenu:() -> Unit, onClickFilter: () -> Unit){
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
                IconButton(onClick = onClickMenu) {
                    Icon(
                        Icons.Filled.Menu,
                        contentDescription = "menu",
                        tint = Secondary,
                        modifier = Modifier.size(38.dp)
                    )
                }
                IconButton({/* TODO */}) {
                    Icon(
                        Icons.Filled.Person,
                        contentDescription = "settings",
                        tint = Secondary,
                        modifier = Modifier.size(38.dp)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchBar()
                IconFAB(
                    icon = Icons.Filled.List,
                    func = onClickFilter,
                    modifier = Modifier.size(50.dp),
                    backgroundColor = Secondary,
                    contentColor = Color.Black
                )
            }
        }
    }
}
