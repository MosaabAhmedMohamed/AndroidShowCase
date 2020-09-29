package com.example.androidshowcase.presentation.home.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.androidshowcase.domain.BaseApiResponse
import com.example.androidshowcase.domain.home.model.AlbumDomainModel
import com.example.androidshowcase.domain.home.usecase.AlbumDetailUseCase
import com.example.androidshowcase.presentation.home.action.BaseAction
import com.example.androidshowcase.presentation.home.viewstate.BaseViewState
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class AlbumDetailViewModel @Inject constructor(
    private val getAlbumDetailUseCase: AlbumDetailUseCase
) : BaseViewModel<AlbumDetailViewModel.ViewState, AlbumDetailViewModel.Action>(ViewState()) {


    fun getAlbum(
        artistName: String,
        albumName: String,
        mbId: String?
    ) {
        viewModelScope.launch {
            getAlbumDetailUseCase.execute(artistName, albumName, mbId).also {
                when {
                    it is BaseApiResponse.SuccessItem ->
                        sendAction(Action.AlbumLoadSuccess(it.data))
                    it is BaseApiResponse.Error ->
                        sendAction(Action.AlbumLoadFailure)
                }
            }
        }
    }

    override fun onReduceState(viewAction: Action) = when (viewAction) {
        is Action.AlbumLoadSuccess -> state.copy(
            isLoading = false,
            isError = false,
            artistName = viewAction.albumDomainModel.artist,
            albumName = viewAction.albumDomainModel.name,
            coverImageUrl = viewAction.albumDomainModel.getDefaultImageUrl() ?: ""
        )
        is Action.AlbumLoadFailure -> state.copy(
            isLoading = false,
            isError = true,
            artistName = "",
            albumName = "",
            coverImageUrl = ""
        )
    }

    internal data class ViewState(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val albumName: String = "",
        val artistName: String = "",
        val coverImageUrl: String = ""
    ) : BaseViewState


    internal sealed class Action : BaseAction {
        class AlbumLoadSuccess(val albumDomainModel: AlbumDomainModel) : Action()
        object AlbumLoadFailure : Action()
    }


}