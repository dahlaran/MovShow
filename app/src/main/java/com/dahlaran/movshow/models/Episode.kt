package com.dahlaran.movshow.models

import com.dahlaran.movshow.utils.DateUtils
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
    fun makeEpisodeDetail(): String {
        return "${season}x${number}"
    }

    fun initTimeOfApparition() {
        time = DateUtils.getEpisodeDateTime(airstamp)
    }
}