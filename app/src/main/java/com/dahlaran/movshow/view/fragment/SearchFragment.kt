package com.dahlaran.movshow.view.fragment

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
import com.dahlaran.movshow.R
import com.dahlaran.movshow.databinding.FragmentSearchBinding
import com.dahlaran.movshow.view.adapter.MediaListAdapter
import com.dahlaran.movshow.viewModel.MediaListViewModel
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : BaseViewPagerFragment() {

    private lateinit var viewDataBinding: FragmentSearchBinding
    private val mediaListViewModel: MediaListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        viewDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        viewDataBinding.apply {
            this.viewmodel = mediaListViewModel
            this.lifecycleOwner = viewLifecycleOwner
        }
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpSearchEditText()
        setUpListAdapter()

        // Set Floating Button
        searchFilterButton.apply {
            ratingListener = {
                mediaListViewModel.sortByRating()
            }
            alphaListener = {
                mediaListViewModel.sortByAlphabetical()
            }
        }
    }


    private fun setUpSearchEditText() {
        mediaListSearchEditText.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchMediaByTitle()
                return@OnEditorActionListener true
            }
            false
        })
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