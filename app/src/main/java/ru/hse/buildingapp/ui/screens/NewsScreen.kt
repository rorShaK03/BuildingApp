package ru.hse.buildingapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.hse.buildingapp.robotoFamily
import ru.hse.buildingapp.ui.viewmodels.NewsViewModel

object NewsScreen {
        @Composable
        fun View(
            viewModel : NewsViewModel = viewModel()
        ) {
            Column(
                Modifier
                    .fillMaxHeight()
                    .background(color = Color(0xFFF0F0F0), RoundedCornerShape(20.dp))
                    .padding(start = 20.dp, end = 20.dp, top = 16.dp)) {
                /*
                NewsCard(title = "Palisades Development Company",
                        text = "Palisades Development Company LLC was incorporated in 2015 to market, manage, lease, buy and sell land in The Palisades ... ",
                        imageId = R.drawable.news_pdc, date = "Oct - 2019")
                Spacer(Modifier.height(20.dp))
                NewsCard(title = "Dubai Investments Industries",
                    text = "Dubai Investments Industries targets both new and established industries and has a strong commitment ...",
                    imageId = R.drawable.news_dii, date = "Oct - 2019")
                Spacer(Modifier.height(20.dp))
                NewsCard(title = " Dubai Cranes and Technical Services LTD",
                    text = "Dubai Cranes & Technical Services Ltd., established in 2006, is one of the top manufacturers of ...",
                    imageId = R.drawable.news_dcts, date = "Oct - 2019")
                Spacer(Modifier.height(20.dp))
                 */
                for(news in viewModel.newsUiState) {
                    NewsCard(
                        title = news.title,
                        text = news.text,
                        imageId = news.iconId,
                        date = news.date
                    )
                    Spacer(Modifier.height(20.dp))
                }
            }
        }

        @Composable
        fun NewsCard(title: String, text: String, imageId: Int, date: String) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFFFFFF), RoundedCornerShape(10.dp))
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly) {
                Column() {
                    Text(text = date,
                        textAlign = TextAlign.Left,
                        fontFamily = robotoFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 10.sp,
                        color = Color(0xFF7D7D7D)
                    )
                    Spacer(Modifier.height(10.dp))
                    Image(modifier = Modifier.size(60.dp), painter = painterResource(id = imageId), contentDescription = null)
                }
                Column(Modifier.fillMaxWidth(0.7f)) {
                    Text(text = title,
                        textAlign = TextAlign.Left,
                        fontFamily = robotoFamily,
                        fontWeight = FontWeight.W700,
                        fontSize = 13.sp,
                        color = Color(0xFF3C6AB0)
                    )
                    Spacer(Modifier.height(15.dp))
                    Text(text = text,
                        textAlign = TextAlign.Left,
                        fontFamily = robotoFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 13.sp,
                        color = Color(0xFF878787)
                    )
                }
            }
        }
}