package com.example.fitness_first.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.fitness_first.ui.theme.Primary

/* TODO: Add behaviour */
/* From: https://www.jetpackcompose.app/snippets/RatingBar */
@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Int = 0,
    starsColor: Color = Primary,
    clickable: Boolean = false
) {
    val stars = 5

    val filledStars = rating
    val unfilledStars = (stars - rating)
    var id = 1

    Column() {
        Row(modifier = modifier) {
            repeat(filledStars) {
                if (clickable) {
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = null,
                        tint = starsColor,
                        modifier = Modifier.clickable { fillStars(id) }
                    )
                    id += 1
                } else {
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = null,
                        tint = starsColor
                    )
                }
            }
            id = 1
            repeat(unfilledStars) {
                if (clickable) {
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = null,
                        tint = Color.DarkGray,
                        modifier = Modifier.clickable { fillStars(id) }
                    )
                    id += 1
                } else {
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = null,
                        tint = Color.DarkGray
                    )

                }
            }
        }
    }
}

fun fillStars(id: Int){

}
