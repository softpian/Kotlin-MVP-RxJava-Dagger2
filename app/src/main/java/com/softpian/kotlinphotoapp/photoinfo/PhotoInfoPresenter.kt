package com.softpian.kotlinphotoapp.photoinfo

import com.softpian.kotlinphotoapp.data.source.PhotoRepository
import com.softpian.kotlinphotoapp.util.getDateFromUnixTimestamp
import com.softpian.kotlinphotoapp.util.getNumberFormatted
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class PhotoInfoPresenter(val photoRepository: PhotoRepository, val view: PhotoInfoContract.View) : PhotoInfoContract.Presenter  {

    init {
        view.presenter = this
    }
    override var photoId: String = ""

    internal val disposables = CompositeDisposable()

    override fun start() {
        loadPhotoInfo(photoId)
    }

    override fun stop() {
        disposables.clear()
    }

    override fun loadPhotoInfo(photoId: String) {
        disposables.add(
                photoRepository.getFlickrPhotoInfo(photoId)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe { view.showProgress() }
                        .doOnTerminate { view.hideProgress() }
                        .subscribe({
                            if ("ok".equals(it.stat)) {
                                view.showToolbarItem(it.photo.owner.getBuddyIcon(), it.photo.owner.username)

                                val commentCountFormatted = getNumberFormatted(it.photo.comments._content.toLong())
                                val viewCountFormatted = getNumberFormatted(it.photo.views.toLong())
                                val dateLastUpdated = getDateFromUnixTimestamp(it.photo.dates.lastupdate)

                                view.showEverythingWithoutToolbar(it.photo.getPhotoUrl(), it.photo.title._content, dateLastUpdated,
                                        commentCountFormatted, viewCountFormatted, it.photo.description._content)

                            } else {
                                view.notifyLoadingFailed(it.code, it.message)
                            }
                        }) {
                            view.notifyLoadingFailed(it)
                        }
        )
    }
}