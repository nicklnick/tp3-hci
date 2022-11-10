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
import com.example.fitness_first.ui.theme.FitnessfirstTheme
import com.example.fitness_first.ui.theme.Tertiary

class NavItem(
    val name: String,
    val icon: ImageVector,
    val click: () -> Unit,  // event that will occur when clicked (navigation)
)

@OptIn(ExperimentalMaterialApi::class)          // EEH!?!?
@Composable
fun NavigationDrawer(nav_items: List<NavItem>) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Card(
            backgroundColor = Tertiary,
            modifier = Modifier.size(100.dp).padding(6.dp),
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
            items(nav_items){ item ->
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

@Preview(showBackground = true)
@Composable
fun LandingScreenPreview() {
    FitnessfirstTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            NavigationDrawer(nav_items = listOf(
                NavItem("My Profile", Icons.Filled.AccountCircle,{}),
                NavItem("Favorites", Icons.Filled.Favorite,{}),
                NavItem("Settings", Icons.Filled.Settings,{}),
                NavItem("Help", Icons.Filled.Info,{}),
                NavItem("Sign Out", Icons.Filled.ExitToApp,{}),
            ))
        }
    }
}
