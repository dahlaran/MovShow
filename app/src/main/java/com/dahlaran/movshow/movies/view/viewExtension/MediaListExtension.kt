package com.dahlaran.movshow.movies.view.viewExtension

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dahlaran.movshow.movies.models.Episode
import com.dahlaran.movshow.movies.models.Media
import com.dahlaran.movshow.movies.models.Season
import com.dahlaran.movshow.movies.view.adapter.EpisodeAdapter
import com.dahlaran.movshow.movies.view.adapter.MediaListAdapter
import com.dahlaran.movshow.movies.view.adapter.SeasonAdapter

@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<Any>?) {
    items?.let {
        when (listView.adapter) {
            is MediaListAdapter ->
                (listView.adapter as MediaListAdapter).submitList(items as List<Media>?)
            is SeasonAdapter ->
                (listView.adapter as SeasonAdapter).submitList(items as List<Season>?)
            is EpisodeAdapter ->
                (listView.adapter as EpisodeAdapter).submitList(items as List<Episode>?)
        }
    }
}
