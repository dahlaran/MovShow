package com.dahlaran.movshow.utils

import android.util.Log
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

fun ImageView.loadUrl(url: String?, @DrawableRes placeholderId: Int = 0) {
    val callback: Callback = object : Callback {
            override fun onSuccess() {
                Log.d("Picasso Image Loading", "Picasso Image Loading succeeded")
            }

            override fun onError() {
                Log.d("Picasso Image Loading", "Picasso Image Loading failed")
            }
        }

    if (placeholderId != 0) {
        Picasso.with(context).load(url).error(placeholderId).placeholder(placeholderId).into(this, callback)
    } else {
        Picasso.with(context).load(url).into(this, callback)
    }
}