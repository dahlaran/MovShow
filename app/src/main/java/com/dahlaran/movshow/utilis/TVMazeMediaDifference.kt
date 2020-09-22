package com.dahlaran.movshow.utilis

import androidx.recyclerview.widget.DiffUtil
import com.dahlaran.movshow.models.TVMazeMedia

class TVMazeMediaDifference : DiffUtil.ItemCallback<TVMazeMedia>() {
    override fun areItemsTheSame(oldItem: TVMazeMedia, newItem: TVMazeMedia): Boolean {
        return oldItem.show.id == newItem.show.id
    }

    override fun areContentsTheSame(oldItem: TVMazeMedia, newItem: TVMazeMedia): Boolean {
        return oldItem == newItem
    }
}