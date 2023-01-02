package com.dahlaran.movshow.movies.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dahlaran.movshow.databinding.FragmentMediaListBinding
import com.dahlaran.movshow.movies.view.activity.MainActivity
import com.dahlaran.movshow.movies.view.adapter.ViewPagerAdapter
import com.dahlaran.movshow.movies.viewModel.MediaListViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_media_list.*


@AndroidEntryPoint
class MediaListFragment : Fragment() {
    private lateinit var viewDataBinding: FragmentMediaListBinding

    private val tabName: MutableList<Pair<Class<*>, String>> = mutableListOf(
        Pair(HomeFragment::class.java, "Home"),
        Pair(FavoriteFragment::class.java, "Favorite"),
        Pair(SearchFragment::class.java, "Search")
    )

    // TODO: Add ViewPager (favorites, most followed, ...)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentMediaListBinding.inflate(inflater, container, false)

        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner

        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPagerInit()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (activity as MainActivity?)?.showNavigationSupportBar()
    }


    private fun viewPagerInit() {
        val fragmentList = ArrayList<BaseViewPagerFragment>()
        tabName.forEach {
            fragmentList.add(generateBaseViewPagerUsingType(it.first))
        }

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        mediaViewPager.adapter = adapter

        // Init TabLayout after adding adapter to pager
        TabLayoutMediator(tabLayout, mediaViewPager) { tab, position ->
            tab.text = tabName[position].second
        }.attach()
    }


    private fun generateBaseViewPagerUsingType(type: Class<*>): BaseViewPagerFragment {
        val newObject = type.constructors[0].newInstance()
        return newObject as BaseViewPagerFragment
    }
}