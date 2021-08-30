package com.mfahimi.domain.repository

import com.mfahimi.domain.FlowResponse
import com.mfahimi.domain.model.input.NewTvShowInput
import com.mfahimi.domain.model.output.NewCreatedTvShow

interface TvShowRepository {
    fun createNewTvShow(input: NewTvShowInput): FlowResponse<NewCreatedTvShow>
}