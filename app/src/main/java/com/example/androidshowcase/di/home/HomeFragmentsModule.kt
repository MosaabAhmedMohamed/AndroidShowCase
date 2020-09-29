package com.example.androidshowcase.di.home

import com.example.androidshowcase.ui.home.fragment.albums.AlbumListFragment
import com.example.androidshowcase.ui.home.fragment.detail.AlbumDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class  HomeFragmentsModule {
    @ContributesAndroidInjector
    abstract fun contributeAlbumListFragment(): AlbumListFragment

    @ContributesAndroidInjector
    abstract fun contributeAlbumDetailFragment(): AlbumDetailFragment

}