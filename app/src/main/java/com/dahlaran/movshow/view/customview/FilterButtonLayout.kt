package com.dahlaran.movshow.view.customview

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.dahlaran.movshow.R
import kotlinx.android.synthetic.main.layout_filter_button.view.*

class FilterButtonLayout : ConstraintLayout {

    var ratingListener: (() -> Unit)? = null
    var alphaListener: (() -> Unit)? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defstyleAttr: Int) : super(context, attrs, defstyleAttr)
    constructor(context: Context, attrs: AttributeSet, defstyleAttr: Int, defStyleRes: Int) : super(context, attrs, defstyleAttr, defStyleRes)


    init {
        inflate(context, R.layout.layout_filter_button, this)

        sortAlphaFloatingButton.setOnClickListener {
            alphaListener?.invoke()
        }

        sortRatingFloatingButton.setOnClickListener {
            ratingListener?.invoke()
        }
    }

}