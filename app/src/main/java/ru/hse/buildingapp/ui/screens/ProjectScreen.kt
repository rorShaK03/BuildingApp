package ru.hse.buildingapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.hse.buildingapp.navigation.NavigationAdapter
import ru.hse.buildingapp.robotoFamily
import ru.hse.buildingapp.ui.viewmodels.ProjectViewModel

object ProjectScreen {
        @Composable
        fun View(
            viewModel : ProjectViewModel
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
                        Column(modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .height(130.dp)) {
                            Property(name = "Adress", value = viewModel.project.address)
                            Property(name = "Area", value = viewModel.project.area.toString())
                            Property(name = "Budget", value = viewModel.project.budget.toString())
                            Property(name = "Status", value = viewModel.project.projStatus.text)
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
                        }
                        Column(modifier = Modifier
                            .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally) {
                            Button(
                                onClick = {
                                    viewModel.navController.navigate(NavigationAdapter.Screen.Reports.createRoute(viewModel.project.id)) {

                                }},
                                modifier = Modifier
                                    .width(150.dp)
                                    .height(80.dp)
                                    .padding(top = 40.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color(
                                        0xFF3C6AB0
                                    )
                                ),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Text(
                                    text = "Reports",
                                    textAlign = TextAlign.Left,
                                    fontFamily = robotoFamily,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 12.sp,
                                    color = Color(0xFFFFFFFF)
                                )
                            }
                        }
                    }
                }

        }

        @Composable
        private fun Property(name : String, value : String) {
            Row(modifier = Modifier
                .padding(start = 18.dp, end = 18.dp, top = 12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
                Text(modifier = Modifier
                    .width(120.dp),
                    text = name,
                    textAlign = TextAlign.Left,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.W600,
                    fontSize = 16.sp,
                    color = Color(0xFF888888))
                Text(modifier = Modifier
                    .width(150.dp),
                    text = value,
                    textAlign = TextAlign.Left,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.W400,
                    fontSize = 16.sp,
                    color = Color(0xFF2D4855))
            }

        }
}