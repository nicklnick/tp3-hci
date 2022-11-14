package com.example.fitness_first.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.fitness_first.MainViewModel
import com.example.fitness_first.R
import com.example.fitness_first.ui.theme.Secondary

@Composable
fun TopBarWFilter(
    onClickMenu:() -> Unit,
    onClickFilter: () -> Unit,
    navController: NavHostController,
    viewModel: MainViewModel
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
            Box(modifier = Modifier.fillMaxWidth()){
                Image(
                    painter = painterResource(id = R.drawable.tp_bkg1),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxWidth()
                )
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
                    OutlinedButton(
                        modifier = Modifier.size(50.dp),
                        onClick = {
                            viewModel.getCurrentUser()
                            navController.navigate("profile")
                                  },
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
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchBar(navController, viewModel)
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
