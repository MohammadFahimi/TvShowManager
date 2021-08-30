package com.mfahimi.domain.usecase.tvshows

import com.mfahimi.domain.model.output.TvShowData
import com.mfahimi.domain.repository.TvShowRepository
import com.mfahimi.domain.result.Result
import com.mfahimi.domain.usecase.FlowUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class TvShowsUseCase(
    private val repo: TvShowRepository
) : FlowUseCase<GetTvShows, List<TvShowData>>(Dispatchers.IO) {

    override fun execute(parameters: GetTvShows): Flow<Result<List<TvShowData>>> {
        return repo.getTvShows(parameters.pageIndex,parameters.pageSize)
    }
}
class GetTvShows(val pageIndex: Int, val pageSize: Int)