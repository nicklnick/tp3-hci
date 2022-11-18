package com.example.fitness_first.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitness_first.ui.theme.*


// - - - - COMO USARLO - - - -
//        var isLiked by remember { mutableStateOf(false) }
//        DetailedRoutineButton("Arms 1","full body",
//        isLiked,
//        {},
//        {
//            isLiked = !isLiked;
//        })
// - - - - - - - - - - - - - - -

@Composable
fun DetailedRoutineButton(name: String, category: String, liked: Boolean, func: ()->Unit, likeFunc: ()->Unit, difficulty: String) {
    OutlinedButton(
        onClick = func,
        modifier = Modifier
            .width(350.dp)
            .height(85.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.DarkGray,
            backgroundColor = Tertiary,
        ),
        border = BorderStroke(2.dp, Primary),
        contentPadding = PaddingValues(0.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Column(
                    modifier = Modifier.absolutePadding(left = 15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        name,
                        fontSize = 24.sp,
                        color = Color.DarkGray,
                        modifier = Modifier.padding(0.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Card(
                            backgroundColor = LightBlue,
                            modifier = Modifier.padding(end = 5.dp)
                        ) {
                            Text(
                                category,
                                fontSize = 17.sp,
                                modifier = Modifier.padding(2.dp)
                            )
                        }
                        var backColor = rookie
                        when (difficulty) {
                            "beginner" -> {
                                backColor = beginner
                            }
                            "intermediate" -> {
                                backColor = intermediate
                            }
                            "advanced" -> {
                                backColor = advanced
                            }
                            "expert" -> {
                                backColor = expert
                            }
                        }
                        Card(
                            backgroundColor = backColor,
                            modifier = Modifier.padding(start = 5.dp)
                        ) {
                            Text(
                                difficulty,
                                fontSize = 17.sp,
                                modifier = Modifier.padding(2.dp)
                            )
                        }
                    }

                }
            }
            val tint by animateColorAsState(
                targetValue = if (liked) Primary else Color.DarkGray
            )
            IconToggleButton(
                checked = liked,
                onCheckedChange = { likeFunc(); },
                modifier = Modifier.padding(end = 10.dp),
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    "Like",
                    modifier = Modifier.size(36.dp),
                    tint = tint,
                )
            }
        }
    }
}
