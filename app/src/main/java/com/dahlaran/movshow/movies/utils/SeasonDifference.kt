package com.dahlaran.movshow.movies.utils

import androidx.recyclerview.widget.DiffUtil
import com.dahlaran.movshow.movies.models.Season

class SeasonDifference: DiffUtil.ItemCallback<Season>() {
    override fun areItemsTheSame(oldItem: Season, newItem: Season): Boolean {
        return oldItem.seasonNumber == newItem.seasonNumber
    }

    override fun areContentsTheSame(oldItem: Season, newItem: Season): Boolean {
        return oldItem == newItem
    }
}