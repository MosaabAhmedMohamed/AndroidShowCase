package com.example.androidshowcase.di.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidshowcase.presentation.home.viewmodel.AlbumDetailViewModel
import com.example.androidshowcase.presentation.home.viewmodel.AlbumListViewModel
import com.example.androidshowcase.presentation.home.viewmodel.HomeViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class HomeViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: HomeViewModelFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @HomeViewModelKey(AlbumListViewModel::class)
    internal abstract fun bindAlbumListViewModel(albumListViewModel: AlbumListViewModel): ViewModel

    @Binds
    @IntoMap
    @HomeViewModelKey(AlbumDetailViewModel::class)
    internal abstract fun bindAlbumDetailViewModel(albumDetailViewModel: AlbumDetailViewModel): ViewModel

}