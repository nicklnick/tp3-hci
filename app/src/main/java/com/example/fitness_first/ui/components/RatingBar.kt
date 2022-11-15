package com.example.fitness_first.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.fitness_first.ui.theme.Primary
import kotlin.math.ceil
import kotlin.math.floor

/* TODO: Add behaviour */
/* From: https://www.jetpackcompose.app/snippets/RatingBar */
@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Int = 0,
    starsColor: Color = Primary,
) {
    val stars = 5

    val filledStars = rating
    val unfilledStars = (stars - rating)

    Column() {
        Row(modifier = modifier) {
            repeat(filledStars) {
                Icon(imageVector = Icons.Outlined.Star, contentDescription = null, tint = starsColor)
            }

            repeat(unfilledStars) {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = null,
                    tint = Color.DarkGray
                )
            }
        }
    }

}
