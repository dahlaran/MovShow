package com.dahlaran.movshow.movies.models

import com.dahlaran.movshow.movies.models.sendByApi.Image
import io.mockk.every
import io.mockk.mockkStatic
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.Days
import org.joda.time.tz.UTCProvider
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.abs


class SeasonListTest {
    private var dateMocked: DateTime? = null

    @Before
    fun before() {
        DateTimeZone.setProvider(UTCProvider())
        dateMocked = DateTime.parse("2010-06-30T01:20+02:00")
        mockkStatic(DateTime::class)
        every { DateTime.now() } returns dateMocked
    }

    @Test
    fun fromEpisodeListOneSeasonTest() {
        val episodeNb = (Math.random() * 10).toInt()
        val episodeList = mutableListOf<Episode>()

        for (i in 0 until episodeNb) {
            episodeList.add(generateEpisode())
        }

        val seasonList = SeasonList.fromEpisodeList(episodeList)

        // Check if not null
        assert(seasonList != null)

        // Counter of all episodes
        var episodeCounter: Int = 0

        seasonList!!.seasons!!.forEach { season ->
            season.episodes.forEach { episode ->
                assert(episode.season == season.seasonNumber)
                ++episodeCounter
            }
        }
        // Check if all episode are presents
        Assert.assertEquals(episodeNb, episodeCounter)
    }

    @Test
    fun fromEpisodeListMultipleSeasonTest() {
        val seasonNb = (Math.random() * 5).toInt()
        val episodeNbBySeason = (Math.random() * 10).toInt()
        val episodeList = mutableListOf<Episode>()

        for (i in 0 until seasonNb) {
            for (j in 0 until episodeNbBySeason) {
                episodeList.add(generateEpisode(i))
            }
        }

        val seasonList = SeasonList.fromEpisodeList(episodeList)

        // Check if not null
        assert(seasonList != null)

        // Counter of all episodes
        var episodeCounter: Int = 0

        seasonList!!.seasons!!.forEach { season ->
            season.episodes.forEach { episode ->
                assert(episode.season == season.seasonNumber)
                ++episodeCounter
            }
        }
        // Check if all episode are presents
        val totalEpisodeExpected = episodeNbBySeason * seasonNb
        Assert.assertEquals(totalEpisodeExpected, episodeCounter)
    }

    @Test
    fun numberOfDayBeforeNextEpisodeTest() {
        val episodeList = mutableListOf<Episode>()

        episodeList.add(generateEpisode())

        val seasonList = SeasonList.fromEpisodeList(episodeList)

        // Check if not null
        assert(seasonList != null)

        // Check if the value are the same (numberOfDayBeforeNextEpisode is there to not call multiple times getDayBeforeNextEpisode())
        val dayNumber = seasonList!!.numberOfDayBeforeNextEpisode
        val dayExpected = seasonList.getDayBeforeNextEpisode()
        Assert.assertEquals(dayExpected, dayNumber)
    }

    @Test
    fun getDayBeforeNextEpisodeTestUsingJoda() {
        val episodeList = mutableListOf<Episode>()

        episodeList.add(generateEpisode(0, 1000))

        val seasonList = SeasonList.fromEpisodeList(episodeList)

        // Check if not null
        assert(seasonList != null)

        // Check if the number of day is accurate (Joda Test)
        val dayCalcByJoda = Days.daysBetween(dateMocked, DateTime(episodeList[0].time)).days
        Assert.assertEquals(seasonList!!.numberOfDayBeforeNextEpisode, dayCalcByJoda)
    }

    @Test
    fun getDayBeforeNextEpisodeTestUsingTimeUnit() {
        val episodeList = mutableListOf<Episode>()

        episodeList.add(generateEpisode(0, 1000))

        val seasonList = SeasonList.fromEpisodeList(episodeList)

        // Check if not null
        assert(seasonList != null)

        // Check if the number of day is accurate (TimeUnit Test)
        val diffInMillies: Long = abs(episodeList[0].time!!.time - dateMocked!!.millis)
        val diff: Long = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS)

        Assert.assertEquals(seasonList!!.numberOfDayBeforeNextEpisode, diff.toInt())
    }

    private fun generateEpisode(seasonNumber: Int = 0, timeToAdd: Long = 0): Episode {
        return Episode(
            "airdate",
            "airstamp",
            "airtime",
            (Math.random() * 1000).toInt(),
            Image("medium", "original"),
            "name",
            (Math.random() * 100).toInt(),
            (Math.random() * 100).toInt(),
            seasonNumber,
            "summary",
            Date(System.currentTimeMillis() + timeToAdd),
            "url"
        )
    }
}