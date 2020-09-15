package com.example.androidshowcase.domain.home.model

import com.example.androidshowcase.domain.home.enum.AlbumDomainImageSize

internal data class AlbumImageDomainModel(
    val url: String,
    val size: AlbumDomainImageSize
)
