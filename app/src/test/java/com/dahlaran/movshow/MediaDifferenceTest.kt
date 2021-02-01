package com.dahlaran.movshow

import com.dahlaran.movshow.models.*
import com.dahlaran.movshow.utils.MediaDifference
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MediaDifferenceTest {
    private lateinit var mediaDiff: MediaDifference

    @Before
    fun before() {
        mediaDiff = MediaDifference()
    }

    @Test
    fun mediaNotDifferent() {
        val mediaItem = generateMedia()

        Assert.assertEquals(mediaDiff.areItemsTheSame(mediaItem, mediaItem), true)
        Assert.assertEquals(mediaDiff.areContentsTheSame(mediaItem, mediaItem), true)
    }

    @Test
    fun mediaDifferent() {
        val mediaItemOne = generateMedia()
        val mediaItemTwo = generateMedia()

        Assert.assertEquals(mediaDiff.areItemsTheSame(mediaItemOne, mediaItemTwo), false)
        Assert.assertEquals(mediaDiff.areContentsTheSame(mediaItemOne, mediaItemTwo), false)
    }

    @Test
    fun mediaContentDifferent() {
        val mediaItemOne = generateMedia()
        val mediaItemTwo = copyIdChangeContent(mediaItemOne.id)

        Assert.assertEquals(mediaDiff.areItemsTheSame(mediaItemOne, mediaItemTwo), true)
        Assert.assertEquals(mediaDiff.areContentsTheSame(mediaItemOne, mediaItemTwo), false)
    }

    @Test
    fun mediaIdDifferent() {
        val mediaItemOne = generateMedia()
        val mediaItemTwo = copyContentChangeId(mediaItemOne)

        Assert.assertEquals(mediaDiff.areItemsTheSame(mediaItemOne, mediaItemTwo), false)
        Assert.assertEquals(mediaDiff.areContentsTheSame(mediaItemOne, mediaItemTwo), false)
    }

    private fun generateMedia(id: Int? = null): Media {
        val show = Show(
            null,
            Externals(null, (Math.random() * 10).toInt(), (Math.random() * 10).toInt()),
            mutableListOf("Action", "Sport", "Media Genre", ""),
            id ?: (Math.random() * 100).toInt(),
            null,
            "english",
            "title",
            Network(
                Country("11", "None", "25-11-2018 00:43:39"),
                (Math.random() * 100).toInt(),
                "Network name"
            ),
            null,
            null,
            Rating(Math.random() * 10),
            (Math.random() * 100).toInt(),
            Schedule(mutableListOf(""), "25-11-2018 00:43:39"),
            "Begin",
            "Summary",
            "type",
            (Math.random() * 1000).toInt(),
            "url",
            null,
            (Math.random() * 100).toInt()
        )

        return Media.fromTVMazeMedia(TVMazeMedia(Math.random() * 10, show))
    }

    private fun copyContentChangeId(media: Media): Media {
        return Media(
            media.genres,
            (Math.random() * 100).toInt(),
            media.image,
            media.language,
            media.name,
            media.officialSite,
            media.premiered,
            media.rating,
            media.runtime,
            media.seasons,
            media.schedule,
            media.status,
            media.summary,
            media.type,
            media.updated,
            media.url
        )
    }

    private fun copyIdChangeContent(id: Int): Media {
        return generateMedia(id)
    }
}