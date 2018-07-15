package com.softpian.kotlinphotoapp.photoinfo

import com.softpian.kotlinphotoapp.BasePresenter
import com.softpian.kotlinphotoapp.BaseView

class PhotoInfoContract {

    interface View : BaseView<Presenter> {

        fun hideProgress()

        fun showProgress()

        fun notifyLoadingFailed()

        fun notifyLoadingFailed(code: Int, message: String)

        fun notifyLoadingFailed(t: Throwable)

        fun showToolbarItem(buddyIconUrl: String, ownerName: String)

        fun showEverythingWithoutToolbar(photoUrl: String, title: String, date: String, commentCount: String, viewCount: String, description: String);
    }

    interface Presenter : BasePresenter {

        var photoId: String

        fun loadPhotoInfo(photoId: String)
    }

}