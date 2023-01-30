package ru.hse.buildingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
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
                    HomeScreen()
                }
            }
        }
    }
}

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

@Preview
@Composable
fun HomeScreen()
{
    var title by rememberSaveable{ mutableStateOf("Home")}
    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.Transparent,
                      elevation = 0.dp) {
                Box(Modifier.height(32.dp)) {
                    Row {
                        IconButton(onClick = { /*TODO*/ }) {
                            Image(
                                painter = painterResource(id = R.drawable.baseline_menu_dark),
                                contentDescription = "Side menu"
                            )
                        }
                    }
                    Box(modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center) {
                        Text(
                            fontFamily = robotoFamily,
                            fontWeight = FontWeight.W500,
                            fontSize = 24.sp,
                            textAlign = Center,
                            maxLines = 1,
                            text = title,
                            color = Color(0xFF2D4855)
                        )
                    }
                    Box(modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.CenterEnd) {
                        IconButton(onClick = {/*TODO*/}) {
                            Image(
                                painter = painterResource(id = R.drawable.profile_icon),
                                contentDescription = "Profile icon"
                            )
                        }
                    }
                }
            }
            },
        bottomBar = {
            var selectedItem by remember{ mutableStateOf(0) };
            val bottomNavItems = listOf("Home", "Projects", "News", "About us")
            val bottomNavIcons: List<Int> = listOf(R.drawable.home_icon, R.drawable.projects_icon, R.drawable.news_icon, R.drawable.about_us_icon)
                BottomNavigation(
                    backgroundColor = Color.White,
                    modifier = Modifier.clip(shape = RoundedCornerShape(10.dp))
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        bottomNavItems.forEachIndexed { index, item ->
                            BottomNavigationItem(
                                icon = {
                                    Icon(
                                        painter = painterResource(id = bottomNavIcons[index]),
                                        contentDescription = null
                                    )
                                },
                                label = { Text(item) },
                                selected = selectedItem == index,
                                onClick = { selectedItem = index },
                                selectedContentColor = Color(0xFF3C6AB0),
                                unselectedContentColor = Color(0xFF5A6970)
                            )
                            if(index != bottomNavItems.size - 1) {
                                Spacer(modifier = Modifier.width(10.dp))
                                Image(
                                    painter = painterResource(id = R.drawable.bottom_nav_separator),
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            }
    )
        {
            if(title == "Home")
                ProjectsBlock { title = "Submit a request" }
            else if(title == "Submit a request")
                RequestForm()
        }
}

@Composable
fun ProjectsBlock(onSubmitClick: () -> Unit)
{
    var title by rememberSaveable{ mutableStateOf("Architectural Designs")}
    var images by rememberSaveable{ mutableStateOf(listOf(R.drawable.house1, R.drawable.house2, R.drawable.house3, R.drawable.house4, R.drawable.house5, R.drawable.house6))}
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
            SectionButtonsRow(onClick = {
                when(it) {
                    0 -> {title = "Architectural Designs"
                          images = listOf(R.drawable.house1, R.drawable.house2, R.drawable.house3, R.drawable.house4, R.drawable.house5, R.drawable.house6)}
                    1 -> {title = "Interior Design"
                        images = listOf(R.drawable.interior1, R.drawable.interior2, R.drawable.interior3, R.drawable.interior4, R.drawable.interior5, R.drawable.interior6)}
                    2 -> {title = "Consultings Engineering"
                        images = listOf(R.drawable.consultings1, R.drawable.consultings2, R.drawable.consultings3, R.drawable.consultings4, R.drawable.consultings5, R.drawable.consultings6)}
                    3 -> {title = "Real estate Consulting"
                        images = listOf(R.drawable.real_estate1, R.drawable.real_estate2, R.drawable.real_estate3, R.drawable.real_estate4, R.drawable.real_estate5, R.drawable.real_estate6)}
                }
            })
            Spacer(Modifier.height(17.dp))
            Column(
                Modifier
                    .background(Color(0xFFEDEDED), shape = RoundedCornerShape(20.dp))
                    .fillMaxWidth()) {
                Text(
                    text = title,
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
                    imageIds = images
                )
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(20.dp), horizontalArrangement = Arrangement.Center) {
                    Button(
                        onClick = onSubmitClick,
                        Modifier
                            .width(145.dp)
                            .height(33.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF3C6AB0)),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(text ="Submit a request",
                            textAlign = Left,
                            fontFamily = robotoFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                            color = Color(0xFFFFFFFF),)
                    }
                }
            }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageColumn(imageIds: List<Int>) {
    LazyVerticalGrid(cells = GridCells.Fixed(2),
        Modifier
            .fillMaxWidth()
            .height(260.dp)) {
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
fun SectionButtonsRow(onClick: (Int) -> Unit)
{
    var active by remember{ mutableStateOf(0) }
    Row(
        Modifier
            .height(120.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly) {
        if(active == 0)
            LabeledSectionButton(R.drawable.architectural_icon_selected, Color(0xFF3C6AB0), Color(0xFF2D4855), "Architectural\n" +
                    "Designs", onClick = {active = 0; onClick(active)}, modifier = Modifier.width(78.dp))
        else
            LabeledSectionButton(R.drawable.architectural_icon_unselected, Color(0xFFEDEDED), Color(0xFF848484), "Architectural\n" +
                    "Designs", onClick = {active = 0; onClick(active)}, modifier = Modifier.width(78.dp))
        if(active == 1)
            LabeledSectionButton(R.drawable.interior_icon_selected, Color(0xFF3C6AB0), Color(0xFF2D4855), "Interior\n" +
                    "Designs", onClick = {active = 1; onClick(active)}, modifier = Modifier.width(78.dp))
        else
            LabeledSectionButton(R.drawable.interior_icon_unselected, Color(0xFFEDEDED), Color(0xFF848484), "Interior\n" +
                    "Designs", onClick = {active = 1; onClick(active)}, modifier = Modifier.width(78.dp))
        if(active == 2)
            LabeledSectionButton(R.drawable.consultings_icon_selected, Color(0xFF3C6AB0), Color(0xFF2D4855), "Consultings\n" +
                    "Engineering", onClick = {active = 2; onClick(active)}, modifier = Modifier.width(78.dp))
        else
            LabeledSectionButton(R.drawable.consultings_icon_unselected, Color(0xFFEDEDED), Color(0xFF848484), "Consultings\n" +
                    "Engineering", onClick = {active = 2; onClick(active)}, modifier = Modifier.width(78.dp))
        if(active == 3)
            LabeledSectionButton(R.drawable.estate_icon_selected, Color(0xFF3C6AB0), Color(0xFF2D4855), "Real estate\n" +
                    "consulting", onClick = {active = 3; onClick(active)}, modifier = Modifier.width(78.dp))
        else
            LabeledSectionButton(R.drawable.estate_icon_unselected, Color(0xFFEDEDED), Color(0xFF848484), "Real estate\n" +
                    "consulting", onClick = {active = 3; onClick(active)}, modifier = Modifier.width(78.dp))
    }
}

@Composable
fun LabeledSectionButton(imgId : Int, backgroundColor : Color, textColor : Color, label : String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier
                      .fillMaxHeight()) {
        SectionButton(imgId, backgroundColor, onClick,
            Modifier
                .weight(0.7f)
                .fillMaxWidth())
        Box(Modifier.weight(0.3f)) {
            Text(
                text = label,
                textAlign = Left,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.W400,
                fontSize = 13.sp,
                color = textColor
            )
        }
    }
}

@Composable
fun SectionButton(imgId : Int, backgroundColor : Color, onClick : () -> Unit, modifier: Modifier = Modifier) {
    Button(onClick = onClick, modifier = modifier,
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
        shape = RoundedCornerShape(10.dp)) {
        Image(painter = painterResource(id = imgId),
            contentDescription = "")
    }
}

@Composable
fun RequestForm()
{
    var name by remember { mutableStateOf(TextFieldValue(""))}
    var phone by remember { mutableStateOf(TextFieldValue(""))}
    var email by remember { mutableStateOf(TextFieldValue(""))}
    var projName by remember { mutableStateOf(TextFieldValue(""))}
    var projType by remember { mutableStateOf(TextFieldValue(""))}
    var totalArea by remember { mutableStateOf(TextFieldValue(""))}
    var budget by remember { mutableStateOf(TextFieldValue(""))}
    var city by remember { mutableStateOf(TextFieldValue(""))}
    Column(
        Modifier
            .background(Color(0xFFEDEDED), shape = RoundedCornerShape(20.dp))
            .fillMaxWidth()
            .padding(start = 18.dp, end = 18.dp),
    horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "We are glad to receive your requests",
            textAlign = Center,
            fontFamily = robotoFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            color = Color(0xFF3C6AB0),
            modifier = Modifier.padding(top = 24.dp, bottom = 16.dp))
        Column(
            Modifier
                .background(Color(0xFFFFFFFF), shape = RoundedCornerShape(20.dp))
                .fillMaxWidth())
        {
            Text(text = "Please insert your informations",
                textAlign = Center,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = Color(0xFF9F9F9F),
                modifier = Modifier.padding(start = 10.dp, top = 14.dp, bottom = 16.dp))
                RequestTextField(R.drawable.name_field_icon, "Name", name) { it: TextFieldValue ->
                    name = it
                }
                RequestTextField(R.drawable.phone_field_icon, "Phone", phone) { it: TextFieldValue ->
                    phone = it
                }
                RequestTextField(R.drawable.email_field_icon, "E-mail", email) { it: TextFieldValue ->
                    email = it
                }
        }
        Spacer(Modifier.height(21.dp))
        Column(
            Modifier
                .background(Color(0xFFFFFFFF), shape = RoundedCornerShape(20.dp))
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()))
        {
            Text(text = "Please insert your Project Informations",
                textAlign = Center,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = Color(0xFF9F9F9F),
                modifier = Modifier.padding(start = 10.dp, top = 14.dp, bottom = 16.dp))
            RequestTextField(R.drawable.proj_name_field_icon, "Name", projName) { it: TextFieldValue ->
                projName = it
            }
            RequestTextField(R.drawable.type_field_icon, "Type", projType) { it: TextFieldValue ->
                projType = it
            }
            RequestTextField(R.drawable.total_area_field_icon, "Total area", totalArea) { it: TextFieldValue ->
                totalArea = it
            }
            RequestTextField(R.drawable.budget_field_icon, "Budget", budget) { it: TextFieldValue ->
                budget = it
            }
            RequestTextField(R.drawable.city_field_icon, "City", city) { it: TextFieldValue ->
                city = it
            }
            Spacer(Modifier.height(60.dp))
        }
    }
}


@Composable
fun RequestTextField(iconId : Int, label: String, value : TextFieldValue, onValueChanged: (TextFieldValue) -> Unit)
{
    Row(Modifier.padding(start = 15.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically)
    {
        Image(painter = painterResource(id = iconId), contentDescription = null,
            Modifier.size(15.dp))
        Spacer(Modifier.width(13.dp))
        Text(text = label,
            textAlign = Left,
            fontFamily = robotoFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            color = Color(0xFFBBBBBB),
            modifier = Modifier
                .padding(top = 24.dp, bottom = 16.dp)
                .width(50.dp))
        Spacer(Modifier.width(30.dp))
        TextField(
            modifier = Modifier.size(width=224.dp, height=50.dp),
            value = value,
            onValueChange = {onValueChanged(it)}
        )
    }
}