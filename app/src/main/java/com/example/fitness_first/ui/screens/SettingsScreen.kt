package com.example.fitness_first.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitness_first.MainViewModel
import com.example.fitness_first.R
import com.example.fitness_first.data.model.Review
import com.example.fitness_first.ui.theme.Quaternary
import com.example.fitness_first.ui.theme.Secondary
import com.example.fitness_first.ui.theme.Tertiary
import java.util.stream.IntStream.range



@Composable
fun SettingsScreen(viewModel: MainViewModel) {
    val images = listOf(R.drawable.bb_opt1, R.drawable.bb_opt2, R.drawable.bb_opt3)
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.bkg1),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            Card(
                backgroundColor = Tertiary,
                modifier = Modifier.padding(10.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        backgroundColor = Secondary,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("Bottom Bar:", fontWeight = FontWeight.Bold, fontSize = 30.sp)
                        }
                    }
                    for(i in range(1,4)){
                        Card(
                            backgroundColor = Quaternary,
                            modifier = Modifier.padding(10.dp)
                        ){
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Image(
                                    painter = painterResource(id = images[i -1]) ,
                                    contentDescription = "options",
                                    modifier = Modifier.fillMaxWidth(0.9f),
                                    contentScale = ContentScale.FillWidth
                                )
                                RadioButton(
                                    selected = viewModel.uiState.bottomBarSelected == i,
                                    onClick = { viewModel.reorderBottomNav(i) },
                                    modifier = Modifier.padding(10.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}