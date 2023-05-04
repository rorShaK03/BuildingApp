package ru.hse.buildingapp.ui.screens

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

class LoginScreen {

    companion object {
        @Composable
        fun View() {
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
                    onClick = { },
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
}