package com.softpian.kotlinphotoapp.photolist

import com.softpian.kotlinphotoapp.BasePresenter
import com.softpian.kotlinphotoapp.BaseView
import com.softpian.kotlinphotoapp.photolist.adapter.PhotoRecyclerViewModel

class PhotoListContract {

    interface View : BaseView<Presenter> {

        fun hideProgress()

        fun showProgress()

        fun notifyLoadingFailed()

        fun notifyLoadingFailed(code: Int, message: String)

        fun notifyLoadingFailed(t: Throwable)
    }

    interface Presenter : BasePresenter {

        fun loadFlickrImage(searchFor: String, page: Int, perPage: Int)

        fun setPhotoRecyclerViewModel(photoRecyclerViewModel: PhotoRecyclerViewModel)
    }
}