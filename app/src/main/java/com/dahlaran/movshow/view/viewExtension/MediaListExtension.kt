package com.dahlaran.movshow.view.viewExtension

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dahlaran.movshow.models.Episode
import com.dahlaran.movshow.models.Media
import com.dahlaran.movshow.models.Season
import com.dahlaran.movshow.view.adapter.EpisodeAdapter
import com.dahlaran.movshow.view.adapter.MediaListAdapter
import com.dahlaran.movshow.view.adapter.SeasonAdapter

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