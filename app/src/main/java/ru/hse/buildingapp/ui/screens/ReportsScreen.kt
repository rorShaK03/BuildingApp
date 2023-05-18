package ru.hse.buildingapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.hse.buildingapp.network.authmodels.RespState
import ru.hse.buildingapp.ui.viewmodels.ReportsViewModel
import ru.hse.buildingapp.robotoFamily

object ReportsScreen {
    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun View(viewModel : ReportsViewModel) {
        val isRefreshing = viewModel.isRefreshing
        val pullRefreshState = rememberPullRefreshState(isRefreshing, {viewModel.refresh()})
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 10.dp, horizontal = 13.dp)
            .verticalScroll(rememberScrollState())
            .pullRefresh(pullRefreshState)) {
            val state = viewModel.reports
            if(state is RespState.Success) {
                for (report in state.res.values) {
                    ReportCard(title = report.title, text = report.text, date = "")
                    Spacer(Modifier.height(20.dp))
                }
            }
            else if(state is RespState.UnknownError) {
                Row(Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Unknown server error. Error code ${state.code}.",
                        textAlign = TextAlign.Left,
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
                    Text(
                        text = "Server is unavailable. Please, check, your internet connection",
                        textAlign = TextAlign.Left,
                        fontFamily = robotoFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 13.sp,
                        color = Color(0xFFF70707),
                    )
                }
            }
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
                .width(200.dp)
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