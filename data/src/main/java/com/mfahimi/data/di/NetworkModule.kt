package com.mfahimi.data.di

import com.apollographql.apollo.ApolloClient
import com.mfahimi.data.network.apolloClient
import org.koin.dsl.module

val networkModule = module {
    single<ApolloClient> { apolloClient() }
}