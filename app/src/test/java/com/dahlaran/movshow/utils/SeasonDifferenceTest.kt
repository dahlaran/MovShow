package com.dahlaran.movshow.utils

import com.dahlaran.movshow.models.Episode
import com.dahlaran.movshow.models.Image
import com.dahlaran.movshow.models.Season
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*

class SeasonDifferenceTest {
    private lateinit var seasonDiff: SeasonDifference

    @Before
    fun before() {
        seasonDiff = SeasonDifference()
    }

    @Test
    fun seasonNotDifferent() {
        val seasonItem = generateSeason()

        Assert.assertEquals(seasonDiff.areItemsTheSame(seasonItem, seasonItem), true)
        Assert.assertEquals(seasonDiff.areContentsTheSame(seasonItem, seasonItem), true)
    }

    @Test
    fun seasonDifferent() {
        val seasonItemOne = generateSeason()
        val seasonItemTwo = generateSeason()

        Assert.assertEquals(seasonDiff.areItemsTheSame(seasonItemOne, seasonItemTwo), false)
        Assert.assertEquals(seasonDiff.areContentsTheSame(seasonItemOne, seasonItemTwo), false)
    }

    @Test
    fun seasonContentDifferent() {
        val seasonItemOne = generateSeason()
        val seasonItemTwo = copyNumberChangeContent(seasonItemOne.seasonNumber)

        Assert.assertEquals(seasonDiff.areItemsTheSame(seasonItemOne, seasonItemTwo), true)
        Assert.assertEquals(seasonDiff.areContentsTheSame(seasonItemOne, seasonItemTwo), false)
    }

    @Test
    fun seasonNumberDifferent() {
        val seasonItemOne = generateSeason()
        val seasonItemTwo = copyContentChangeNumber(seasonItemOne)

        Assert.assertEquals(seasonDiff.areItemsTheSame(seasonItemOne, seasonItemTwo), false)
        Assert.assertEquals(seasonDiff.areContentsTheSame(seasonItemOne, seasonItemTwo), false)
    }

    private fun generateEpisode(id: Int? = null): Episode {
        return Episode(
            "airdate",
            "airstamp",
            "airtime",
            id ?: (Math.random() * 1000).toInt(),
            Image("medium", "original"),
            "name",
            (Math.random() * 100).toInt(),
            (Math.random() * 100).toInt(),
            (Math.random() * 100).toInt(),
            "summary",
            Date(),
            "url"
        )
    }

    private fun copyContentChangeNumber(season: Season): Season {
        return Season(
            (Math.random() * 100).toInt(),
            season.episodes
        )
    }

    private fun copyNumberChangeContent(number: Int): Season {
        return generateSeason(number)
    }

    private fun generateSeason(number: Int? = null): Season {
        val episodeNb = (Math.random() * 10).toInt()
        val episodeList = mutableListOf<Episode>()

        for (i in 0 until episodeNb) {
            episodeList.add(generateEpisode())
        }

        return Season(
            number ?: (Math.random() * 10).toInt(),
            episodeList
        )
    }
}