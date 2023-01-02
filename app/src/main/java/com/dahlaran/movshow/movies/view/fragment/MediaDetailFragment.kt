package com.dahlaran.movshow.movies.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.dahlaran.movshow.databinding.FragmentMediaDetailBinding
import com.dahlaran.movshow.movies.view.activity.MainActivity
import com.dahlaran.movshow.movies.view.adapter.SeasonAdapter
import com.dahlaran.movshow.movies.viewModel.MediaDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MediaDetailFragment : Fragment() {
    private lateinit var viewDataBinding: FragmentMediaDetailBinding
    private val args: MediaDetailFragmentArgs by navArgs()
    private val mediaDetailViewModel by viewModels<MediaDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentMediaDetailBinding.inflate(inflater, container, false).apply {
            viewmodel = mediaDetailViewModel
        }

        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner

        mediaDetailViewModel.start(args.mediaId)
        setUpListAdapter()
        return viewDataBinding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (activity as MainActivity?)?.hideNavigationSupportBar()
    }

    override fun onDestroy() {
        (activity as MainActivity?)?.showNavigationSupportBar()
        super.onDestroy()
    }

    private fun setUpListAdapter() {
        val listAdapter = SeasonAdapter(null)
        viewDataBinding.seasonRecycler.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(context)

            // Don't be scrollable, only the scrollView is scrollable
            isNestedScrollingEnabled = false
            // Remove system animator to use custom animation
            itemAnimator = null
        }
    }
}