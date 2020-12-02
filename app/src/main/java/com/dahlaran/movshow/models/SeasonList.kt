package com.dahlaran.movshow.models

import java.util.*
import kotlin.collections.ArrayList

data class SeasonList(
    val seasons: List<Season>?,
    val lastEpisode: Episode?,
    val nextEpisode: Episode?
) {
    companion object {
        fun fromEpisodeList(episodes: List<Episode>?): SeasonList? {
            if (episodes == null) {
                return null
            }
            val listOfSeason = ArrayList<Season>()
            var tmpListOfEpisode = ArrayList<Episode>()
            var currentSeason = 1
            episodes.forEach {
                if (it.season != currentSeason) {
                    if (tmpListOfEpisode.isNotEmpty()) {
                        listOfSeason.add(Season(currentSeason, tmpListOfEpisode))
                    }
                    currentSeason++
                    tmpListOfEpisode = ArrayList()
                }
                tmpListOfEpisode.add(it)
            }
            if (tmpListOfEpisode.isNotEmpty()) {
                listOfSeason.add(Season(currentSeason, tmpListOfEpisode))
            }

            val now = Calendar.getInstance().time

            // Initialize apparition date
            episodes.map { if (it.time == null) it.initTimeOfApparition() }

            val latestEpisode = episodes.lastOrNull { it.time != null && it.time!! <= now }
            val nextEpisode = episodes.firstOrNull { it.time != null && it.time!! > now }

            return SeasonList(listOfSeason, latestEpisode, nextEpisode)
        }
    }
}

