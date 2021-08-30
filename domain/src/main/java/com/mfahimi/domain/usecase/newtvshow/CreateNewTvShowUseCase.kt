package com.mfahimi.domain.usecase.newtvshow

import com.mfahimi.domain.model.input.NewTvShowInput
import com.mfahimi.domain.model.output.NewCreatedTvShow
import com.mfahimi.domain.repository.TvShowRepository
import com.mfahimi.domain.usecase.FlowUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import com.mfahimi.domain.result.Result

class CreateNewTvShowUseCase(
    private val repo: TvShowRepository
) : FlowUseCase<NewTvShowInput, NewCreatedTvShow>(Dispatchers.IO) {

    override fun execute(parameters: NewTvShowInput): Flow<Result<NewCreatedTvShow>> {
        return repo.createNewTvShow(parameters)
    }
}