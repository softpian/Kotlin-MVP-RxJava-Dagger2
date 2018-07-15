package com.softpian.kotlinphotoapp.network

import com.softpian.kotlinphotoapp.data.photodata.PhotoInfo
import com.softpian.kotlinphotoapp.data.photodata.PhotoResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface FlickrPhotoApi {

    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1")
    fun getFlickrPhotoSearch(@QueryMap queryParameters: Map<String, String>): Observable<PhotoResponse>

    @GET("?method=flickr.photos.getInfo&format=json&nojsoncallback=1")
    fun getFlickrPhotoInfo(
            @Query("api_key") flickrApiKey: String,
            @Query("photo_id") photoId: String
    ): Observable<PhotoInfo>
}