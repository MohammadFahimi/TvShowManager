package com.mfahimi.data.network


import com.apollographql.apollo.ApolloClient
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response


fun apolloClient(): ApolloClient {

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthorizationInterceptor())
        .build()

    val instance: ApolloClient = ApolloClient.builder()
        .serverUrl("https://tv-show-manager.combyne.com/graphql")
        .okHttpClient(okHttpClient)

        .build()

    return instance
}

private class AuthorizationInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("X-Parse-Client-Key", "yiCk1DW6WHWG58wjj3C4pB/WyhpokCeDeSQEXA5HaicgGh4pTUd+3/rMOR5xu1Yi")
            .addHeader("X-Parse-Application-Id", "AaQjHwTIQtkCOhtjJaN/nDtMdiftbzMWW5N8uRZ+DNX9LI8AOziS10eHuryBEcCI")
            .build()

        return chain.proceed(request)
    }
}
