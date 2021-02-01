package com.dahlaran.movshow.models

import org.junit.Assert
import org.junit.Test

class MediaTest {

    @Test
    fun generateMediaFromTVMazeMediaTest() {
        val show = Show(
            null,
            Externals(null, (Math.random() * 10).toInt(), (Math.random() * 10).toInt()),
            mutableListOf("Action", "Sport", "Media Genre", ""),
            (Math.random() * 100).toInt(),
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
        val media = Media.fromTVMazeMedia(TVMazeMedia(Math.random() * 10, show))

        Assert.assertEquals(show.genres, media.genres)
        Assert.assertEquals(show.id, media.id)
        Assert.assertEquals(show.image, media.image)
        Assert.assertEquals(show.language, media.language)
        Assert.assertEquals(show.name, media.name)
        Assert.assertEquals(show.rating, media.rating)
        Assert.assertEquals(show.officialSite, media.officialSite)
        Assert.assertEquals(show.runtime, media.runtime)
        Assert.assertEquals(show.premiered, media.premiered)
        Assert.assertEquals(show.schedule, media.schedule)
        Assert.assertEquals(show.updated, media.updated)
        Assert.assertEquals(show.status, media.status)
        Assert.assertEquals(show.summary, media.summary)
        Assert.assertEquals(show.type, media.type)
        Assert.assertEquals(show.url, media.url)
    }
}