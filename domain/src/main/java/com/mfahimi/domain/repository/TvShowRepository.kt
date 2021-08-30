package com.mfahimi.domain.repository

import com.mfahimi.domain.FlowResponse
import com.mfahimi.domain.model.input.NewTvShowInput
import com.mfahimi.domain.model.output.NewCreatedTvShow
import com.mfahimi.domain.model.output.TvShowData

interface TvShowRepository {

    fun createNewTvShow(input: NewTvShowInput): FlowResponse<NewCreatedTvShow>

    fun getTvShows(pageIndex:Int,pageSize:Int): FlowResponse<List<TvShowData>>
}