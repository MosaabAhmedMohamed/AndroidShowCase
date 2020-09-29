package com.example.androidshowcase.domain.home.usecase

import com.example.androidshowcase.domain.BaseApiResponse
import com.example.androidshowcase.domain.home.model.AlbumDomainModel
import com.example.androidshowcase.domain.home.repository.AlbumRepository
import javax.inject.Inject

internal class AlbumUseCase @Inject constructor(private val albumRepository: AlbumRepository) {


    suspend fun executeList(): BaseApiResponse<AlbumDomainModel> {
        // Due to API limitations we have to perform search with custom phrase to get albums
        val phrase = "sd"
        return try {
            BaseApiResponse.SuccessList(albumRepository.searchAlbum(phrase)
                .filter { it.getDefaultImageUrl() != null })

        } catch (e: Exception) {
            BaseApiResponse.Error(e)
        }

    }
}