package com.dahlaran.movshow.view.viewExtension

<<<<<<< Updated upstream
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dahlaran.movshow.R
import com.dahlaran.movshow.models.*
import com.dahlaran.movshow.utils.loadUrl
=======
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dahlaran.movshow.models.Episode
import com.dahlaran.movshow.models.Media
import com.dahlaran.movshow.models.Season
>>>>>>> Stashed changes
import com.dahlaran.movshow.view.adapter.EpisodeAdapter
import com.dahlaran.movshow.view.adapter.MediaListAdapter
import com.dahlaran.movshow.view.adapter.SeasonAdapter

@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<Any>?) {
    items?.let {
<<<<<<< Updated upstream
        when(listView.adapter){
            is MediaListAdapter->
=======
        when (listView.adapter) {
            is MediaListAdapter ->
>>>>>>> Stashed changes
                (listView.adapter as MediaListAdapter).submitList(items as List<Media>?)
            is SeasonAdapter ->
                (listView.adapter as SeasonAdapter).submitList(items as List<Season>?)
            is EpisodeAdapter ->
                (listView.adapter as EpisodeAdapter).submitList(items as List<Episode>?)
        }
    }
<<<<<<< Updated upstream
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
=======
}
>>>>>>> Stashed changes
