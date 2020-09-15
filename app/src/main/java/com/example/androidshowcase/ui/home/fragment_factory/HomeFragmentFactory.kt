package com.example.androidshowcase.ui.home.fragment_factory

import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.androidshowcase.di.home.HomeScope
import com.example.androidshowcase.ui.home.fragment.AlbumListFragment
import javax.inject.Inject

@HomeScope
class HomeFragmentFactory
@Inject
constructor(
    private val viewModelFactory: ViewModelProvider.Factory,
    private val requestOptions: RequestOptions,
    private val requestManager: RequestManager
): FragmentFactory(){

    override fun instantiate(classLoader: ClassLoader, className: String) =
        when (className) {

            AlbumListFragment::class.java.name -> {
                AlbumListFragment(viewModelFactory, requestManager)
            }


            else -> {
                AlbumListFragment(viewModelFactory, requestManager)
            }
        }

}
