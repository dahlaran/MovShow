package com.dahlaran.movshow.movies.view.viewExtension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.dahlaran.movshow.R
import com.dahlaran.movshow.movies.models.sendByApi.Image
import com.dahlaran.movshow.utils.loadUrl

@BindingAdapter("app:imageUrl")
fun loadImageWithUrl(imageView: ImageView, image: Image?) {
    image?.original?.let {
        if (it.trim().isNotEmpty()) {
            imageView.loadUrl(it, R.drawable.ic_placeholder)
        } else {
            imageView.setImageResource(R.drawable.ic_placeholder)
        }
    } ?: imageView.setImageResource(R.drawable.ic_placeholder)
}