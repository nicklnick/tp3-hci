package com.example.fitness_first.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitness_first.ui.theme.*

@Composable
fun CategoryCard(text: String, icon: Painter, func: ()->Unit = {}){
    Button(
        onClick = func,
        modifier = Modifier
            .width(140.dp)
            .height(110.dp)
            .padding(0.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.DarkGray,
            backgroundColor = Quaternary
        ),
        border = BorderStroke(2.dp, Primary),
        contentPadding = PaddingValues(0.dp)

    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = func,
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp)
                    .padding(0.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.DarkGray,
                    backgroundColor = Quaternary
                ),
                border = BorderStroke(2.dp, Primary),
                contentPadding = PaddingValues(0.dp),

            ){
                Icon(icon,"My Profile", modifier = Modifier.size(35.dp))
            }
            Text(
                text,
                fontSize = 24.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(0.dp)
            )
        }

    }
}
//
//@Composable
//@Preview
//fun CategoryCardPreview() {
//    FitnessfirstTheme() {
//        CategoryCard(text = "hola", icon = Icons.Filled.Person)
//    }
//}