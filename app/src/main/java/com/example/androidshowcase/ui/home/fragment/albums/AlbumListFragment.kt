package com.example.androidshowcase.ui.home.fragment.albums

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.androidshowcase.R
import com.example.androidshowcase.util.extension.observe
import com.example.androidshowcase.util.extension.visible
import com.example.androidshowcase.presentation.home.viewmodel.AlbumListViewModel
import com.example.androidshowcase.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_album_list.*


class AlbumListFragment : BaseFragment(R.layout.fragment_album_list) {

    private val viewModel: AlbumListViewModel by viewModels {
        viewModelFactory
    }

    private val albumAdapter: AlbumAdapter =
        AlbumAdapter()


    private val stateObserver = Observer<AlbumListViewModel.ViewState> {
        albumAdapter.albums = it.albums
        progressBar.visible(it.isLoading)
        errorAnimation.visible(it.isError)
    }

    override fun init() {

        albumAdapter.setOnDebouncedClickListener {
            args.putString("artist",it.artist)
            args.putString("name",it.name)
            args.putString("mbId",it.mbId)
           navigate(R.id.action_albumListFragment_to_albumDetailFragment, args)
        }

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