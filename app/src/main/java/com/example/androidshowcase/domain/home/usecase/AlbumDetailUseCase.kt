package com.example.androidshowcase.domain.home.usecase

import com.example.androidshowcase.domain.BaseApiResponse
import com.example.androidshowcase.domain.home.model.AlbumDomainModel
import com.example.androidshowcase.domain.home.repository.AlbumRepository
import java.lang.Exception
import javax.inject.Inject

internal class AlbumDetailUseCase @Inject constructor(
    private val albumRepository: AlbumRepository
) {

    suspend fun execute(
        artistName: String,
        albumName: String,
        mbId: String?
    ): BaseApiResponse<AlbumDomainModel> = try {
        albumRepository.getAlbumInfo(artistName, albumName, mbId)?.let {
            BaseApiResponse.SuccessItem(it)
        } ?: BaseApiResponse.Error(RuntimeException("No data"))
    } catch (e: Exception) {
        BaseApiResponse.Error(e)
    }

}
