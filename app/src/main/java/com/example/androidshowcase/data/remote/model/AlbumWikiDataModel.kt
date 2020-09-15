package com.example.androidshowcase.data.remote.model

import com.example.androidshowcase.domain.home.model.AlbumWikiDomainModel

data class AlbumWikiDataModel(
    val published: String,
    val summary: String
)

internal fun AlbumWikiDataModel.toDomainModel() = AlbumWikiDomainModel(
    published = this.published,
    summary = this.summary
)