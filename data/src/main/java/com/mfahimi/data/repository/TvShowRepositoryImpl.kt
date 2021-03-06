package com.mfahimi.data.repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.coroutines.await
import com.mfahimi.domain.FlowResponse
import com.mfahimi.domain.model.input.NewTvShowInput
import com.mfahimi.domain.model.output.NewCreatedTvShow
import com.mfahimi.domain.model.output.TvShowData
import com.mfahimi.domain.repository.TvShowRepository
import com.mfahimi.domain.result.Result
import com.mfahimi.tvshowmanager.CreateMovieMutation
import com.mfahimi.tvshowmanager.MoviesQuery
import com.mfahimi.tvshowmanager.type.CreateMovieFieldsInput
import com.mfahimi.tvshowmanager.type.CreateMovieInput
import com.mfahimi.tvshowmanager.type.MovieOrder
import kotlinx.coroutines.flow.flow

class TvShowRepositoryImpl(private val apollo: ApolloClient) : TvShowRepository {

    override fun createNewTvShow(input: NewTvShowInput): FlowResponse<NewCreatedTvShow> = flow {
        emit(Result.Loading(null))
        val response = apollo.mutate(
            CreateMovieMutation(
                CreateMovieInput(
                    clientMutationId = Input.fromNullable(""),
                    fields = Input.fromNullable(
                        CreateMovieFieldsInput(
                            title = input.title,
                            releaseDate = Input.fromNullable(input.releaseDate),
                            seasons = Input.fromNullable(input.seasons)
                        )
                    )
                )
            )
        ).await()
        if (response.hasErrors())
            emit(Result.Error(Exception(response.errors?.get(0)?.message)))
        else
            emit(Result.Success(NewCreatedTvShow(response.data!!.createMovie!!.movie.title)))

    }

    override fun getTvShows(pageIndex: Int, pageSize: Int): FlowResponse<List<TvShowData>> = flow {
        emit(Result.Loading(null))
        val response = apollo.query(
            MoviesQuery(pageSize, pageIndex * pageSize, Input.fromNullable(listOf(MovieOrder.CREATEDAT_DESC)))
        ).await()
        if (response.hasErrors())
            emit(Result.Error(Exception(response.errors?.get(0)?.message)))
        else {
            val movies = response.data?.movies?.edges?.map {
                TvShowData(
                    title = it?.node?.title,
                    releaseDate = it?.node?.releaseDate.toString(),
                    seasons = it?.node?.seasons ?: 0.0,
                )
            }
            emit(Result.Success(if (movies.isNullOrEmpty()) listOf() else movies))
        }
    }
}