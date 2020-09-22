package com.dahlaran.movshow.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.dahlaran.movshow.databinding.FragmentMediaDetailBinding
import com.dahlaran.movshow.view.activity.MainActivity
import com.dahlaran.movshow.viewModel.MediaDetailViewModel
import kotlinx.android.synthetic.main.fragment_media_detail.*


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
}