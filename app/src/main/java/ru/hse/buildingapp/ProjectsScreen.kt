package ru.hse.buildingapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ProjectsScreen private constructor() {
    companion object {

        enum class ProjectStatus {
            PREPARING, ON_PROGRESS, DONE
        }

        @Composable
        fun view() {
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
                    LabeledStatusBar(label = "Preparing", progress = 0.25f)
                    LabeledStatusBar(label = "On progress", progress = 0.5f)
                    LabeledStatusBar(label = "Done", progress = 0.75f)
                }
                Column(
                    Modifier
                        .fillMaxSize()
                        .background(
                            Color(0xFFFFFFFF),
                            RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                        )
                        .padding(top = 30.dp)) {
                    ProjectCard(title = "Modification of al Ruwaiyyah Dining \n" +
                            "4th Floor Evp Offices", imageId = R.drawable.projects_screen_lifting_crane_icon, status = ProjectStatus.PREPARING)
                    Divider()
                    Spacer(Modifier.height(20.dp))
                    ProjectCard(title = "Building a model school with a\n" +
                            "capacity of 500 students", imageId = R.drawable.projects_screen_house_icon, status = ProjectStatus.DONE)
                    Divider()
                    Spacer(Modifier.height(20.dp))
                    ProjectCard(title = "Solid waste removal station", imageId = R.drawable.projects_screen_lifting_crane_icon, status = ProjectStatus.ON_PROGRESS)
                }
            }
        }

        @Composable
        fun LabeledStatusBar(label: String, progress: Float) {
            Column() {
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
        fun ProjectCard(title: String, imageId: Int, status: ProjectStatus) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 10.dp),
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
}