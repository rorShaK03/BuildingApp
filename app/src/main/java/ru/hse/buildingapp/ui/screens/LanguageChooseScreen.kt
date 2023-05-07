package ru.hse.buildingapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import ru.hse.buildingapp.R
import ru.hse.buildingapp.robotoFamily

object LanguageChooseScreen {
    @Composable
    fun View(navController: NavHostController)
    {
        Box {
            Column( modifier =
            Modifier.fillMaxSize()
                .padding(top = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Royal Palace",
                    textAlign = TextAlign.Center,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = Color(0xFF484848)
                )
                Text(text = "Engineering Consultancy",
                    textAlign = TextAlign.Center,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = Color(0xFF484848),
                )
                Spacer(
                    Modifier
                    .height(17.dp))
                Image (
                    painter = painterResource(id = R.drawable.lang_picture),
                    contentDescription= "Picture of building works",
                    modifier = Modifier
                        .size(width = 339.dp, height = 326.dp)
                )
                Spacer(Modifier.height(33.dp))
                Button(
                    onClick = {navController.navigate("main-app") {
                        popUpTo(navController.graph.findStartDestination().id) {inclusive = true}
                        launchSingleTop = true
                    } },
                    Modifier
                        .width(230.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF484848)
                    )
                ) {
                    Text("English",
                        textAlign = TextAlign.Center,
                        fontFamily = robotoFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        color = Color.White)
                }
                /*
                Spacer(Modifier.height(23.dp))
                Button(
                    onClick = {},
                    Modifier
                        .width(230.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF484848)
                    )
                ) {
                    Text("Русский",
                        textAlign = TextAlign.Center,
                        fontFamily = robotoFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        color = Color.White)
                }
                 */
            }
        }
    }
}