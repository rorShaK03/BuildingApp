package ru.hse.buildingapp.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.util.*

class BookingViewModel : ViewModel() {

    val now : Calendar = Calendar.getInstance()
    val lastDisplay : Calendar = Calendar.getInstance()
    val firstDisplay : Calendar = Calendar.getInstance()
    var selected : Calendar by mutableStateOf(Calendar.getInstance())
    var year: String by mutableStateOf("Unknown")
        private set
    var month: String by mutableStateOf("Unkonown")
        private set
    var day : Int by mutableStateOf(0)
        private set

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
        lastDisplay.timeInMillis = firstDisplay.timeInMillis
        lastDisplay.add(Calendar.DATE, 34)
        lastDisplay.set(Calendar.HOUR_OF_DAY, 23)
        lastDisplay.set(Calendar.MINUTE, 59)
    }

    fun getDayWithOffset(offset: Int) : Calendar {
        val cal : Calendar = Calendar.getInstance()
        cal.timeInMillis = firstDisplay.timeInMillis
        cal.add(Calendar.DATE, offset)
        return cal
    }

    fun setSelectedDate(d: Calendar) {
        val cal : Calendar = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, selected.get(Calendar.HOUR_OF_DAY))
        cal.set(Calendar.YEAR, d.get(Calendar.YEAR))
        cal.set(Calendar.MONTH, d.get(Calendar.MONTH))
        cal.set(Calendar.DAY_OF_MONTH, d.get(Calendar.DAY_OF_MONTH))
        selected = cal
    }

    // 24-часовой формат
    fun setSelectedHour(hour: Int) {
        val cal : Calendar = Calendar.getInstance()
        cal.set(Calendar.YEAR, selected.get(Calendar.YEAR))
        cal.set(Calendar.MONTH, selected.get(Calendar.MONTH))
        cal.set(Calendar.DAY_OF_MONTH, selected.get(Calendar.DAY_OF_MONTH))
        cal.set(Calendar.HOUR_OF_DAY, hour)
        selected = cal
    }


    fun addSelected(offs: Int) {
        val cal : Calendar = Calendar.getInstance()
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
}