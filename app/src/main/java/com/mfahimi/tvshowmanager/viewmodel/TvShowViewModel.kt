package com.mfahimi.tvshowmanager.viewmodel

import androidx.lifecycle.viewModelScope
import com.mfahimi.domain.model.input.NewTvShowInput
import com.mfahimi.domain.model.output.NewCreatedTvShow
import com.mfahimi.domain.usecase.newtvshow.CreateNewTvShowUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class TvShowViewModel(
    private val createNewTvShowUseCase: CreateNewTvShowUseCase,
) : BaseViewModel() {

    private val _createNewShowObservable = mutableLiveData<NewCreatedTvShow>()
    val createNewShowObservable = _createNewShowObservable.immutable()
    fun startSession(input: NewTvShowInput) = createNewTvShowUseCase(input).onEach {
        _createNewShowObservable.emit(it)
    }.launchIn(viewModelScope)

}