package com.dahlaran.movshow.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dahlaran.movshow.databinding.LayoutSeasonListBinding
import com.dahlaran.movshow.models.Season
import com.dahlaran.movshow.utils.SeasonDifference

class SeasonAdapter(onclickItemCallback: ((itemClicked: Season) -> Unit)?) :
    CustomAdapter<Season, SeasonAdapter.CategoryViewHolder>(
        SeasonDifference(),
        onclickItemCallback
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        return CategoryViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bind(getItem(position))
    }

    class CategoryViewHolder(private val binding: LayoutSeasonListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): CategoryViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LayoutSeasonListBinding.inflate(layoutInflater, parent, false)
                return CategoryViewHolder(binding)
            }
        }

        init {
            // Set on click listener to layout to trigger an event
            itemView.setOnClickListener {
                binding.episodeRecycler.visibility =
                    if (binding.episodeRecycler.visibility == View.GONE)
                    {
                        View.VISIBLE
                    } else {

                        // Reset recycler adapter for remake the animation of episode
                        binding.episodeRecycler.removeAllViews()

                        View.GONE
                    }
            }
            initEpisodeRecycler()
        }

        fun bind(season: Season) {
            // Set gone to recycler to be gone even an old version of him has been clicked
            binding.episodeRecycler.visibility = View.GONE
            // Set information to layout
            binding.season = season
            binding.executePendingBindings()
        }

        private fun initEpisodeRecycler() {
            binding.episodeRecycler.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = EpisodeAdapter()
                // Don't be scrollable, only the scrollView is scrollable
                isNestedScrollingEnabled = false

                // Remove system animator to use custom animation
                itemAnimator = null
            }
        }
    }
}




