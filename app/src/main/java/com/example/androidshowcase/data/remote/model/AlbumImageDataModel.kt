package com.example.androidshowcase.data.remote.model

import com.example.androidshowcase.data.enum.AlbumDataImageSize
import com.example.androidshowcase.data.enum.toDomainEnum
import com.example.androidshowcase.domain.home.model.AlbumImageDomainModel
import com.squareup.moshi.Json

data class AlbumImageDataModel(
    @field:Json(name = "#text") val url: String,
    val size: AlbumDataImageSize
)

internal fun AlbumImageDataModel.toDomainModel() = AlbumImageDomainModel(
    url = this.url,
    size = this.size.toDomainEnum()
)
