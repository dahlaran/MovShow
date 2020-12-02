package com.dahlaran.movshow.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    private val episodeDateTimeFormatter: SimpleDateFormat by lazy {
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    }

    fun getEpisodeDateTime(airstamp: String?): Date? {
        if (airstamp == null) {
            return null
        }
        return try {
            episodeDateTimeFormatter.parse(airstamp)
        } catch (e: ParseException) {
            null
        }
    }

    fun getYearFromDate(date: Date): Int {
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar[Calendar.YEAR]
    }

    fun getMonthFromDate(date: Date): Int {
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar[Calendar.MONTH]
    }

    fun getDayOfWeekFromDate(date: Date): Int {
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar[Calendar.DAY_OF_MONTH]
    }
}