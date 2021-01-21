package com.dahlaran.movshow

import com.dahlaran.movshow.utils.DateUtils
import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class DateUtilsTest {
    @Test
    fun generateDateTime() {
        val dateString = "2019-07-26T12:00:00+00:00"
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

        assertEquals(dateFormat.parse(dateString), DateUtils.getEpisodeDateTime(dateString))
    }

    @Test
    fun generateDateEmpty() {
        val dateString = ""

        assertEquals(null, DateUtils.getEpisodeDateTime(dateString))
    }

    @Test
    fun generateDateIncorrect() {
        val dateString = "19QdD"

        assertEquals(null, DateUtils.getEpisodeDateTime(dateString))
    }

    @Test
    fun generateDateCheckNullable() {
        val dateString = null

        assertEquals(null, DateUtils.getEpisodeDateTime(dateString))
    }

    @Test
    fun getYearFromDateCheck() {
        val dateString = "2019-07-26T12:00:00+00:00"
        val date: Date? = DateUtils.getEpisodeDateTime(dateString)

        assert(date != null)
        assertEquals(2019, DateUtils.getYearFromDate(date!!))
    }

    @Test
    fun getMonthFromDateCheck() {
        val dateString = "2019-07-26T12:00:00+00:00"
        val date: Date? = DateUtils.getEpisodeDateTime(dateString)

        assert(date != null)
        assertEquals(7, DateUtils.getMonthFromDate(date!!))
    }

    @Test
    fun getDayFromDateCheck() {
        val dateString = "2019-07-26T12:00:00+00:00"
        val date: Date? = DateUtils.getEpisodeDateTime(dateString)

        assert(date != null)
        assertEquals(26, DateUtils.getDayOfWeekFromDate(date!!))
    }
}