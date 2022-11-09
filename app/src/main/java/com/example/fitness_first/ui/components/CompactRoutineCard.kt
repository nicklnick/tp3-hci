package com.example.fitness_first.ui.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.fitness_first.ui.theme.Primary
import com.example.fitness_first.ui.theme.Quaternary
import com.example.fitness_first.ui.theme.Secondary

/* Event triggered when user clicks the favourite icon */
private fun markAsFavourite(context: Context) {
    Toast.makeText(context, "Favourite!", Toast.LENGTH_SHORT).show()
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CompactRoutineCard(label : String, clickEvent: () -> Unit) {
    Row(
        modifier = Modifier.size(width = 150.dp, height = 110.dp),
    ){
        Card(
            onClick =  clickEvent ,
            modifier = Modifier.size(width = 150.dp, height = 110.dp),
            backgroundColor = Quaternary,
            border = BorderStroke(2.dp, Secondary),
            shape = RoundedCornerShape(5.dp),
            elevation = 2.dp
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ){
                Surface(
                    color = Color.Transparent,
                    modifier = Modifier
                        .width(200.dp)
                        .height(30.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.End
                    ) {
                        val context = LocalContext.current
                        IconButton(
                            onClick = { markAsFavourite(context) },
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = "Favourite Button",
                                tint = Primary,
                            )
                        }
                    }
                }
                Surface(
                    color = Color.Transparent,
                    modifier = Modifier
                        .width(200.dp)
                        .height(30.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = label)
                    }
                }
                Surface(
                    color = Color.Transparent,
                    modifier = Modifier
                        .width(200.dp)
                        .height(30.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RatingBar()
                    }
                }
            }
        }
    }
}
