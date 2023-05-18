package ru.hse.buildingapp.ui.screens

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.hse.buildingapp.ui.viewmodels.BookingViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.hse.buildingapp.network.authmodels.RespState
import ru.hse.buildingapp.robotoFamily
import java.util.*

object BookingScreen {
    @Composable
    fun View(viewModel : BookingViewModel = viewModel()) {
        val state = viewModel.state
        val context = LocalContext.current
        if(state is RespState.Success) {
            viewModel.state = RespState.Loading()
            Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show()
        }
        else if(state is RespState.UnknownError) {
            viewModel.state = RespState.Loading()
            Toast.makeText(context, "Server error! Code ${state.code}", Toast.LENGTH_SHORT).show()
        }
        else if(state is RespState.Unauthorized) {
            viewModel.state = RespState.Loading()
            Toast.makeText(context, "Please, authorize before booking!", Toast.LENGTH_SHORT).show()
        }
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
                onClick = {if(viewModel.selected.get(Calendar.HOUR_OF_DAY) == 0)
                                Toast.makeText(context, "Please, choose the time!", Toast.LENGTH_SHORT).show()
                           else
                                viewModel.submit()}) {
                Text(
                    text = stringResource(id = R.string.book),
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
        val bookedMeetings = viewModel.bookedMeetings
        Column(modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .background(Color(0xFFFFFFFF), RoundedCornerShape(7))
            .border(1.dp, Color(0xFF3C6AB0), RoundedCornerShape(7))) {
            Text(modifier = Modifier
                .width(100.dp)
                .padding(top = 12.dp, bottom = 20.dp, start = 16.dp),
                text = stringResource(id = R.string.select_time),
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
                    val variant = Calendar.getInstance()
                    variant.timeInMillis = viewModel.selected.timeInMillis
                    variant.set(Calendar.HOUR_OF_DAY, h)
                    TimeVariant(h, isAvailable = !bookedMeetings.any{viewModel.equalToHours(it, variant)}, viewModel)
                }
            }
        }
    }

    @Composable
    private fun TimeVariant(hour : Int, isAvailable : Boolean, viewModel: BookingViewModel) {
        Row(modifier = (
        if (isAvailable) Modifier
            .fillMaxWidth()
            .height(37.dp)
            .clickable { viewModel.setSelectedHour(hour) }
            .background(
                if (viewModel.selected.get(Calendar.HOUR_OF_DAY) == hour)
                    Color(0x213C6AB0)
                else
                    Color(0x00FFFFFF)
            )
        else Modifier
            .fillMaxWidth()
            .height(37.dp)
            .background(
                if (viewModel.selected.get(Calendar.HOUR_OF_DAY) == hour)
                    Color(0x213C6AB0)
                else
                    Color(0x00FFFFFF)
            ))) {
            Row(modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Row(
                    modifier = Modifier
                        .width(70.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier
                            .width(40.dp),
                        text = if (hour <= 12) "${hour}:00" else "${(hour - 12)}:00",
                        textAlign = TextAlign.Left,
                        fontFamily = robotoFamily,
                        fontWeight = FontWeight.W500,
                        fontSize = 14.sp,
                        color = if (isAvailable) Color(0xFF3C6AB0) else Color(0xFFC4C4C4)
                    )
                    Text(
                        modifier = Modifier
                            .width(40.dp),
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
                    text = if (isAvailable) stringResource(id = R.string.available) else stringResource(R.string.booked),
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
                                .width(20.dp)
                                .padding(start = 5.dp),
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
                    Color(0x00FFFFFF),
                disabledBackgroundColor = Color(0x00FFFFFF)
            ),
            elevation = ButtonDefaults.elevation(0.dp),
            contentPadding = PaddingValues(0.dp),
            shape = CircleShape,
            enabled = d >= viewModel.now
        ) {
            if(d >= viewModel.now) {
                Text(
                    modifier = Modifier
                        .width(20.dp),
                    text = date.get(Calendar.DAY_OF_MONTH).toString(),
                    textAlign = TextAlign.Center,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.W700,
                    fontSize = 12.sp,
                    color =
                    if (date.get(Calendar.MONTH) == viewModel.selected.get(Calendar.MONTH) &&
                        date.get(Calendar.DAY_OF_MONTH) == viewModel.selected.get(Calendar.DAY_OF_MONTH)
                    )
                        Color(0xFFFFFFFF)
                    else if (date.get(Calendar.MONTH) == viewModel.now.get(Calendar.MONTH))
                        Color(0xFF3C6AB0)
                    else
                        Color(0xFF9BA7CF)
                )
            }
        }

    }
}