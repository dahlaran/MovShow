package com.dahlaran.movshow.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dahlaran.movshow.databinding.LayoutMediaBinding
import com.dahlaran.movshow.models.TVMazeMedia
import com.dahlaran.movshow.utilis.TVMazeMediaDifference

// TODO: Make media list infinite

class MediaListAdapter(private val onclickItemCallback: (itemClicked: TVMazeMedia) -> Unit) :
    CustomAdapter<TVMazeMedia, MediaListAdapter.MediaViewHolder>(
        TVMazeMediaDifference(),
        onclickItemCallback
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        return MediaViewHolder.from(parent, onclickItemCallback)
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bind(getItem(position))
    }

    class MediaViewHolder(
        private val binding: LayoutMediaBinding,
        private val onclickCallback: ((itemClicked: TVMazeMedia) -> Unit)
    ) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(
                parent: ViewGroup,
                onclickItemCallback: (itemClicked: TVMazeMedia) -> Unit
            ): MediaViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LayoutMediaBinding.inflate(layoutInflater, parent, false)
                return MediaViewHolder(binding, onclickItemCallback)
            }
        }

        init {
            // Set on click listener to layout to trigger an event
            itemView.setOnClickListener {
                this.binding.media?.let {
                    onclickCallback(it)
                }
            }
        }

        fun bind(media: TVMazeMedia) {
            // Set information to layout
            binding.media = media
            binding.executePendingBindings()
        }
    }
}