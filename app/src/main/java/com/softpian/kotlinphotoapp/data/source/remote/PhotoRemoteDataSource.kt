package com.softpian.kotlinphotoapp.data.source.remote

import android.app.Application
import com.softpian.kotlinphotoapp.KotlinPhotoApp
import com.softpian.kotlinphotoapp.data.photodata.PhotoInfo
import com.softpian.kotlinphotoapp.data.photodata.PhotoResponse
import com.softpian.kotlinphotoapp.data.source.PhotoDataSource
import com.softpian.kotlinphotoapp.network.FlickrPhotoApi
import com.softpian.kotlinphotoapp.util.Constant
import io.reactivex.Observable
import javax.inject.Inject

class PhotoRemoteDataSource(application: Application) : PhotoDataSource {

    init {
        (application as KotlinPhotoApp).appComponent.inject(this)
    }

    @Inject lateinit var flickrPhotoApi: FlickrPhotoApi


    override fun getFlickrPhotoSearch(text: String, page: Int, perPage: Int): Observable<PhotoResponse> {

        val queryParameters = mutableMapOf<String, String>()
        queryParameters["api_key"] = Constant.FLICKR_API_KEY
        queryParameters["safe_search"] = Constant.SAFE_SEARCH
        queryParameters["text"] = text
        queryParameters["page"] = page.toString()
        queryParameters["per_page"] = perPage.toString()

        return flickrPhotoApi.getFlickrPhotoSearch(queryParameters)
    }

    override fun getFlickrPhotoInfo(photoId: String): Observable<PhotoInfo> {

        return flickrPhotoApi.getFlickrPhotoInfo(Constant.FLICKR_API_KEY, photoId)

    }
}