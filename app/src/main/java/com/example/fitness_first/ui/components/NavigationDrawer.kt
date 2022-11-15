package com.example.fitness_first.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fitness_first.MainViewModel
import com.example.fitness_first.ui.theme.FitnessfirstTheme
import com.example.fitness_first.ui.theme.Secondary
import com.example.fitness_first.ui.theme.Tertiary

class NavItem(
    val name: String,
    val icon: ImageVector,
    val click: () -> Unit,  // event that will occur when clicked (navigation)
)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NavigationDrawer(
    navController: NavController,
    viewModel: MainViewModel
) {
    Card(
        modifier =  Modifier.fillMaxSize(),
        backgroundColor = Secondary
    ){

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(),
        ){
            Card(
                backgroundColor = Tertiary,
                modifier = Modifier
                    .size(100.dp)
                    .padding(6.dp),
                contentColor = Color.DarkGray,
                shape = CircleShape,
            ){
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Account",
                    modifier = Modifier.size(50.dp)
                )
            }
            LazyColumn(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(
                    listOf(
                        NavItem("My Profile", Icons.Filled.AccountCircle) {
                            viewModel.getCurrentUser()
                            navController.navigate("profile")
                        },
                        NavItem("My Routines", Icons.Filled.DateRange) { navController.navigate("routines") },
                        NavItem("Favorites", Icons.Filled.Favorite) { navController.navigate("favourites") },
                        NavItem("Settings", Icons.Filled.Settings) { navController.navigate("settings") },
                        NavItem("Help", Icons.Filled.Info) { navController.navigate("help") },
                        NavItem("Sign Out", Icons.Filled.ExitToApp
                        ) {
                            viewModel.logout { navController.navigate("landing") }
                        },
                    )
                ){ item ->
                    Card(
                        backgroundColor = Tertiary,
                        onClick = item.click,
                        modifier = Modifier
                            .width(220.dp)
                            .height(70.dp)
                            .padding(6.dp),
                        contentColor = Color.DarkGray,
                        shape = RoundedCornerShape(15.dp),
                    ){
                        Row(
                            modifier = Modifier.padding(start = 15.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ){
                            Icon(
                                imageVector = item.icon,
                                item.name,
                                Modifier
                                    .size(40.dp)
                                    .padding(end = 10.dp),
                            )
                            Text(
                                item.name,
                                fontSize = 22.sp
                            )
                        }
                    }
                }
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun LandingScreenPreview() {
    FitnessfirstTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            //NavigationDrawer(NavController())
        }
    }
}
