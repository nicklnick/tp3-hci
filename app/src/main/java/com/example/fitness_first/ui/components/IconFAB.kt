package com.example.fitness_first

import android.widget.Toast
import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.fitness_first.ui.theme.Primary
import com.example.fitness_first.ui.theme.Quaternary

@Composable
fun IconFAB(icon: ImageVector, func: () -> Unit){
    val ctx = LocalContext.current
    FloatingActionButton(
        onClick = { Toast.makeText(ctx, "test", Toast.LENGTH_SHORT).show()}, // remove!!
        backgroundColor = Quaternary,
        contentColor = Primary
    ) {
        Icon(
            imageVector = icon,
            "Go back",
            Modifier.size(40.dp),
        )
    }
}