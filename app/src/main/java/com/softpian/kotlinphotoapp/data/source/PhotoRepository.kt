package com.softpian.kotlinphotoapp.data.source

import android.app.Application
import com.softpian.kotlinphotoapp.data.source.remote.PhotoRemoteDataSource

class PhotoRepository(application: Application) : PhotoDataSource {

    val photoRemoteDataSource = PhotoRemoteDataSource(application)

    override fun getFlickrPhotoSearch(text: String, page: Int, perPage: Int)
            = photoRemoteDataSource.getFlickrPhotoSearch(text, page, perPage)

    override fun getFlickrPhotoInfo(photoId: String)
            = photoRemoteDataSource.getFlickrPhotoInfo(photoId)
}