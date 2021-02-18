package com.dahlaran.movshow.utils

import com.dahlaran.movshow.models.*
import org.junit.Before
import org.junit.Test

class SortMediaExtensionTest {

    private var mediaList: List<Media>? = null

    @Before
    fun before() {
        mediaList = generateMedias()
    }


    @Test
    fun sortByAlphabetical() {
        val nameOrder = mutableListOf(
            "", "Any Name", "are", "b", "bar", "bernard",
            "habitation", "house", "name", "nose", "s", "we have something", "zone"
        )

        mediaList = mediaList!!.sortByAlphabetical()
        for (i in mediaList!!.indices) {
            assert(mediaList!![i].name == nameOrder[i])
        }
    }

    @Test
    fun sortByRating() {
        val nameOrder = mutableListOf(
            "bernard", "Any Name", "b", "are", "", "habitation",
            "house", "nose", "zone", "bar", "we have something", "name", "s"
        )

        mediaList = mediaList!!.sortByRating()
        for (i in mediaList!!.indices) {
            assert(mediaList!![i].name == nameOrder[i])
        }
    }

    @Test
    fun sortByRatingDescending() {
        val nameOrder = mutableListOf(
            "bernard", "Any Name", "b", "are", "", "habitation",
            "house", "nose", "zone", "bar", "we have something", "name", "s"
        )

        mediaList = mediaList!!.sortByRatingDescending()
        for (i in mediaList!!.indices) {
            assert(mediaList!![i].name == nameOrder[mediaList!!.size - (i + 1)])
        }
    }

    @Test
    fun sortByAlphabeticalEmpty() {
        mediaList = null
        mediaList = mediaList?.sortByAlphabetical()
        assert(mediaList == null)

        mediaList = mutableListOf()
        mediaList = mediaList!!.sortByAlphabetical()

        assert(mediaList!!.isEmpty())
    }

    @Test
    fun sortByRatingEmpty() {
        mediaList = null
        mediaList = mediaList?.sortByRating()
        assert(mediaList == null)

        mediaList = mutableListOf()
        mediaList = mediaList!!.sortByRating()

        assert(mediaList!!.isEmpty())
    }

    private fun generateMedias(): List<Media> {
        val mediaList = mutableListOf<Media>()

        mediaList.add(generateMedia("name", 9.9))
        mediaList.add(generateMedia("are", 3.0))
        mediaList.add(generateMedia("b", 2.5))
        mediaList.add(generateMedia("bernard", 1.7))
        mediaList.add(generateMedia("habitation", 6.0))
        mediaList.add(generateMedia("nose", 7.1))
        mediaList.add(generateMedia("house", 6.4))
        mediaList.add(generateMedia("zone", 8.8))
        mediaList.add(generateMedia("bar", 9.0))
        mediaList.add(generateMedia("s", 10.0))
        mediaList.add(generateMedia("we have something", 9.8))
        mediaList.add(generateMedia("", 5.2))
        mediaList.add(generateMedia("Any Name", 2.1))

        return mediaList
    }

    private fun generateMedia(name: String, rate: Double): Media {
        val show = Show(
            null,
            Externals(null, (Math.random() * 10).toInt(), (Math.random() * 10).toInt()),
            mutableListOf("Action", "Sport", "Media Genre", ""),
            (Math.random() * 100).toInt(),
            null,
            "english",
            name,
            Network(
                Country("11", "None", "25-11-2018 00:43:39"),
                (Math.random() * 100).toInt(),
                "Network name"
            ),
            null,
            null,
            Rating(rate),
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
}