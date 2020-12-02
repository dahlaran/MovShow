package com.dahlaran.movshow.view.adapter

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SmoothScroller


abstract class CustomAdapter<T, VH : RecyclerView.ViewHolder>(
    diffCallback: DiffUtil.ItemCallback<T>,
    val onclickItemCallback: ((itemClicked: T) -> Unit)?
) : ListAdapter<T, VH>(diffCallback) {

    companion object {
        private var ANIMATION_DURATION: Long = 200
    }

    private var onAttach = true


    // TODO: Animation create a buffer/List to know how many items have there animation executed to have the perfect delay

    // TODO: Use animation direction to work better when using the scrolling instead of "onAttach"
    private fun setAnimation(itemView: View, position: Int) {
        var i = position
        // On attach, make it like it will be the next one to appear(small duration)
        if (!onAttach) {
            i = 1
        }
        // Check if it is the first item
        val isFirstItem = i++ == 0
        itemView.alpha = 0f
        val animatorSet = AnimatorSet()
        val animator = ObjectAnimator.ofFloat(itemView, "alpha", 0f, 0.5f, 1.0f)
        ObjectAnimator.ofFloat(itemView, "alpha", 0f).start()
        // Put delay to make it appear after the last one
        animator.startDelay = if (isFirstItem) ANIMATION_DURATION / 2 else i * ANIMATION_DURATION / 2
        animator.duration = ANIMATION_DURATION
        animatorSet.play(animator)
        animator.start()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        // Set a scroll listener to know when it scroll
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                Log.d(javaClass.simpleName, "onScrollStateChanged: Called $newState")
                onAttach = false
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        setAnimation(holder.itemView, position)
    }

    fun smoothScrollToFirstItem(recyclerView: RecyclerView?) {
        if (recyclerView?.context != null) {
            val smoothScroller: SmoothScroller = object : LinearSmoothScroller(recyclerView.context) {
                override fun getVerticalSnapPreference(): Int {
                    return SNAP_TO_START
                }
            }
            smoothScroller.targetPosition = 0
            recyclerView.layoutManager?.startSmoothScroll(smoothScroller)
        }
    }
}