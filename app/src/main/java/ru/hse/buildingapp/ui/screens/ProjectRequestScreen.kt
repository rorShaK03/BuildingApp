package ru.hse.buildingapp.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.hse.buildingapp.R
import ru.hse.buildingapp.robotoFamily

object ProjectRequestScreen {
        @Composable
        fun View() {
            var name by remember { mutableStateOf(TextFieldValue("")) }
            var phone by remember { mutableStateOf(TextFieldValue("")) }
            var email by remember { mutableStateOf(TextFieldValue("")) }
            var projName by remember { mutableStateOf(TextFieldValue("")) }
            var projType by remember { mutableStateOf(TextFieldValue("")) }
            var totalArea by remember { mutableStateOf(TextFieldValue("")) }
            var budget by remember { mutableStateOf(TextFieldValue("")) }
            var city by remember { mutableStateOf(TextFieldValue("")) }
            var notes by remember { mutableStateOf(TextFieldValue("")) }
            Column(
                Modifier
                    .background(Color(0xFFEDEDED), shape = RoundedCornerShape(20.dp))
                    .fillMaxWidth()
                    .height(600.dp)
                    .padding(start = 18.dp, end = 18.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "We are glad to receive your requests",
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
                )
                {
                    Text(
                        text = "Please insert your informations",
                        textAlign = TextAlign.Center,
                        fontFamily = robotoFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        color = Color(0xFF9F9F9F),
                        modifier = Modifier.padding(start = 10.dp, top = 14.dp, bottom = 16.dp)
                    )
                    RequestTextField(R.drawable.name_field_icon, "Name", name) {
                        name = it
                    }
                    RequestTextField(R.drawable.phone_field_icon, "Phone", phone) {
                        phone = it
                    }
                    RequestTextField(R.drawable.email_field_icon, "E-mail", email) {
                        email = it
                    }
                }
                Spacer(Modifier.height(21.dp))
                Column(
                    Modifier
                        .background(Color(0xFFFFFFFF), shape = RoundedCornerShape(20.dp))
                        .fillMaxWidth()
                )
                {
                    Text(
                        text = "Please insert your Project Informations",
                        textAlign = TextAlign.Center,
                        fontFamily = robotoFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        color = Color(0xFF9F9F9F),
                        modifier = Modifier.padding(start = 10.dp, top = 14.dp, bottom = 16.dp)
                    )
                    RequestTextField(R.drawable.proj_name_field_icon, "Name", projName) {
                        projName = it
                    }
                    RequestTextField(R.drawable.type_field_icon, "Type", projType) {
                        projType = it
                    }
                    RequestTextField(R.drawable.total_area_field_icon, "Total area", totalArea) {
                        totalArea = it
                    }
                    RequestTextField(R.drawable.budget_field_icon, "Budget", budget) {
                        budget = it
                    }
                    RequestTextField(R.drawable.city_field_icon, "City", city) {
                        city = it
                    }
                }
                Spacer(Modifier.height(21.dp))
                Column(
                    Modifier
                        .background(Color(0xFFFFFFFF), shape = RoundedCornerShape(20.dp))
                        .fillMaxWidth()
                )
                {
                    Text(
                        text = "Please insert your notes",
                        textAlign = TextAlign.Center,
                        fontFamily = robotoFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        color = Color(0xFF9F9F9F),
                        modifier = Modifier.padding(start = 10.dp, top = 14.dp, bottom = 16.dp)
                    )
                    BasicTextField(
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                        value = notes,
                        onValueChange = { notes = it }
                    )
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
            onValueChanged: (TextFieldValue) -> Unit
        ) {
            Row(
                Modifier
                    .padding(start = 15.dp)
                    .padding(top = 12.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Image(
                    painter = painterResource(id = iconId), contentDescription = null,
                    Modifier.size(15.dp)
                )
                Spacer(Modifier.width(13.dp))
                BasicTextField(
                    modifier = Modifier
                        .size(width = 270.dp, height = 35.dp),
                    value = value,
                    onValueChange = { onValueChanged(it) },
                    decorationBox = {innerTextField ->
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = Color(0xFFD1E2E1),
                                shape = RoundedCornerShape(size = 6.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        ) {
                            if(value.text.isEmpty()) {
                                Text(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .align(Alignment.BottomStart),
                                    text = label,
                                    fontSize = 12.sp,
                                    fontFamily = robotoFamily,
                                    fontWeight = FontWeight.Light,
                                    color = Color(0xFF9F9F9F),

                                )
                            }
                            innerTextField()
                        }
                    }
                )
            }
        }
}