package com.example.androidshowcase.presentation.home.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.androidshowcase.domain.home.model.AlbumDomainModel
import com.example.androidshowcase.domain.home.usecase.AlbumUseCase
import com.example.androidshowcase.presentation.NavManager
import com.example.androidshowcase.presentation.home.action.BaseAction
import com.example.androidshowcase.presentation.home.viewstate.BaseViewState
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class AlbumListViewModel
@Inject constructor(
    private val useCase: AlbumUseCase
) : BaseViewModel<AlbumListViewModel.ViewState, AlbumListViewModel.Action>(ViewState()) {


    override fun onLoadData() {
        getAlbumList()
    }

    private fun getAlbumList() {
        viewModelScope.launch {
            useCase.executeList().also { result ->
                val action = when (result) {
                    is AlbumUseCase.Result.Success ->
                        if (result.data.isEmpty()) {
                            Action.AlbumListLoadingFailure
                        } else {
                            Action.AlbumListLoadingSuccess(result.data)
                        }
                    is AlbumUseCase.Result.Error ->
                        Action.AlbumListLoadingFailure
                }
                sendAction(action)
            }
        }
    }

    override fun onReduceState(viewAction: Action) = when (viewAction) {
        is Action.AlbumListLoadingSuccess -> state.copy(
            isLoading = false,
            isError = false,
            albums = viewAction.albums
        )
        is Action.AlbumListLoadingFailure -> state.copy(
            isLoading = false,
            isError = true,
            albums = listOf()
        )
    }


    internal data class ViewState(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val albums: List<AlbumDomainModel> = listOf()
    ) : BaseViewState

    internal sealed class Action : BaseAction {
        class AlbumListLoadingSuccess(val albums: List<AlbumDomainModel>) : Action()
        object AlbumListLoadingFailure : Action()
    }
}