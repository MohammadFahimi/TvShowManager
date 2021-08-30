package com.mfahimi.tvshowmanager.app

import android.app.Application
import com.mfahimi.data.di.networkModule
import com.mfahimi.data.di.repositoryModule
import com.mfahimi.tvshowmanager.di.usecaseModule
import com.mfahimi.tvshowmanager.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TvShowManager : Application() {
    override fun onCreate() {
        super.onCreate()
        setupDI()
    }

    private fun setupDI() {
        startKoin {
            androidContext(this@TvShowManager)
            modules(getKoinModules())
        }
    }

    private fun getKoinModules() = listOf(
        repositoryModule, networkModule,usecaseModule,viewModelModule
    )
}