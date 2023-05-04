package ru.hse.buildingapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.hse.buildingapp.robotoFamily

object ProjectScreen {
        @Composable
        fun View(
            viewModel : ProjectViewModel = viewModel()
        ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF3C6AB0))
                ) {
                    Text(
                        modifier = Modifier
                            .padding(top = 25.dp, bottom = 25.dp, start = 18.dp, end = 65.dp),
                        text = viewModel.project.projectName,
                        textAlign = TextAlign.Left,
                        fontFamily = robotoFamily,
                        fontWeight = FontWeight.W400,
                        fontSize = 16.sp,
                        color = Color(0xFFFFFFFF)
                    )
                    Image(
                        painter = painterResource(id = viewModel.project.projectImageId),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(RoundedCornerShape(10, 10, 0, 0))
                            .fillMaxWidth()
                    )
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFFFFFFF))){
                        Text( modifier = Modifier
                                .padding(top = 25.dp, bottom = 10.dp, start = 18.dp),
                            text = "General Information",
                            textAlign = TextAlign.Left,
                            fontFamily = robotoFamily,
                            fontWeight = FontWeight.W700,
                            fontSize = 18.sp,
                            color = Color(0xFF3C6AB0)
                        )
                        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                            Property(name = "Address", value = viewModel.project.address)
                            Property(name = "Area", value = viewModel.project.area.toString())
                            Property(name = "Budget", value = viewModel.project.budget.toString())
                            /*
                            Property(name = "Due Date", value = viewModel.dueDate)
                            Property(name = "Location", value = viewModel.location)
                            Property(name = "Status", value = viewModel.status)
                            Property(name = "Created by", value = viewModel.createdBy)
                            Property(name = "RFI manager", value = viewModel.RFIManager)
                            Property(name = "Assignees", value = viewModel.assignees)
                            Property(name = "Received From", value = viewModel.receivedFrom)
                            Property(
                                name = "Responsible Contractor",
                                value = viewModel.responsibleContractor
                            )
                            Property(name = "Drawing Number", value = viewModel.drawingNumber)
                            */
                            Spacer(Modifier.height(10.dp))
                        }
                    }
                }

        }

        @Composable
        private fun Property(name : String, value : String) {
            Row(modifier = Modifier
                .padding(start = 18.dp, top = 12.dp)) {
                Text(modifier = Modifier
                    .width(120.dp),
                    text = name,
                    textAlign = TextAlign.Left,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.W600,
                    fontSize = 16.sp,
                    color = Color(0xFF888888))
                Spacer(Modifier.width(140.dp))
                Text(text = value,
                    textAlign = TextAlign.Left,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.W400,
                    fontSize = 16.sp,
                    color = Color(0xFF2D4855))
            }

        }
}