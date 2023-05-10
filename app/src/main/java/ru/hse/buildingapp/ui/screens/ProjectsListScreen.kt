package ru.hse.buildingapp.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.hse.buildingapp.navigation.NavigationAdapter
import ru.hse.buildingapp.network.RetrofitClient
import ru.hse.buildingapp.network.authmodels.RespState
import ru.hse.buildingapp.network.models.ProjectStatus
import ru.hse.buildingapp.robotoFamily
import ru.hse.buildingapp.ui.viewmodels.ProjectsListViewModel

object ProjectsListScreen {

        @OptIn(ExperimentalMaterialApi::class)
        @Composable
        fun View(viewModel: ProjectsListViewModel) {
            val isRefreshing = viewModel.isRefreshing
            val pullRefreshState = rememberPullRefreshState(isRefreshing, {viewModel.refresh()})
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .background(Color(0xFF3C6AB0))
                    .pullRefresh(pullRefreshState)
                    .verticalScroll(rememberScrollState())) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 25.dp),
                horizontalArrangement = Arrangement.SpaceEvenly) {
                    LabeledStatusBar(label = "Preparing", progress = viewModel.preparingFraction)
                    LabeledStatusBar(label = "On progress", progress = viewModel.onProgressFraction)
                    LabeledStatusBar(label = "Done", progress = viewModel.doneFraction)
                }
                Column(
                    Modifier
                        .fillMaxSize()
                        .background(
                            Color(0xFFFFFFFF),
                            RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                        )
                        .padding(top = 30.dp)) {
                    if(RetrofitClient.tokens.token.isEmpty()) {
                        Row(Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically) {
                            Text( modifier = Modifier.fillMaxWidth(),
                                text = "Please, authorize before watching projects list.",
                                textAlign = TextAlign.Center,
                                fontFamily = robotoFamily,
                                fontWeight = FontWeight.Medium,
                                fontSize = 13.sp,
                                color = Color(0xFFF70707),
                            )
                        }
                    }
                    val state = viewModel.projects
                    if(state is RespState.Success) {
                        for (project in state.res.values) {
                            ProjectCard(
                                title = project.projectName,
                                imageId = project.iconId,
                                status = project.projStatus
                            ) {
                                viewModel.navController.navigate(
                                    NavigationAdapter.Screen.Project.createRoute(
                                        project.id
                                    )
                                )
                            }
                            Divider()
                            Spacer(Modifier.height(20.dp))
                        }
                    }
                    else if(state is RespState.UnknownError) {
                        Row(Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically) {
                            Text( modifier = Modifier.fillMaxWidth(),
                                text = "Unknown server error. Error code ${state.code}.",
                                textAlign = TextAlign.Center,
                                fontFamily = robotoFamily,
                                fontWeight = FontWeight.Medium,
                                fontSize = 13.sp,
                                color = Color(0xFFF70707),
                            )
                        }
                    }
                    else if(state is RespState.ConnectionError) {
                        Row(Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically) {
                            Text( modifier = Modifier.fillMaxWidth(),
                                text = "Server is unavailable. Please, check, your internet connection",
                                textAlign = TextAlign.Center,
                                fontFamily = robotoFamily,
                                fontWeight = FontWeight.Medium,
                                fontSize = 13.sp,
                                color = Color(0xFFF70707),
                            )
                        }
                    }
                }
            }
        }

        @Composable
        fun LabeledStatusBar(label: String, progress: Float) {
            Column() {
                Text(text = "${(progress * 100).toInt()}%",
                    textAlign = TextAlign.Left,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = Color(0xFFFFFFFF),
                    modifier = Modifier.padding(bottom = 7.dp)
                )
                CircularProgressIndicator(progress = progress,
                color = Color(0xFFFFFFFF))
                Spacer(Modifier.height(10.dp))
                Text(text = label,
                    textAlign = TextAlign.Left,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = Color(0xFFFFFFFF)
                )
            }
        }

        @Composable
        fun ProjectCard(title: String,
                        imageId: Int,
                        status: ProjectStatus,
                        onClick: () -> Unit
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
                    .clickable { onClick() },
                horizontalArrangement = Arrangement.SpaceEvenly) {
                Column(Modifier.fillMaxWidth(0.8f)) {
                    Text(text = title,
                        textAlign = TextAlign.Left,
                        fontFamily = robotoFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        color = Color(0xFF4F4F4F)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    val statusString: String = status.text
                    Text(text = statusString,
                        textAlign = TextAlign.Left,
                        fontFamily = robotoFamily,
                        fontWeight = FontWeight.W700,
                        fontSize = 14.sp,
                        color = Color(0xFFEA6B6B)
                    )
                }
                Image(painter = painterResource(id = imageId),
                contentDescription = null)
            }
        }
}