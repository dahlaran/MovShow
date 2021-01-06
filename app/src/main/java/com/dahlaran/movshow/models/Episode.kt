package com.dahlaran.movshow.models

import android.text.Spanned
import com.dahlaran.movshow.R
import com.dahlaran.movshow.utils.DateUtils
import com.dahlaran.movshow.utils.HtmlUtils
import com.dahlaran.movshow.view.application.MovShowApplication
import java.util.*

data class Episode(
    val airdate: String,
    val airstamp: String,
    val airtime: String,
    val id: Int,
    val image: Image,
    val name: String,
    val number: Int,
    val runtime: Int,
    val season: Int,
    val summary: String,
    var time: Date?,
    val url: String
) {
    private var summarySpanned: Spanned? = null

    fun makeEpisodeDetail(): String {
        return "${season}x${number}"
    }

    fun initTimeOfApparition() {
        time = DateUtils.getEpisodeDateTime(airstamp)
    }

    fun makeEpisodeDateText(): String {
        return time?.run {
            String.format("%02d", DateUtils.getYearFromDate(this)) + "-" +
                    String.format("%02d", DateUtils.getMonthFromDate(this)) + "-" +
                    String.format("%02d", DateUtils.getDayOfWeekFromDate(this))
        } ?: ""
    }

    fun getTextSummary(): String {
        if (summarySpanned == null) {
            summarySpanned = HtmlUtils.convertHtmlTextToShowText(summary)
        }
        if (summarySpanned?.isNotEmpty() == true) {
            return summarySpanned.toString()
        }
        return MovShowApplication.context.get()?.getString(R.string.episode_any_summary) ?: ""
    }
}