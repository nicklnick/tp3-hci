package com.example.fitness_first.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitness_first.ui.theme.LightBlue
import com.example.fitness_first.ui.theme.Quaternary
import com.example.fitness_first.ui.theme.Secondary

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CompactRoutineCard(label : String, clickEvent: () -> Unit, category: String) {
    Row(
        modifier = Modifier.size(width = 150.dp, height = 110.dp).padding(start = 8.dp, end = 8.dp),
    ){
        Card(
            onClick = clickEvent,
            modifier = Modifier.fillMaxSize(),
            backgroundColor = Quaternary,
            border = BorderStroke(2.dp, Secondary),
            shape = RoundedCornerShape(5.dp),
            elevation = 2.dp
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
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
                        Text(
                            text = label,
                            fontWeight = FontWeight.Medium,
                            fontSize = 20.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(start = 10.dp, end = 5.dp)
                        )
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
                        Card(
                            backgroundColor = LightBlue,
                        ) {
                            Text(
                                category,
                                fontSize = 17.sp,
                                modifier = Modifier.padding(2.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
