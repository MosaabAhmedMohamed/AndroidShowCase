package com.example.androidshowcase.ui.home.fragment

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.example.androidshowcase.R
import com.example.androidshowcase.util.extension.observe
import com.example.androidshowcase.util.extension.visible
import com.example.androidshowcase.presentation.home.viewmodel.AlbumListViewModel
import com.example.androidshowcase.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_album_list.*
import javax.inject.Inject


class AlbumListFragment @Inject
constructor(
    viewModelFactory: ViewModelProvider.Factory,
    requestManager: RequestManager
) : BaseFragment(R.layout.fragment_album_list) {

    private val viewModel: AlbumListViewModel by viewModels {
        viewModelFactory
    }

    private val albumAdapter: AlbumAdapter = AlbumAdapter()


    private val stateObserver = Observer<AlbumListViewModel.ViewState> {
        albumAdapter.albums = it.albums
        progressBar.visible(it.isLoading)
        errorAnimation.visible(it.isError)
    }

    override fun init() {

        recyclerView.apply {
            setHasFixedSize(true)
            val columnWidth = context.resources.getDimension(R.dimen.image_size).toInt()
            layoutManager =
                GridAutofitLayoutManager(
                    context,
                    columnWidth
                )
            adapter = albumAdapter
        }

        observe(viewModel.stateLiveData, stateObserver)
        viewModel.loadData()
    }



    override fun onViewClicked() {
        showRetryDialog(true)
    }


}