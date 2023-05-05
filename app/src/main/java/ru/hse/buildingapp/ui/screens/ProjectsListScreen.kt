package ru.hse.buildingapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.hse.buildingapp.network.models.ProjectStatus
import ru.hse.buildingapp.robotoFamily
import ru.hse.buildingapp.ui.viewmodels.ProjectsListViewModel

object ProjectsListScreen {

        @Composable
        fun View(navController: NavHostController,
                 viewModel: ProjectsListViewModel = viewModel()) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .background(Color(0xFF3C6AB0))) {
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
                    for(project in viewModel.projects) {
                        ProjectCard(
                            title = project.projectName,
                            imageId = project.iconId,
                            status = project.status
                            ) {
                                navController.navigate("project/${project.id}") {
                                    launchSingleTop = true
                                    restoreState = true

                            }
                            }
                        Divider()
                        Spacer(Modifier.height(20.dp))
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
                    val statusString: String = when (status) {
                        ProjectStatus.PREPARING -> "Preparing"
                        ProjectStatus.ON_PROGRESS -> "On progress"
                        ProjectStatus.DONE -> "Done"
                    }
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