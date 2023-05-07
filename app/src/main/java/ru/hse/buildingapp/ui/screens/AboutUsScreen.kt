package ru.hse.buildingapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.hse.buildingapp.robotoFamily
import ru.hse.buildingapp.R

object AboutUsScreen {

    @Composable
    fun View() {
        var active : Int by remember{ mutableStateOf(0) }
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF3C6AB0))) {
            AboutUsButtonsRow() {active = it}
            Column(modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFFFFF), RoundedCornerShape(7, 7, 0, 0))) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)) {
                        when(active) {
                            0 -> AboutUs()
                            1 -> BookingScreen.View()
                            2 -> OurTeamScreen.View()
                        }
                }
            }
        }

    }

    @Composable
    private fun AboutUs() {
        Image(
            modifier = Modifier
                .padding(start = 10.dp, top = 20.dp)
                .width(170.dp),
            painter = painterResource(id = R.drawable.nav_drawer_logo),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Spacer(Modifier.height(20.dp))
        Text(
            text = "INFO STRATEGIC CONSULTANCY\n" +
                    "Is an information management company, specialized in designing and implementing comprehensive" +
                    " information technology-based solutions that support clients to successfully achieve their strategy and overarching objectives.",
            textAlign = TextAlign.Left,
            fontFamily = robotoFamily,
            fontWeight = FontWeight.W400,
            fontSize = 14.sp,
            color = Color(0xFF7D7D7D)
        )
        Spacer(Modifier.height(10.dp))
        Divider()
        Spacer(Modifier.height(46.dp))
        ContactsColumn()
    }

    @Composable
    private fun ContactsColumn() {
        Column(horizontalAlignment = Alignment.Start) {
            Row(modifier = Modifier
                .padding(bottom = 13.dp)) {
                Image( modifier = Modifier
                    .padding(end = 10.dp),
                    painter = painterResource(id = R.drawable.phone_field_icon),
                    contentDescription = null
                )
                Text(text = "9008 605 50 00971",
                    textAlign = TextAlign.Left,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.W400,
                    fontSize = 14.sp,
                    color = Color(0xFF7D7D7D))
            }
            Row(modifier = Modifier
                .padding(bottom = 13.dp)) {
                Image( modifier = Modifier
                    .padding(end = 10.dp),
                    painter = painterResource(id = R.drawable.web_icon),
                    contentDescription = null
                )
                Text(text = "www.infostrategic.com",
                    textAlign = TextAlign.Left,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.W400,
                    fontSize = 14.sp,
                    color = Color(0xFF7D7D7D))
            }
            Row(modifier = Modifier
                .padding(bottom = 13.dp)) {
                Image( modifier = Modifier
                    .padding(end = 10.dp),
                    painter = painterResource(id = R.drawable.email_field_icon),
                    contentDescription = null
                )
                Text(text = "info@infostrategic.com",
                    textAlign = TextAlign.Left,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.W400,
                    fontSize = 14.sp,
                    color = Color(0xFF7D7D7D))
            }
            Row(modifier = Modifier
                .padding(bottom = 13.dp)) {
                Image( modifier = Modifier
                    .padding(end = 10.dp),
                    painter = painterResource(id = R.drawable.city_field_icon),
                    contentDescription = null
                )
                Text(text = "P.O Box: 412024, Dubai, UAE",
                    textAlign = TextAlign.Left,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.W400,
                    fontSize = 14.sp,
                    color = Color(0xFF7D7D7D))
            }
        }
    }

    @Composable
    private fun AboutUsButtonsRow(onClick : (Int) -> Unit) {
        var active : Int by remember{ mutableStateOf(0) }
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(85.dp)
            .padding(bottom = 20.dp, top = 30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly) {
            TextButton(modifier = Modifier
                .width(100.dp)
                .fillMaxHeight(),
                colors = ButtonDefaults.buttonColors(backgroundColor = if(active == 0) Color(0xFFFFFFFF) else Color(0x3DFFFFFF)),
                shape = RoundedCornerShape(5.dp),
                onClick = {active = 0; onClick(active)}
            ) {
                Text(
                    text = stringResource(id = R.string.about_us),
                    textAlign = TextAlign.Left,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.W400,
                    fontSize = 13.sp,
                    color = if(active == 0) Color(0xFF3C6AB0) else Color(0xFFFFFFFF)
                )
            }
            TextButton(modifier = Modifier
                .width(100.dp)
                .fillMaxHeight(),
                colors = ButtonDefaults.buttonColors(backgroundColor = if(active == 1) Color(0xFFFFFFFF) else Color(0x3DFFFFFF) ),
                shape = RoundedCornerShape(5.dp),
                onClick = {active = 1; onClick(active)}
            ) {
                Text(
                    text = stringResource(id = R.string.booking),
                    textAlign = TextAlign.Left,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.W400,
                    fontSize = 13.sp,
                    color = if(active == 1) Color(0xFF3C6AB0) else Color(0xFFFFFFFF)
                )
            }
            TextButton(modifier = Modifier
                .width(100.dp)
                .fillMaxHeight(),
                colors = ButtonDefaults.buttonColors(backgroundColor = if(active == 2) Color(0xFFFFFFFF) else Color(0x3DFFFFFF)),
                shape = RoundedCornerShape(5.dp),
                onClick = {active = 2; onClick(active)}
            ){
                Text(
                    text = stringResource(id = R.string.our_team),
                    textAlign = TextAlign.Left,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.W400,
                    fontSize = 13.sp,
                    color = if(active == 2) Color(0xFF3C6AB0) else Color(0xFFFFFFFF)
                )
            }
        }
    }
}