package com.example.androidshowcase.data.enum

import com.example.androidshowcase.domain.home.enum.AlbumDomainImageSize
import com.squareup.moshi.Json

enum class AlbumDataImageSize {

    @field:Json(name = "medium")
    MEDIUM,
    @field:Json(name = "small")
    SMALL,
    @field:Json(name = "large")
    LARGE,
    @field:Json(name = "extralarge")
    EXTRA_LARGE,
    @field:Json(name = "mega")
    MEGA,
    @field:Json(name = "")
    UNKNOWN
}

internal fun AlbumDataImageSize.toDomainEnum() = AlbumDomainImageSize.valueOf(this.name)