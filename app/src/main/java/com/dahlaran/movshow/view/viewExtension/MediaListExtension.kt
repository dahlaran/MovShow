package com.dahlaran.movshow.view.viewExtension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dahlaran.movshow.R
import com.dahlaran.movshow.models.Image
import com.dahlaran.movshow.models.Media
import com.dahlaran.movshow.models.TVMazeMedia
import com.dahlaran.movshow.utils.loadUrl
import com.dahlaran.movshow.view.adapter.MediaListAdapter

@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<Media>?) {
    items?.let {
        (listView.adapter as MediaListAdapter).submitList(items)
    }
}

@BindingAdapter("app:imageUrl")
fun loadImageWithUrl(imageView: ImageView, image: Image?) {
    image?.original?.let {
        if (it.trim().isNotEmpty()) {
            imageView.loadUrl(it, R.drawable.ic_placeholder)
        } else {
            imageView.setImageResource(R.drawable.ic_placeholder)
        }
    }
}
