package com.example.fitness_first.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.example.fitness_first.ui.components.*
import com.example.fitness_first.ui.theme.Primary
import com.example.fitness_first.ui.theme.Quaternary

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreenPhone(backFunc: () -> Unit, loginFunc: () -> Unit, viewModel: MainViewModel) {

    var user by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        snackbarHost = { SnackbarHost(it) { data -> ErrorSnackBar(data = data) } },
    ) {
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, start = 30.dp)
            ) {
                IconFAB(
                    icon = Icons.Filled.KeyboardArrowLeft,
                    { backFunc() },
                    Modifier.size(80.dp),
                    Quaternary,
                    Primary)
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

                                    if (it == "Invalid username or password" || it == "")
                                        scaffoldState.snackbarHostState.showSnackbar(msgUserOrPasswError)
                                }
                            )
                        },
                        !viewModel.uiState.isFetching
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {

                        if (viewModel.uiState.isFetching) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}