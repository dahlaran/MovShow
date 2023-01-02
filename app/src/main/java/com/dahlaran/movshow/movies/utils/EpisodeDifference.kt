package com.dahlaran.movshow.movies.utils

import androidx.recyclerview.widget.DiffUtil
import com.dahlaran.movshow.movies.models.Episode

class EpisodeDifference : DiffUtil.ItemCallback<Episode>() {
    override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean {
        return oldItem == newItem
    }
}