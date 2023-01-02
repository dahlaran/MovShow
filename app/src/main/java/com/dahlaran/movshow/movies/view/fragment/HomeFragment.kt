package com.dahlaran.movshow.movies.view.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dahlaran.movshow.R
import com.dahlaran.movshow.databinding.FragmentHomeBinding
import com.dahlaran.movshow.movies.view.adapter.MediaListAdapter
import com.dahlaran.movshow.movies.viewModel.MediaListViewModel
import kotlinx.android.synthetic.main.fragment_search.*

class HomeFragment : BaseViewPagerFragment() {

    private lateinit var viewDataBinding: FragmentHomeBinding
    private val mediaListViewModel: MediaListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        viewDataBinding.apply {
            this.viewmodel = mediaListViewModel
            this.lifecycleOwner = viewLifecycleOwner
        }
        mediaListViewModel.getShows()
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpListAdapter()
    }

    private fun setUpListAdapter() {
        val listAdapter = MediaListAdapter { itemClicked ->
            findNavController().navigate(
                MediaListFragmentDirections.actionMediaListFragmentToMediaDetailFragment(
                    itemClicked?.id ?: 0
                )
            )
        }
        viewDataBinding.mediaListRecycler.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(context)
            // Remove system animator to use custom animation
            itemAnimator = null
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1)) {
                        mediaListViewModel.getShows()
                    }
                }
            })
        }
    }
}