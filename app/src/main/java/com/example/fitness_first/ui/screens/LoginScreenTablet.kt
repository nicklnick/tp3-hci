package com.example.fitness_first.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.Typeface
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Device
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitness_first.MainViewModel
import com.example.fitness_first.R
import com.example.fitness_first.data.model.Category
import com.example.fitness_first.ui.components.*
import com.example.fitness_first.ui.theme.FitnessfirstTheme
import com.example.fitness_first.ui.theme.Primary
import com.example.fitness_first.ui.theme.Quaternary
import com.example.fitness_first.util.getViewModelFactory

@Composable
fun LoginScreenTablet(backFunc: () -> Unit, loginFunc: () -> Unit) {

    loginFunc()

    var user by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.bk7),
            contentDescription = null,
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
                    .padding(bottom= 50.dp)
                    .height(550.dp).width(700.dp),
                shape = RoundedCornerShape(40.dp),
                backgroundColor = Quaternary,
                border = BorderStroke(4.dp, Primary)
            )  {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(horizontal = 40.dp)

                ) {
                    Text(
                        text = stringResource(R.string.login_title),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray
                    )
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier

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
                    GenericLongButton(stringResource(R.string.login_continue)){}
                    }
                }
        }
    }
}

@Preview(device= Devices.NEXUS_10)
@Composable
fun PreviewLoginScreenTablet() {
    LoginScreenTablet(backFunc = { /*TODO*/ }, loginFunc = { /*TODO*/ })
}
