package ru.hse.buildingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.hse.buildingapp.ui.theme.BuildingAppTheme

var robotoFamily = FontFamily(
    Font(R.font.roboto_light, FontWeight.Light),
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_bold, FontWeight.Bold)
);

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuildingAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LanguageChoose()
                }
            }
        }
    }
}

@Composable
fun LogoShow() {
    Box {
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.start_logo),
                contentDescription = "Company logo",
                modifier = Modifier
                    .size(width = 143.dp, height = 88.dp)
            )
            Text(text = "Royal Palace",
                textAlign = Center,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 30.sp,
                color = Color(0xFF484848)
            )
            Text("Engineering",
                textAlign = Center,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp,
                color = Color(0xFF484848)
            )
        }
    }
}

@Composable
fun LanguageChoose()
{
    Box {
        Column( modifier =
            Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Royal Palace",
                textAlign = Center,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = Color(0xFF484848)
            )
            Text(text = "Emgineering Consultancy",
                textAlign = Center,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = Color(0xFF484848),
            )
            Spacer(Modifier
                .height(17.dp))
            Image (
                painter = painterResource(id = R.drawable.lang_picture),
                contentDescription= "Picture of building works",
                modifier = Modifier
                    .size(width = 339.dp, height = 326.dp)
            )
            Spacer(Modifier.height(33.dp))
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
                Text("English",
                    textAlign = Center,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    color = Color.White)
            }
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
                Text("ﻲﺑﺮﻋ",
                    textAlign = Center,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BuildingAppTheme {
        LogoShow()
    }
}