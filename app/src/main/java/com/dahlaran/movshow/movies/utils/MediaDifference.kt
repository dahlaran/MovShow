package com.dahlaran.movshow.movies.utils

import androidx.recyclerview.widget.DiffUtil
import com.dahlaran.movshow.movies.models.Media

class MediaDifference : DiffUtil.ItemCallback<Media?>() {
    override fun areItemsTheSame(oldItem: Media, newItem: Media): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Media, newItem: Media): Boolean {
        return oldItem == newItem
    }
}