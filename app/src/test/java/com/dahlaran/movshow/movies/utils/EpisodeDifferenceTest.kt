package com.dahlaran.movshow.movies.utils

import com.dahlaran.movshow.movies.models.Episode
import com.dahlaran.movshow.movies.models.sendByApi.Image
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*

class EpisodeDifferenceTest {
    private lateinit var episodeDiff: EpisodeDifference

    @Before
    fun before() {
        episodeDiff = EpisodeDifference()
    }

    @Test
    fun episodeNotDifferent() {
        val episodeItem = generateEpisode()

        Assert.assertEquals(episodeDiff.areItemsTheSame(episodeItem, episodeItem), true)
        Assert.assertEquals(episodeDiff.areContentsTheSame(episodeItem, episodeItem), true)
    }

    @Test
    fun episodeDifferent() {
        val episodeItemOne = generateEpisode()
        val episodeItemTwo = generateEpisode()

        Assert.assertEquals(episodeDiff.areItemsTheSame(episodeItemOne, episodeItemTwo), false)
        Assert.assertEquals(episodeDiff.areContentsTheSame(episodeItemOne, episodeItemTwo), false)
    }

    @Test
    fun episodeContentDifferent() {
        val episodeItemOne = generateEpisode()
        val episodeItemTwo = copyIdChangeContent(episodeItemOne.id)

        Assert.assertEquals(episodeDiff.areItemsTheSame(episodeItemOne, episodeItemTwo), true)
        Assert.assertEquals(episodeDiff.areContentsTheSame(episodeItemOne, episodeItemTwo), false)
    }

    @Test
    fun episodeIdDifferent() {
        val episodeItemOne = generateEpisode()
        val episodeItemTwo = copyContentChangeId(episodeItemOne)

        Assert.assertEquals(episodeDiff.areItemsTheSame(episodeItemOne, episodeItemTwo), false)
        Assert.assertEquals(episodeDiff.areContentsTheSame(episodeItemOne, episodeItemTwo), false)
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

    private fun copyContentChangeId(episode: Episode): Episode {
        return Episode(
            episode.airdate,
            episode.airstamp,
            episode.airtime,
            (Math.random() * 1000).toInt(),
            episode.image,
            episode.name,
            episode.number,
            episode.runtime,
            episode.season,
            episode.summary,
            episode.time,
            episode.url
        )
    }

    private fun copyIdChangeContent(id: Int): Episode {
        return generateEpisode(id)
    }
}