package com.example.androidshowcase.domain.home.usecase

import com.example.androidshowcase.domain.home.model.AlbumDomainModel
import com.example.androidshowcase.domain.home.repository.AlbumRepository
import javax.inject.Inject

internal class AlbumUseCase @Inject constructor(
    private val albumRepository: AlbumRepository
) {

    sealed class Result {

        data class Success(val data: List<AlbumDomainModel>) : Result()
        data class Error(val e: Throwable) : Result()
    }

    suspend fun executeList(): Result {
        // Due to API limitations we have to perform search with custom phrase to get albums
        val phrase = "sd"

        return try {
            Result.Success(albumRepository.searchAlbum(phrase)
                .filter { it.getDefaultImageUrl() != null })

        } catch (e: Exception) {
            Result.Error(e)
        }

    }
/*
    suspend fun executeAlbum(
        artistName: String,
        albumName: String,
        mbId: String?
    ): Result = try {
        albumRepository.getAlbumInfo(artistName,albumName,mbId)?.let {
            Result.Success(it)
        }?:Result.Error(RuntimeException("No data"))

    } catch (e: IOException) {
        Result.Error(e)
    }*/
}