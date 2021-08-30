package com.mfahimi.data.di

import com.mfahimi.data.repository.TvShowRepositoryImpl
import com.mfahimi.domain.repository.TvShowRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<TvShowRepository> { TvShowRepositoryImpl(get()) }
}