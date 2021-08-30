package com.mfahimi.tvshowmanager.di

import com.mfahimi.tvshowmanager.viewmodel.TvShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule=module{
    viewModel{ TvShowViewModel(get()) }
}