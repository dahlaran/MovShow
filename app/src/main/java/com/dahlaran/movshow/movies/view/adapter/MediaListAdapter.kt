package com.dahlaran.movshow.movies.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dahlaran.movshow.databinding.ItemLoadingBinding
import com.dahlaran.movshow.databinding.LayoutMediaBinding
import com.dahlaran.movshow.movies.models.Media
import com.dahlaran.movshow.movies.utils.MediaDifference

class MediaListAdapter(onclickItemCallback: (itemClicked: Media?) -> Unit) :
    CustomAdapter<Media?, RecyclerView.ViewHolder>(MediaDifference(), onclickItemCallback) {
    companion object {
        private const val ITEM_ENTITY = 0
        private const val ITEM_LOADING = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            null -> ITEM_LOADING
            else -> ITEM_ENTITY
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            ITEM_LOADING -> {
                val binding = ItemLoadingBinding.inflate(layoutInflater, parent, false)
                LoadingViewHolder(binding)
            }
            else -> {
                val binding = LayoutMediaBinding.inflate(layoutInflater, parent, false)
                MediaViewHolder(binding, onclickItemCallback)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val item = getItem(position)

        if (item != null && holder is MediaViewHolder) {
            holder.bind(item)
        }
    }

    class LoadingViewHolder(binding: ItemLoadingBinding) : RecyclerView.ViewHolder(binding.root)

    class MediaViewHolder(
        private val binding: LayoutMediaBinding,
        private val onclickCallback: ((itemClicked: Media) -> Unit)?
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            // Set on click listener to layout to trigger an event
            itemView.setOnClickListener {
                this.binding.media?.let { media ->
                    onclickCallback?.run {
                        this(media)
                    }
                }
            }
        }

        fun bind(media: Media) {
            // Set information to layout
            binding.media = media
            binding.executePendingBindings()
        }
    }
}