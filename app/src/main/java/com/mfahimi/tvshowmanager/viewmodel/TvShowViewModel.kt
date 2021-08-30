package com.mfahimi.tvshowmanager.viewmodel

import androidx.lifecycle.viewModelScope
import com.mfahimi.domain.model.input.NewTvShowInput
import com.mfahimi.domain.model.output.NewCreatedTvShow
import com.mfahimi.domain.model.output.TvShowData
import com.mfahimi.domain.usecase.newtvshow.CreateNewTvShowUseCase
import com.mfahimi.domain.usecase.tvshows.GetTvShows
import com.mfahimi.domain.usecase.tvshows.TvShowsUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class TvShowViewModel(
    private val createNewTvShowUseCase: CreateNewTvShowUseCase,
    private val tvShowsUseCase: TvShowsUseCase,
) : BaseViewModel() {

    private val pageSize = 30

    private val _createNewShowObservable = mutableLiveData<NewCreatedTvShow>()
    val createNewShowObservable = _createNewShowObservable.immutable()
    fun startSession(input: NewTvShowInput) = createNewTvShowUseCase(input).onEach {
        _createNewShowObservable.emit(it)
    }.launchIn(viewModelScope)

    private val _tvShowsObservable = mutableLiveData<List<TvShowData>>()
    val tvShowsObservable = _tvShowsObservable.immutable()
    fun getTvShows(pageIndex: Int) = tvShowsUseCase(GetTvShows(pageIndex, pageSize)).onEach {
        _tvShowsObservable.emit(it)
    }.launchIn(viewModelScope)


}