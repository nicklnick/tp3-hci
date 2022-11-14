package com.example.fitness_first.ui.components

import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.fitness_first.MainViewModel
import com.example.fitness_first.R
import com.example.fitness_first.ui.theme.Primary
import com.example.fitness_first.ui.theme.Secondary

@Composable
fun topBar(
    menuFunc: () -> Unit,
    navController: NavHostController,
    viewModel: MainViewModel,
    NavigateToAllRoutinesScreen: () -> Unit
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
            Box(modifier = Modifier.fillMaxWidth()){
                Image(
                    painter = painterResource(id = R.drawable.tp_bkg1),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxWidth()
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
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
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ){
                SearchBar(navController, viewModel)
                Button(
                    shape = CircleShape,
                    modifier = Modifier.size(50.dp),
                    onClick = NavigateToAllRoutinesScreen
                ){
                    Text(text = stringResource(R.string.seeAll), fontSize = 9.sp, color = Color.Black)
                }
            }

        }
    }
}