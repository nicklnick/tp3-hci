package com.example.fitness_first.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetState
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitness_first.MainViewModel
import com.example.fitness_first.R
import com.example.fitness_first.ui.theme.Secondary
import com.example.fitness_first.ui.theme.Tertiary
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun sortSheet(
    viewModel: MainViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Column{
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                backgroundColor = Secondary,
                contentColor = Color.Black
            ) {
                Text(
                    modifier = Modifier.padding(start = 15.dp),
                    text = stringResource(R.string.sort_by),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                )
            }

            viewModel.uiState.filters.forEachIndexed { index, filter ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(start = 15.dp,end = 15.dp, top= 7.dp,bottom=7.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Card(
                        modifier = Modifier.fillMaxWidth().clickable
                        {
                            viewModel.getRoutinesWFilter(filter.order, filter.dir, index)
                        },
                        backgroundColor = if(viewModel.uiState.orderBy == index) Secondary else Tertiary
                    ) {
                        Text(
                            text = stringResource(filter.text),
                            fontSize = 24.sp,
                            modifier = Modifier.padding(start = 7.dp,  top = 2.dp, bottom = 2.dp)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
fun showFilters(scope: CoroutineScope, sheetState: BottomSheetState) {
    scope.launch {
        if (sheetState.isCollapsed) {
            sheetState.expand()
        } else {
            sheetState.collapse()
        }
    }
}
