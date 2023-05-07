package ru.hse.buildingapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.hse.buildingapp.ui.viewmodels.ReportsViewModel
import ru.hse.buildingapp.robotoFamily

object ReportsScreen {
    @Composable
    fun View(viewModel : ReportsViewModel) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 10.dp, horizontal = 13.dp)
            .verticalScroll(rememberScrollState())) {
            ReportCard("Test title for ${viewModel.project.projectName}", "Test text 1", "05/05/2023")
            ReportCard("Test title for ${viewModel.project.projectName}", "Test text 2", "05/05/2023")
            ReportCard("Test title for ${viewModel.project.projectName}", "Test text 3", "05/05/2023")
        }
    }

    @Composable
    private fun ReportCard(title : String, text : String, date : String) {
        Column(modifier = Modifier
            .padding(bottom = 15.dp)
            .fillMaxWidth()
            .height(115.dp)
            .background(Color(0xFFF2F2F2), RoundedCornerShape(10.dp))) {
            Text(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, top = 7.dp, bottom = 10.dp),
                text = title,
                textAlign = TextAlign.Left,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.W500,
                fontSize = 14.sp,
                color = Color(0xFF3F4C52))
            Text(modifier = Modifier
                .width(100.dp)
                .padding(start = 10.dp, bottom = 7.dp),
                text = text,
                textAlign = TextAlign.Left,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.W400,
                fontSize = 14.sp,
                color = Color(0xFF3F4C52))
            Row(modifier = Modifier
                .padding(bottom = 7.dp)
                .fillMaxSize(),
                verticalAlignment = Alignment.Bottom) {
                Text(modifier = Modifier
                    .width(100.dp)
                    .padding(start = 10.dp, bottom = 7.dp),
                    text = date,
                    textAlign = TextAlign.Left,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.W400,
                    fontSize = 11.sp,
                    color = Color(0xFF77909B))
            }
        }
    }
}