package ru.hse.buildingapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import ru.hse.buildingapp.R
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.hse.buildingapp.robotoFamily

object OurTeamScreen {
    @Composable
    fun View() {
        Column(modifier = Modifier
            .padding(top = 23.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())) {
            TeamCard(
                "Executive supervisor",
                "2 highly experienced architects",
                listOf(R.drawable.avatar1, R.drawable.avatar2)
            )
            TeamCard(
                "Architect",
                "3 highly experienced architects",
                listOf(R.drawable.avatar3, R.drawable.avatar5, R.drawable.avatar4)
            )
            TeamCard(
                "Interior designer",
                "4 highly experienced Interior designer",
                listOf(R.drawable.avatar2, R.drawable.avatar1, R.drawable.avatar3, R.drawable.avatar4)
            )
        }
    }

    @Composable
    private fun TeamCard(name : String, desc : String, avatarIds : List<Int> = listOf()) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .height(125.dp)
            .padding(bottom = 15.dp)
            .background(Color(0xFFF2F2F2), RoundedCornerShape(10))
        ){
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(top = 4.dp, start = 15.dp)) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = name,
                    textAlign = TextAlign.Left,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.W500,
                    fontSize = 18.sp,
                    color = Color(0xFF3C6AB0)
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    text = desc,
                    textAlign = TextAlign.Left,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.W400,
                    fontSize = 14.sp,
                    color = Color(0xFF656565)
                )
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, bottom = 5.dp)) {
                    for(id in avatarIds) {
                        Image(painter = painterResource(id),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(34.dp)
                            .padding(end = 6.dp))
                    }
                }
            }
        }
    }
}