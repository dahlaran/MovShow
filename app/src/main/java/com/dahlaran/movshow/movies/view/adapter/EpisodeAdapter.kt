package com.dahlaran.movshow.movies.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dahlaran.movshow.databinding.LayoutEpisodeBinding
import com.dahlaran.movshow.movies.models.Episode
import com.dahlaran.movshow.movies.utils.EpisodeDifference

class EpisodeAdapter : CustomAdapter<Episode, EpisodeAdapter.EpisodeViewHolder>(
    EpisodeDifference(),
    null
) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EpisodeViewHolder {
        return EpisodeViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bind(getItem(position))
    }

    class EpisodeViewHolder(private val binding: LayoutEpisodeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(
                parent: ViewGroup
            ): EpisodeViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LayoutEpisodeBinding.inflate(layoutInflater, parent, false)
                return EpisodeViewHolder(binding)
            }
        }

        init {
            itemView.setOnClickListener {
                binding.detailSummaryLayout.visibility =
                    if (binding.detailSummaryLayout.visibility == View.GONE) View.VISIBLE else View.GONE
            }
        }

        fun bind(episode: Episode) {
            if (layoutPosition == 0) {
                binding.itemDivider.visibility = View.GONE
            } else {
                binding.itemDivider.visibility = View.VISIBLE
            }
            binding.detailSummaryLayout.visibility = View.GONE
            // Set information to layout
            binding.episode = episode
            binding.executePendingBindings()
        }
    }
}