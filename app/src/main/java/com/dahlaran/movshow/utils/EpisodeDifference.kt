package com.dahlaran.movshow.utils

import androidx.recyclerview.widget.DiffUtil
import com.dahlaran.movshow.models.Episode

class EpisodeDifference : DiffUtil.ItemCallback<Episode>() {
    override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean {
        return oldItem == newItem
    }
}