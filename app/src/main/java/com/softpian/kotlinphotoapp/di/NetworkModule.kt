package com.softpian.kotlinphotoapp.di

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
class NetworkModule {

    @Provides
    fun provideClient(loggingInterceptor: Interceptor): OkHttpClient
            = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    fun provideLoggingIntercepter(): Interceptor
            = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
}