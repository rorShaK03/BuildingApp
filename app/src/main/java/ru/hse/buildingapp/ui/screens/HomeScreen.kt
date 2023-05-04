package ru.hse.buildingapp.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ru.hse.buildingapp.MainActivity
import ru.hse.buildingapp.R
import ru.hse.buildingapp.robotoFamily

class HomeScreen private constructor() {
    companion object {
        @Composable
        fun View(navController: NavHostController,
                 viewModel : HomeViewModel = viewModel()
        )
        {
            var title by rememberSaveable{ mutableStateOf("Architectural Designs") }
            var images by rememberSaveable{ mutableStateOf(listOf(
                R.drawable.house1,
                R.drawable.house2,
                R.drawable.house3,
                R.drawable.house4,
                R.drawable.house5,
                R.drawable.house6
            )) }
            Column {
                Text(
                    text = "Our Services",
                    textAlign = TextAlign.Left,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    color = Color(0xFF2D4855),
                    modifier = Modifier.padding(20.dp)
                )
                SectionButtonsRow(onClick = {
                    when(it) {
                        0 -> {title = "Architectural Designs"
                            images = viewModel.architecturalImgIds }
                        1 -> {title = "Interior Design"
                            images = viewModel.interiorImgIds}
                        2 -> {title = "Consultings Engineering"
                            images = viewModel.consultingImgIds}
                        3 -> {title = "Real estate Consulting"
                            images = viewModel.realEstateImgIds}
                    }
                })
                Spacer(Modifier.height(14.dp))
                Column(
                    Modifier
                        .background(Color(0xFFEDEDED), shape = RoundedCornerShape(20.dp))
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())) {
                    Text(
                        text = title,
                        textAlign = TextAlign.Left,
                        fontFamily = robotoFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        color = Color(0xFF2D4855),
                        modifier = Modifier.padding(start = 19.dp, top = 14.dp)
                    )
                    Text(
                        text = "We offer architectural designs about any building or any apartment you want with all the necessary plans.",
                        textAlign = TextAlign.Left,
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
                            onClick = {
                                navController.navigate(MainActivity.Screen.ProjectRequest.route) {
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            Modifier
                                .width(145.dp)
                                .height(40.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF3C6AB0)),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text(text ="Submit a request",
                                textAlign = TextAlign.Left,
                                fontFamily = robotoFamily,
                                fontWeight = FontWeight.Medium,
                                fontSize = 12.sp,
                                color = Color(0xFFFFFFFF))
                        }
                    }
                }
            }
        }


//        @OptIn(ExperimentalFoundationApi::class)
        @Composable
        fun ImageColumn(imageIds: List<Int>) {
            Row(
                Modifier
                    .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
                Column() {
                    for(i in 0 until imageIds.size / 2) {
                        val id = imageIds[i]
                        Image(
                            painter = painterResource(id),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(20.dp)
                                .clip(RoundedCornerShape(20))
                                .size(width = 125.dp, height = 200.dp)
                        )
                    }
                }
                Spacer(Modifier.width(20.dp))
                Column() {
                    for(i in imageIds.size / 2 until imageIds.size) {
                        val id = imageIds[i]
                        Image(
                            painter = painterResource(id),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(20.dp)
                                .clip(RoundedCornerShape(20))
                                .size(width = 125.dp, height = 200.dp)
                        )
                    }
                }
            }
            /*
            LazyVerticalGrid(cells = GridCells.Fixed(2),
                Modifier
                    .fillMaxWidth()
                    .height(250.dp)) {
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
            */
        }

        @Composable
        fun SectionButtonsRow(onClick: (Int) -> Unit)
        {
            var active by remember{ mutableStateOf(0) }
            Row(
                Modifier
                    .height(100.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly) {
                if(active == 0)
                    LabeledSectionButton(
                        R.drawable.architectural_icon_selected, Color(0xFF3C6AB0), Color(0xFF2D4855), "Architectural\n" +
                            "Designs", onClick = {active = 0; onClick(active)}, modifier = Modifier.width(75.dp))
                else
                    LabeledSectionButton(
                        R.drawable.architectural_icon_unselected, Color(0xFFEDEDED), Color(0xFF848484), "Architectural\n" +
                            "Designs", onClick = {active = 0; onClick(active)}, modifier = Modifier.width(75.dp))
                if(active == 1)
                    LabeledSectionButton(
                        R.drawable.interior_icon_selected, Color(0xFF3C6AB0), Color(0xFF2D4855), "Interior\n" +
                            "Designs", onClick = {active = 1; onClick(active)}, modifier = Modifier.width(75.dp))
                else
                    LabeledSectionButton(
                        R.drawable.interior_icon_unselected, Color(0xFFEDEDED), Color(0xFF848484), "Interior\n" +
                            "Designs", onClick = {active = 1; onClick(active)}, modifier = Modifier.width(75.dp))
                if(active == 2)
                    LabeledSectionButton(
                        R.drawable.consultings_icon_selected, Color(0xFF3C6AB0), Color(0xFF2D4855), "Consultings\n" +
                            "Engineering", onClick = {active = 2; onClick(active)}, modifier = Modifier.width(75.dp))
                else
                    LabeledSectionButton(
                        R.drawable.consultings_icon_unselected, Color(0xFFEDEDED), Color(0xFF848484), "Consultings\n" +
                            "Engineering", onClick = {active = 2; onClick(active)}, modifier = Modifier.width(75.dp))
                if(active == 3)
                    LabeledSectionButton(
                        R.drawable.estate_icon_selected, Color(0xFF3C6AB0), Color(0xFF2D4855), "Real estate\n" +
                            "consulting", onClick = {active = 3; onClick(active)}, modifier = Modifier.width(75.dp))
                else
                    LabeledSectionButton(
                        R.drawable.estate_icon_unselected, Color(0xFFEDEDED), Color(0xFF848484), "Real estate\n" +
                            "consulting", onClick = {active = 3; onClick(active)}, modifier = Modifier.width(75.dp))
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
                Box(Modifier.weight(0.4f)) {
                    Text(
                        text = label,
                        textAlign = TextAlign.Left,
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
                shape = RoundedCornerShape(10.dp)
            ) {
                Image(painter = painterResource(id = imgId),
                    contentDescription = "")
            }
        }
    }
}