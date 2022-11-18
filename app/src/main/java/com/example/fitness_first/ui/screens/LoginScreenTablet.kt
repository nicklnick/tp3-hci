package com.example.fitness_first.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitness_first.MainViewModel
import com.example.fitness_first.R
import com.example.fitness_first.ui.components.*
import com.example.fitness_first.ui.theme.Quaternary

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreenTablet(backFunc: () -> Unit, loginFunc: () -> Unit, viewModel: MainViewModel) {

    var user by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        snackbarHost = { SnackbarHost(it) { data -> ErrorSnackBar(data = data) } },
    ) {
        Image(
            painter = painterResource(id = R.drawable.bk7),
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(),
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(420.dp)
                    .padding(top = 10.dp)
            )
            Card(
                modifier = Modifier
                    .padding(bottom = 50.dp)
                    .height(600.dp)
                    .width(700.dp),
                shape = RoundedCornerShape(40.dp),
                backgroundColor = Quaternary,
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(start = 40.dp, end = 40.dp, top = 10.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = stringResource(R.string.login_title),
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.DarkGray
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        GenericInputField(
                            label = stringResource(R.string.login_user),
                            value = user,
                            { user = it },
                            true
                        )

                        GenericInputField(
                            label = stringResource(R.string.login_password),
                            value = password,
                            { password = it },
                            false
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .fillMaxHeight()
                    ) {
                        val msgApiError = stringResource(R.string.err_api)
                        val msgUserOrPasswError = stringResource(R.string.err_user_or_passw)
                        GenericLongButton(
                            stringResource(R.string.login_continue), {
                                viewModel.login(user, password,
                                    {
                                        viewModel.setupViewModel()
                                        loginFunc()
                                    },
                                    {
                                        if (it == "Connection error")
                                            scaffoldState.snackbarHostState.showSnackbar(msgApiError)

                                        if (it == "Invalid username or password")
                                            scaffoldState.snackbarHostState.showSnackbar(msgUserOrPasswError)
                                    }
                                )
                            },
                            !viewModel.uiState.isFetching
                        )

                    }
                }
            }
        }
    }
}
