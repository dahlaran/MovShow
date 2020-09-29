package com.dahlaran.movshow.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dahlaran.movshow.databinding.FragmentMediaListBinding
import com.dahlaran.movshow.view.adapter.MediaListAdapter
import com.dahlaran.movshow.viewModel.MediaListViewModel
import kotlinx.android.synthetic.main.fragment_media_list.*

class MediaListFragment : Fragment() {
    private lateinit var viewDataBinding: FragmentMediaListBinding
    private val mediaListViewModel by viewModels<MediaListViewModel>()

    // TODO: Add ViewPager (favorites, most followed, ...)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentMediaListBinding.inflate(inflater, container, false).apply {
            viewmodel = mediaListViewModel
        }

        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner

        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        editTaskFloatingButton.setOnClickListener {
            mediaListViewModel.searchByTitle(mediaListSearchEditText.text.toString())
        }

        setUpListAdapter()
    }

    private fun setUpListAdapter() {
        val listAdapter = MediaListAdapter { itemClicked ->
            findNavController().navigate(
                MediaListFragmentDirections.actionMediaListFragmentToMediaDetailFragment(
                    itemClicked.id
                )
            )
        }
        viewDataBinding.mediaListRecycler.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(context)
            // Remove system animator to use custom animation
            itemAnimator = null
        }
    }
}