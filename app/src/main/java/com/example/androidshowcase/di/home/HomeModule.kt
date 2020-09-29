package com.example.androidshowcase.di.home

import com.example.androidshowcase.data.remote.client.HomeApi
import com.example.androidshowcase.data.repository.AlbumRepositoryImpl
import com.example.androidshowcase.domain.home.repository.AlbumRepository
import com.example.androidshowcase.domain.home.usecase.AlbumDetailUseCase
import com.example.androidshowcase.domain.home.usecase.AlbumUseCase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object HomeModule {

    @JvmStatic
    @HomeScope
    @Provides
    internal fun provideHomeApi(retrofit: Retrofit.Builder): HomeApi {
        return retrofit
            .build()
            .create(HomeApi::class.java)
    }

    @JvmStatic
    @HomeScope
    @Provides
    internal fun provideAlbumRepository(api: HomeApi): AlbumRepository {
        return AlbumRepositoryImpl(api)
    }

    @JvmStatic
    @HomeScope
    @Provides
    internal fun provideAlbumUseCase(repository: AlbumRepository): AlbumUseCase {
        return AlbumUseCase(repository)
    }

    @JvmStatic
    @HomeScope
    @Provides
    internal fun provideAlbumDetailUseCase(repository: AlbumRepository): AlbumDetailUseCase {
        return AlbumDetailUseCase(repository)
    }

}
