package com.mfahimi.tvshowmanager.di

import com.mfahimi.domain.usecase.newtvshow.CreateNewTvShowUseCase
import org.koin.dsl.module

val usecaseModule = module {
    factory { CreateNewTvShowUseCase(get()) }
}