package com.example.androidshowcase.data.repository

import com.example.androidshowcase.data.remote.client.HomeApi
import com.example.androidshowcase.data.remote.model.toDomainModel
import com.example.androidshowcase.domain.home.repository.AlbumRepository
import javax.inject.Inject

internal class AlbumRepositoryImpl
@Inject
constructor(
    private val homeApi: HomeApi
) : AlbumRepository {

    override suspend fun getAlbumInfo(artistName: String, albumName: String, mbId: String?) =
        homeApi.getAlbumInfoAsync(artistName, albumName, mbId)
            ?.album
            ?.toDomainModel()

    override suspend fun searchAlbum(phrase: String) =
        homeApi.searchAlbumAsync(phrase).results
            .albumMatches
            .album
            .map { it.toDomainModel() }
}
