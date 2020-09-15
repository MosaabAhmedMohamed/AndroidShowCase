package com.example.androidshowcase.data.remote.client

import com.example.androidshowcase.data.remote.model.AlbumDataModel
import com.example.androidshowcase.data.remote.model.AlbumSearchResultDataModel
import com.example.androidshowcase.data.remote.model.SearchAlbumResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

internal interface HomeApi {

    @POST("./?method=album.search")
    suspend fun searchAlbumAsync(
        @Query("album") phrase: String,
        @Query("limit") limit: Int = 60
    ): SearchAlbumResponse

    @POST("./?method=album.getInfo")
   suspend fun getAlbumInfoAsync(
        @Query("artist") artistName: String,
        @Query("album") albumName: String,
        @Query("mbid") mbId: String?
    ): AlbumDataModel?

}