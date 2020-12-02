package com.dahlaran.movshow.utils

import androidx.recyclerview.widget.DiffUtil
import com.dahlaran.movshow.models.Media

class MediaDifference: DiffUtil.ItemCallback<Media>() {
    override fun areItemsTheSame(oldItem: Media, newItem: Media): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Media, newItem: Media): Boolean {
        return oldItem == newItem
    }
}