package com.example.fitness_first.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.fitness_first.ui.theme.Primary
import com.example.fitness_first.ui.theme.Quaternary

@Composable
fun IconFAB(icon: ImageVector, func: () -> Unit, modifier: Modifier, backgroundColor: Color, contentColor: Color){
    FloatingActionButton(
        onClick = func,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        modifier = modifier
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(40.dp),
        )
    }
}