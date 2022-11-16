package com.example.fitness_first.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitness_first.MainViewModel
import com.example.fitness_first.R
import com.example.fitness_first.data.model.Category
import com.example.fitness_first.ui.components.*
import com.example.fitness_first.ui.theme.Primary
import com.example.fitness_first.ui.theme.Quaternary
import kotlin.math.abs

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(backFunc: () -> Unit, loginFunc: () -> Unit, viewModel: MainViewModel) {

    var user by rememberSaveable { mutableStateOf("")}
    var password by rememberSaveable { mutableStateOf("")}

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        snackbarHost = { SnackbarHost(it){ data -> ErrorSnackBar(data = data) } },

    ){
        Image(
            painter = painterResource(id = R.drawable.bkg3),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxSize(),
        )
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(),
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, start = 30.dp)
            ) {
                IconFAB(icon = Icons.Filled.KeyboardArrowLeft, { backFunc() } , Modifier.size(80.dp), Quaternary, Primary)
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(650.dp),
                shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
                backgroundColor = Quaternary,
                //border = BorderStroke(5.dp, Primary)
            ) {
                Column(
                    modifier = Modifier
                        .width(100.dp)
                        .padding(30.dp)
                ) {
                    Text(
                        text = stringResource(R.string.login_title),
                        fontSize = 50.sp,
                        color = Color.DarkGray
                    )
                    GenericInputField(label = stringResource(R.string.login_user), value = user, {user = it }, true)

                    GenericInputField(label = stringResource(R.string.login_password), value = password, {password = it }, false)

                    val bicepName = stringResource(Categories.Bicep.name)
                    val tricepName = stringResource(Categories.Tricep.name)
                    val chestName = stringResource(Categories.Chest.name)
                    val shouldersName = stringResource(Categories.Shoulders.name)
                    val backName = stringResource(Categories.Back.name)
                    val legsName = stringResource(Categories.Legs.name)
                    val absName = stringResource(Categories.Abs.name)
                    val fullBodyName = stringResource(Categories.FullBody.name)

                    GenericLongButton(stringResource(R.string.login_continue)) {
                        viewModel.login(user,password,{
                            viewModel.addCategory(Category(name = bicepName))
                            viewModel.addCategory(Category(name = tricepName))
                            viewModel.addCategory(Category(name = chestName))
                            viewModel.addCategory(Category(name = shouldersName))
                            viewModel.addCategory(Category(name = backName))
                            viewModel.addCategory(Category(name = legsName))
                            viewModel.addCategory(Category(name = absName))
                            viewModel.addCategory(Category(name = fullBodyName))
                            // TODO: en vez de agregarlos podemos pedir que los tengan agregados de antemano

//                            viewModel.getCategories()
//                            if(!viewModel.uiState.isFetching){
//                                val categories = viewModel.uiState.categories.orEmpty()
//                                val categoriesData = listOf(
//                                    Categories.Bicep,
//                                    Categories.Tricep,
//                                    Categories.Chest,
//                                    Categories.Legs,
//                                    Categories.Abs,
//                                    Categories.Back,
//                                    Categories.FullBody,
//                                    Categories.Shoulders
//                                )
//                                categories.forEach { apiCategory ->
//                                    categoriesData.forEach { dataCategory ->
//                                        if( dataCategory.title.equals(apiCategory.name, true)){
//                                            dataCategory.id = apiCategory.id
//                                            Log.d("name",dataCategory.title)
//                                            Log.d("id value for category", dataCategory.id.toString())
//                                        }
//                                    }
//                                }
//                            }
                            viewModel.getRoutines()
//                            viewModel.getFavourites()
//                            viewModel.getCurrentUserRoutines()

//                            viewModel.uiState.routines.orEmpty().forEach { routine ->
//                                if( viewModel.uiState.favouriteRoutines.orEmpty().find { it.id == routine.id } != null){
//                                    routine.liked = true
//                                }
//                                if( viewModel.uiState.userRoutines.orEmpty().find { it.id == routine.id } != null){
//                                    routine.fromCUser = true
//                                }
//                            }

                            viewModel.getCurrentUser()

                            loginFunc()
                        },
                            {
                                scaffoldState.snackbarHostState.showSnackbar("Invalid username or password.")
                            }
                        )
                    }
                }

            }
        }
    }
}
