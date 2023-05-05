package ru.hse.buildingapp.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import ru.hse.buildingapp.R
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.hse.buildingapp.ui.viewmodels.BookingViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.hse.buildingapp.robotoFamily
import java.util.*

object BookingScreen {
    @Composable
    fun View(viewModel : BookingViewModel = viewModel()) {
        Row(modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(modifier = Modifier
                .width(80.dp),
                text = "${viewModel.month} ${viewModel.year}",
                textAlign = TextAlign.Left,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.W400,
                fontSize = 16.sp,
                color = Color(0xFF585858)
            )
            Row(modifier = Modifier
                .width(70.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Button(modifier = Modifier
                    .size(30.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFFFFFF)),
                    onClick = {viewModel.addSelected(-1)},
                    contentPadding = PaddingValues(0.dp),
                    elevation = ButtonDefaults.elevation(0.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.left_arrow_btn),
                        contentDescription = null)
                }
                Button(modifier = Modifier
                    .size(30.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFFFFFF)),
                    onClick = {viewModel.addSelected(1)},
                    contentPadding = PaddingValues(0.dp),
                    elevation = ButtonDefaults.elevation(0.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.right_arrow_btn),
                        contentDescription = null)
                }
            }
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF6F6F6), RoundedCornerShape(7, 7, 0, 0))) {
            CalendarView(viewModel)
            TimePicker(viewModel)
        }
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Button(modifier = Modifier
                .width(170.dp)
                .height(40.dp),
                shape = RoundedCornerShape(7.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF3C6AB0)),
                onClick = {}) {
                Text(
                    text = "Book",
                    textAlign = TextAlign.Center,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.W400,
                    fontSize = 20.sp,
                    color = Color(0xFFFFFFFF)
                )
            }
        }
    }

    @Composable
    private fun TimePicker(viewModel: BookingViewModel) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color(0xFFFFFFFF), RoundedCornerShape(7))
            .border(1.dp, Color(0xFF3C6AB0), RoundedCornerShape(7))) {
            Text(modifier = Modifier
                .width(100.dp)
                .padding(top = 12.dp, bottom = 20.dp, start = 16.dp),
                text = "Select time",
                textAlign = TextAlign.Left,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.W400,
                fontSize = 16.sp,
                color = Color(0xFF585858))
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
                .verticalScroll(rememberScrollState())) {
                for (h in 9..21) {
                    Divider()
                    TimeVariant(h, isAvailable = true, viewModel)
                }
            }
        }
    }

    @Composable
    private fun TimeVariant(hour : Int, isAvailable : Boolean, viewModel: BookingViewModel) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .clickable { viewModel.setSelectedHour(hour) }
            .background(
                if (viewModel.selected.get(Calendar.HOUR_OF_DAY) == hour)
                    Color(0x213C6AB0)
                else
                    Color(0x00FFFFFF)
            )) {
            Row(modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Row(
                    modifier = Modifier
                        .width(60.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier
                            .width(40.dp),
                        text = if (hour <= 12) "${hour.toString()}:00" else "${(hour - 12).toString()}:00",
                        textAlign = TextAlign.Left,
                        fontFamily = robotoFamily,
                        fontWeight = FontWeight.W500,
                        fontSize = 14.sp,
                        color = if (isAvailable) Color(0xFF3C6AB0) else Color(0xFFC4C4C4)
                    )
                    Text(
                        modifier = Modifier
                            .width(30.dp),
                        text = if (hour < 12) "am" else "pm",
                        textAlign = TextAlign.Left,
                        fontFamily = robotoFamily,
                        fontWeight = FontWeight.W500,
                        fontSize = 14.sp,
                        color = if (isAvailable) Color(0xFF3C6AB0) else Color(0xFFC4C4C4)
                    )
                }
                Text(
                    modifier = Modifier
                        .width(60.dp),
                    text = if (isAvailable) "available" else "booked",
                    textAlign = TextAlign.Left,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.W500,
                    fontSize = 14.sp,
                    color = if (isAvailable) Color(0xFF3C6AB0) else Color(0xFFC4C4C4)
                )
            }
        }
    }


    @Composable
    private fun CalendarView(viewModel: BookingViewModel) {

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp, top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val days: List<String> = listOf("S", "M", "T", "W", "T", "F", "S")
                    for (day in days) {
                        Text(
                            modifier = Modifier
                                .width(20.dp),
                            text = day,
                            textAlign = TextAlign.Left,
                            fontFamily = robotoFamily,
                            fontWeight = FontWeight.W700,
                            fontSize = 12.sp,
                            color = Color(0xFF494949)
                        )
                    }
                }
                for (i in 0..4) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 7.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        for (j in 0..6) {
                           CalendarViewDay(viewModel, viewModel.getDayWithOffset(i * 7 + j))
                        }
                    }
                }
            }
    }

    @Composable
    private fun CalendarViewDay(viewModel : BookingViewModel, d : Calendar) {
        val date : Calendar by remember{ mutableStateOf(d) }
        Button(modifier = Modifier
            .size(30.dp),
            onClick = { viewModel.setSelectedDate(date) },
            colors = ButtonDefaults.buttonColors(
                backgroundColor =
                if(date.get(Calendar.MONTH) == viewModel.selected.get(Calendar.MONTH) &&
                    date.get(Calendar.DAY_OF_MONTH) == viewModel.selected.get(Calendar.DAY_OF_MONTH))
                    Color(0xFF3C6AB0)
                else
                    Color(0x00FFFFFF)
            ),
            elevation = ButtonDefaults.elevation(0.dp),
            contentPadding = PaddingValues(0.dp),
            shape = CircleShape
        ) {
            Text(
                modifier = Modifier
                    .width(20.dp),
                text = date.get(Calendar.DAY_OF_MONTH).toString(),
                textAlign = TextAlign.Center,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.W700,
                fontSize = 12.sp,
                color =
                if(date.get(Calendar.MONTH) == viewModel.selected.get(Calendar.MONTH) &&
                    date.get(Calendar.DAY_OF_MONTH) == viewModel.selected.get(Calendar.DAY_OF_MONTH))
                    Color(0xFFFFFFFF)
                else if (date.get(Calendar.MONTH) == viewModel.now.get(Calendar.MONTH))
                    Color(0xFF3C6AB0)
                else
                    Color(0xFF9BA7CF)
            )
        }

    }
}