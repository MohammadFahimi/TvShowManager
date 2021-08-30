package com.mfahimi.tvshowmanager.di

import com.mfahimi.domain.formatter.DateFormatter
import com.mfahimi.domain.formatter.DateFormatterImpl
import org.koin.dsl.module

val appModule = module {
    single<DateFormatter> { DateFormatterImpl() }
}