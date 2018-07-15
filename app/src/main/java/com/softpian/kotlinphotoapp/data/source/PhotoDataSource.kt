package com.softpian.kotlinphotoapp.data.source

import com.softpian.kotlinphotoapp.data.photodata.PhotoInfo
import com.softpian.kotlinphotoapp.data.photodata.PhotoResponse
import io.reactivex.Observable

interface PhotoDataSource {

    fun getFlickrPhotoSearch(text: String, page: Int, perPage: Int): Observable<PhotoResponse>

    fun getFlickrPhotoInfo(photoId: String): Observable<PhotoInfo>
}