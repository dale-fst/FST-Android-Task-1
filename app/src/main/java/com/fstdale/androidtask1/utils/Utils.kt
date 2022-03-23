package com.fstdale.androidtask1.utils

import java.text.SimpleDateFormat
import java.util.*

object Utils {
    fun convertDate(date: String): String {
        val dateString = SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy", Locale.ENGLISH).apply {
            isLenient = true
        }.parse(date)
        return SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.US).format(dateString)
    }
}