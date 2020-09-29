package com.example.androidshowcase.ui.home.fragment.detail

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import coil.api.load
import com.example.androidshowcase.R
import com.example.androidshowcase.presentation.home.viewmodel.AlbumDetailViewModel
import com.example.androidshowcase.ui.base.BaseFragment
import com.example.androidshowcase.util.extension.observe
import com.example.androidshowcase.util.extension.visible
import kotlinx.android.synthetic.main.fragment_album_detail.*
import kotlinx.android.synthetic.main.fragment_album_list.errorAnimation
import kotlinx.android.synthetic.main.fragment_album_list.progressBar


class AlbumDetailFragment : BaseFragment(R.layout.fragment_album_detail) {


    private val viewModel: AlbumDetailViewModel by viewModels {
        viewModelFactory
    }

    private val stateObserver = Observer<AlbumDetailViewModel.ViewState> {
        progressBar.visible(it.isLoading)

        nameTextView.text = it.albumName
        nameTextView.visible(it.albumName.isNotBlank())

        artistTextView.text = it.artistName
        artistTextView.visible(it.artistName.isNotBlank())

        errorAnimation.visible(it.isError)

        val imageSize = 800

        coverImageView.load(it.coverImageUrl) {
            size(imageSize, imageSize)
        }
    }

    override fun init() {
        observe(viewModel.stateLiveData, stateObserver)
        checkArgs()
    }

    private fun checkArgs() {
        arguments?.let {
            val artist = arguments?.getString("artist")
            val name = arguments?.getString("name")
            val mbId = arguments?.getString("mbId")
            if (artist != null && name != null)
                viewModel.getAlbum(artist, name, mbId)
        }
    }

    override fun onViewClicked() {
        TODO("Not yet implemented")
    }
}