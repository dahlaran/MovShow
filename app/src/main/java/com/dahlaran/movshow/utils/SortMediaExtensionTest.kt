package com.dahlaran.movshow.utils

import com.dahlaran.movshow.models.Media

fun List<Media>.sortByAlphabetical(): List<Media> {
    return this.sortedBy  {
        it.name
    }
}

fun List<Media>.sortByRating(): List<Media> {
    return this.sortedBy  {
        it.rating.average
    }
}

fun List<Media>.sortByRatingDescending(): List<Media> {
    return this.sortedByDescending  {
        it.rating.average
    }
}