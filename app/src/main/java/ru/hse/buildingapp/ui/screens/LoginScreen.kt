package ru.hse.buildingapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.hse.buildingapp.R
import ru.hse.buildingapp.robotoFamily
import ru.hse.buildingapp.ui.viewmodels.LoginViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import ru.hse.buildingapp.navigation.NavigationAdapter
import ru.hse.buildingapp.network.authmodels.AuthRespState

object LoginScreen {
        @Composable
        fun View(viewModel: LoginViewModel) {
            var email by remember { mutableStateOf(TextFieldValue("")) }
            var password by remember { mutableStateOf(TextFieldValue("")) }

            Column(
                Modifier
                    .background(Color(0xFFEDEDED), shape = RoundedCornerShape(20.dp))
                    .fillMaxWidth()
                    .height(600.dp)
                    .padding(start = 18.dp, end = 18.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Please insert your login and password",
                    textAlign = TextAlign.Center,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    color = Color(0xFF3C6AB0),
                    modifier = Modifier.padding(top = 24.dp, bottom = 16.dp)
                )
                Column(
                    Modifier
                        .background(Color(0xFFFFFFFF), shape = RoundedCornerShape(20.dp))
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(vertical = 10.dp)
                )
                {
                    RequestTextField(R.drawable.email_field_icon, "E-mail", email, false) {
                        email = it
                    }
                    RequestTextField(R.drawable.proj_name_field_icon, "Password", password, true) {
                        password = it
                    }
                }

                Spacer(Modifier.height(30.dp))
                Button(
                    onClick = {viewModel.submit(email.text, password.text) },
                    Modifier
                        .width(145.dp)
                        .wrapContentHeight()
                        .padding(bottom = 16.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF3C6AB0)),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "Send",
                        textAlign = TextAlign.Left,
                        fontFamily = robotoFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        color = Color(0xFFFFFFFF),
                    )
                }
                Spacer(Modifier.height(30.dp))
                var state = viewModel.state
                if(state is AuthRespState.Success) {
                    viewModel.state = AuthRespState.Loading
                    Toast.makeText(LocalContext.current, "Success!", Toast.LENGTH_SHORT).show()
                    viewModel.navController.navigate(NavigationAdapter.Screen.Projects.route) {
                        popUpTo(viewModel.navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                }
                else if(state is AuthRespState.InvalidCredentials) {
                    Text(
                        text = "Invalid login or password. Try again!",
                        textAlign = TextAlign.Left,
                        fontFamily = robotoFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 13.sp,
                        color = Color(0xFFF70707),
                    )
                }
                else if(state is AuthRespState.UnknownServerError) {
                    Text(
                        text = "Unknown server error. Error code ${state.code}.",
                        textAlign = TextAlign.Left,
                        fontFamily = robotoFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 13.sp,
                        color = Color(0xFFF70707),
                    )
                }
                else if(state is AuthRespState.ConnectionError) {
                    Text(
                        text = "Server is unavailable. Please, check, your internet connection",
                        textAlign = TextAlign.Left,
                        fontFamily = robotoFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 13.sp,
                        color = Color(0xFFF70707),
                    )
                }
            }
        }


        @Composable
        fun RequestTextField(
            iconId: Int,
            label: String,
            value: TextFieldValue,
            hideSymbols: Boolean,
            onValueChanged: (TextFieldValue) -> Unit
        ) {
            Row(
                Modifier.padding(start = 15.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Image(
                    painter = painterResource(id = iconId), contentDescription = null,
                    Modifier.size(15.dp)
                )
                Spacer(Modifier.width(13.dp))
                Text(
                    text = label,
                    textAlign = TextAlign.Left,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = Color(0xFFBBBBBB),
                    modifier = Modifier
                        .padding(top = 12.dp, bottom = 16.dp)
                        .width(70.dp)
                )
                Spacer(Modifier.width(20.dp))
                BasicTextField(
                    modifier = Modifier.size(width = 210.dp, height = 30.dp),
                    value = value,
                    onValueChange = { onValueChanged(it) },
                    visualTransformation =
                        if(hideSymbols) PasswordVisualTransformation() else VisualTransformation.None
                )
            }
        }
}