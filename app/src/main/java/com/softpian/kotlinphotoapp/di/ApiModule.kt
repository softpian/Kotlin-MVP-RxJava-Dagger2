package com.softpian.kotlinphotoapp.di

import com.softpian.kotlinphotoapp.network.FlickrPhotoApi
import com.softpian.kotlinphotoapp.util.Constant
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    @Provides
    fun providePhotoApi(okHttpClient: OkHttpClient,
                        callAdapter: CallAdapter.Factory,
                        converter: Converter.Factory): FlickrPhotoApi {

        var flickrPhotoApi: FlickrPhotoApi? = null

        if (flickrPhotoApi == null) {
            flickrPhotoApi = Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .client(okHttpClient)
                    .addCallAdapterFactory(callAdapter)
                    .addConverterFactory(converter)
                    .build()
                    .create(FlickrPhotoApi::class.java)
        }

        return flickrPhotoApi!!
    }

    @Provides
    fun provideCallAdapterFactory(): CallAdapter.Factory
            = RxJava2CallAdapterFactory.createAsync()

    @Provides
    fun provideConverterFactory(): Converter.Factory
            = GsonConverterFactory.create()
}