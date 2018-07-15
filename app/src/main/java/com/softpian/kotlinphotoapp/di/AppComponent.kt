package com.softpian.kotlinphotoapp.di

import com.softpian.kotlinphotoapp.data.source.remote.PhotoRemoteDataSource
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = arrayOf(
                ApiModule::class,
                NetworkModule::class
        )
)
interface AppComponent {

    fun inject(photoRemoteDataSource: PhotoRemoteDataSource)
}