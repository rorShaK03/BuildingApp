package ru.hse.buildingapp.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import ru.hse.buildingapp.network.RetrofitClient
import ru.hse.buildingapp.network.authmodels.RespState
import ru.hse.buildingapp.network.models.MeetingModel
import ru.hse.buildingapp.repository.MeetingsRepository
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class BookingViewModel : ViewModel() {

    var bookedMeetings : MutableList<Calendar> = mutableListOf()
    val now : Calendar = Calendar.getInstance() as Calendar
    val lastDisplay : Calendar = Calendar.getInstance() as Calendar
    val firstDisplay : Calendar = Calendar.getInstance() as Calendar
    var selected : Calendar by mutableStateOf(Calendar.getInstance())
    var year: String by mutableStateOf("Unknown")
        private set
    var month: String by mutableStateOf("Unknown")
        private set
    var day : Int by mutableStateOf(0)
        private set

    var state : RespState<Unit> by mutableStateOf(RespState.Loading())

    init {
        day = Calendar.DAY_OF_MONTH
        month = when(now.get(Calendar.MONTH)) {
            0 -> "January"
            1 -> "February"
            2 -> "March"
            3 -> "April"
            4 -> "May"
            5 -> "June"
            6 -> "July"
            7 -> "August"
            8 -> "September"
            9 -> "October"
            10 -> "November"
            11 -> "December"
            else -> "Unknown"
        }
        year = now.get(Calendar.YEAR).toString()
        while(firstDisplay.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)
            firstDisplay.add(Calendar.DATE, -1)
        selected.timeInMillis = now.timeInMillis
        selected.set(Calendar.HOUR_OF_DAY, 0)
        lastDisplay.timeInMillis = firstDisplay.timeInMillis
        lastDisplay.add(Calendar.DATE, 34)
        lastDisplay.set(Calendar.HOUR_OF_DAY, 23)
        lastDisplay.set(Calendar.MINUTE, 59)
        viewModelScope.launch {
            val resp = MeetingsRepository.meetings
            if(resp is RespState.Success)
                for(m : MeetingModel in resp.res) {
                    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
                    val booked : Calendar = Calendar.getInstance()
                    booked.time = sdf.parse(m.date) as Date
                    bookedMeetings.add(booked)
                }
            MeetingsRepository.updateData()
        }
    }

    fun equalToHours(a : Calendar, b : Calendar) : Boolean {
        return  a.get(Calendar.YEAR) == b.get(Calendar.YEAR) &&
                a.get(Calendar.MONTH) == b.get(Calendar.MONTH) &&
                a.get(Calendar.DAY_OF_MONTH) == b.get(Calendar.DAY_OF_MONTH) &&
                a.get(Calendar.HOUR_OF_DAY) == b.get(Calendar.HOUR_OF_DAY)
    }

    fun getDayWithOffset(offset: Int) : Calendar {
        val cal : Calendar = Calendar.getInstance() as Calendar
        cal.timeInMillis = firstDisplay.timeInMillis
        cal.add(Calendar.DATE, offset)
        return cal
    }

    fun setSelectedDate(d: Calendar) {
        val cal : Calendar = Calendar.getInstance() as Calendar
        cal.set(Calendar.HOUR_OF_DAY, selected.get(Calendar.HOUR_OF_DAY))
        cal.set(Calendar.YEAR, d.get(Calendar.YEAR))
        cal.set(Calendar.MONTH, d.get(Calendar.MONTH))
        cal.set(Calendar.DAY_OF_MONTH, d.get(Calendar.DAY_OF_MONTH))
        selected = cal
    }

    // 24-часовой формат
    fun setSelectedHour(hour: Int) {
        val cal : Calendar = Calendar.getInstance() as Calendar
        cal.set(Calendar.YEAR, selected.get(Calendar.YEAR))
        cal.set(Calendar.MONTH, selected.get(Calendar.MONTH))
        cal.set(Calendar.DAY_OF_MONTH, selected.get(Calendar.DAY_OF_MONTH))
        cal.set(Calendar.HOUR_OF_DAY, hour)
        selected = cal
    }


    fun addSelected(offs: Int) {
        val cal : Calendar = Calendar.getInstance() as Calendar
        cal.timeInMillis = selected.timeInMillis
        cal.add(Calendar.DATE, offs)
        if(cal.timeInMillis < now.timeInMillis) {
            cal.set(Calendar.YEAR, now.get(Calendar.YEAR))
            cal.set(Calendar.MONTH, now.get(Calendar.MONTH))
            cal.set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH))
        }
        else if(cal.timeInMillis > lastDisplay.timeInMillis) {
            cal.set(Calendar.YEAR, lastDisplay.get(Calendar.YEAR))
            cal.set(Calendar.MONTH, lastDisplay.get(Calendar.MONTH))
            cal.set(Calendar.DAY_OF_MONTH, lastDisplay.get(Calendar.DAY_OF_MONTH))
        }
        selected = cal
    }

    fun submit() {
        if(RetrofitClient.tokens.token.isEmpty()) {
            state = RespState.Unauthorized()
            return
        }
        try {
            viewModelScope.launch {
                var hourSel : String = selected.get(Calendar.HOUR_OF_DAY).toString()
                if(hourSel.toInt() < 10)
                    hourSel = "0$hourSel"

                var daySel : String = selected.get(Calendar.DAY_OF_MONTH).toString()
                if(daySel.toInt() < 10)
                    daySel = "0$daySel"

                var monthSel: String = (selected.get(Calendar.MONTH) + 1).toString()
                if(monthSel.toInt() < 10)
                    monthSel = "0$monthSel"

                val yearSel: String = selected.get(Calendar.YEAR).toString()

                val resp: Response<Unit> = RetrofitClient.retrofitService.setMeeting(
                    MeetingModel(
                        "Consultation",
                        "$yearSel-$monthSel-$daySel $hourSel:00:00"
                    )
                )
                if (resp.isSuccessful) {
                    state = RespState.Success(Unit)
                    MeetingsRepository.updateData()
                    val meetResp = MeetingsRepository.meetings
                    if(meetResp is RespState.Success)
                        for(m : MeetingModel in meetResp.res) {
                            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
                            val booked : Calendar = Calendar.getInstance()
                            booked.time = sdf.parse(m.date) as Date
                            bookedMeetings.add(booked)
                        }
                }
                else
                    state = RespState.UnknownError(resp.code())
            }
        } catch(e : IOException) {
            state = RespState.ConnectionError()
        }
    }
}