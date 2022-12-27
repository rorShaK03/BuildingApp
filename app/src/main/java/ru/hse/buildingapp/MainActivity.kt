package ru.hse.buildingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.text.style.TextAlign.Companion.Left
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
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuildingAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ArchitecturalDesigns()
                }
            }
        }
    }
}

@Preview
@Composable
fun Preview()
{

    LanguageChoose()
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
            Text(text = "Engineering Consultancy",
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
                Text("Русский",
                    textAlign = Center,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    color = Color.White)
            }
        }
    }
}

@Composable
fun ArchitecturalDesigns()
{
    Column {
        Text(
            text = "Our Services",
            textAlign = Left,
            fontFamily = robotoFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            color = Color(0xFF2D4855),
            modifier = Modifier.padding(20.dp)
        )
            SectionButtonsRow()
            Spacer(Modifier.height(17.dp))
            Column(
                Modifier
                    .background(Color(0xFFEDEDED), shape = RoundedCornerShape(20.dp))
                    .fillMaxSize()) {
                Text(
                    text = "Architectural Designs",
                    textAlign = Left,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    color = Color(0xFF2D4855),
                    modifier = Modifier.padding(start = 19.dp, top = 14.dp)
                )
                Text(
                    text = "We offer architectural designs about any building or any apartment you want with all the necessary plans.",
                    textAlign = Left,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.W400,
                    fontSize = 14.sp,
                    color = Color(0xFF566871),
                    modifier = Modifier.padding(start = 19.dp, top = 11.dp)
                )
                Spacer(Modifier.height(10.dp))
                ImageColumn(
                    imageIds = listOf(
                        R.drawable.house1,
                        R.drawable.house2,
                        R.drawable.house3,
                        R.drawable.house4,
                        R.drawable.house5,
                        R.drawable.house6
                    )
                )
            }
    }
}

@Preview
@Composable
fun ButtonsPreview()
{
    SectionButtonsRow()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageColumn(imageIds: List<Int>) {
    LazyVerticalGrid(cells = GridCells.Fixed(2)) {
        items(items = imageIds) {
                id -> Image(
                    painter = painterResource(id = id),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(20.dp)
                        .clip(RoundedCornerShape(20))
                        .size(width = 125.dp, height = 200.dp)
                )
        }
    }
}

@Composable
fun SectionButtonsRow()
{
    var active by remember{ mutableStateOf(0) }

    Row(
        Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(23.dp, alignment = Alignment.CenterHorizontally)) {
        if(active == 0)
            LabeledSectionButton(R.drawable.architectural_icon_selected, Color(0xFF3C6AB0), Color(0xFF2D4855), "Architectural\n" +
                    "Designs", onClick = {active = 0})
        else
            LabeledSectionButton(R.drawable.architectural_icon_unselected, Color(0xFFEDEDED), Color(0xFF848484), "Architectural\n" +
                    "Designs", onClick = {active = 0})
        if(active == 1)
            LabeledSectionButton(R.drawable.interior_icon_selected, Color(0xFF3C6AB0), Color(0xFF2D4855), "Interior\n" +
                    "Designs", onClick = {active = 1})
        else
            LabeledSectionButton(R.drawable.interior_icon_unselected, Color(0xFFEDEDED), Color(0xFF848484), "Interior\n" +
                    "Designs", onClick = {active = 1})
        if(active == 2)
            LabeledSectionButton(R.drawable.consultings_icon_selected, Color(0xFF3C6AB0), Color(0xFF2D4855), "Consultings\n" +
                    "Engineering", onClick = {active = 2})
        else
            LabeledSectionButton(R.drawable.consultings_icon_unselected, Color(0xFFEDEDED), Color(0xFF848484), "Consultings\n" +
                    "Engineering", onClick = {active = 2})
        if(active == 3)
            LabeledSectionButton(R.drawable.estate_icon_selected, Color(0xFF3C6AB0), Color(0xFF2D4855), "Real estate\n" +
                    "consulting", onClick = {active = 3})
        else
            LabeledSectionButton(R.drawable.estate_icon_unselected, Color(0xFFEDEDED), Color(0xFF848484), "Real estate\n" +
                    "consulting", onClick = {active = 3})
    }
}

@Composable
fun LabeledSectionButton(imgId : Int, backgroundColor : Color, textColor : Color, label : String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Column {
        SectionButton(imgId, backgroundColor, onClick, modifier)
        Spacer(Modifier.height(16.dp))
        Text(
            text = label,
            textAlign = Left,
            fontFamily = robotoFamily,
            fontWeight = FontWeight.W400,
            fontSize = 14.sp,
            color = textColor,
        )
    }
}

@Composable
fun SectionButton(imgId : Int, backgroundColor : Color, onClick : () -> Unit, modifier: Modifier = Modifier) {
    Button(onClick = onClick, modifier
        .size(78.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
        shape = RoundedCornerShape(10.dp)) {
        Image(painter = painterResource(id = imgId),
            contentDescription = "",
            Modifier.size(34.dp))
    }
}