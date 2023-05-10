package ru.hse.buildingapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.hse.buildingapp.navigation.NavigationAdapter
import ru.hse.buildingapp.robotoFamily
import ru.hse.buildingapp.ui.viewmodels.ProjectViewModel
import ru.hse.buildingapp.R

object ProjectScreen {
        @OptIn(ExperimentalMaterialApi::class)
        @Composable
        fun View(
            viewModel : ProjectViewModel
        ) {
            val isRefreshing = viewModel.isRefreshing
            val pullRefreshState: PullRefreshState = rememberPullRefreshState(isRefreshing, {viewModel.refresh()})
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF3C6AB0))
                        .pullRefresh(pullRefreshState)
                        .verticalScroll(rememberScrollState())
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
                    val imgState  = viewModel.projectImg
                    Image(
                        bitmap = imgState?.asImageBitmap() ?: ImageBitmap.imageResource(R.drawable.project_img_sample),
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
                            text = stringResource(id = R.string.general_information),
                            textAlign = TextAlign.Left,
                            fontFamily = robotoFamily,
                            fontWeight = FontWeight.W700,
                            fontSize = 18.sp,
                            color = Color(0xFF3C6AB0)
                        )
                        Column(modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .height(130.dp)) {
                            Property(name = stringResource(id = R.string.address), value = viewModel.project.address)
                            Property(name = stringResource(id = R.string.area), value = viewModel.project.area.toString())
                            Property(name = stringResource(id = R.string.budget), value = viewModel.project.budget.toString())
                            Property(name = stringResource(id = R.string.status), value = viewModel.project.projStatus.text)
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
                                    text = stringResource(id = R.string.reports),
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