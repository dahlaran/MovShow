package com.dahlaran.movshow.view.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView.OnEditorActionListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dahlaran.movshow.databinding.FragmentMediaListBinding
import com.dahlaran.movshow.view.adapter.MediaListAdapter
import com.dahlaran.movshow.viewModel.MediaListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_media_list.*

@AndroidEntryPoint
class MediaListFragment : Fragment() {
    private lateinit var viewDataBinding: FragmentMediaListBinding
    private val mediaListViewModel: MediaListViewModel by viewModels()

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

        // Set Floating Button
        editTaskFloatingButton.setOnClickListener {
            searchMediaByTitle()
        }
        setUpSearchEditText()
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

    private fun setUpSearchEditText() {
        mediaListSearchEditText.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchMediaByTitle()
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun searchMediaByTitle() {
        hideKeyBoard()
        mediaListViewModel.searchByTitle(mediaListSearchEditText.text.toString())
    }

    private fun hideKeyBoard() {
        val context = context
        if (context != null) {
            (activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager)?.apply {
                hideSoftInputFromWindow(view?.windowToken, 0)
            }
        }
    }
}