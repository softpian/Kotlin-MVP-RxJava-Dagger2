package com.softpian.kotlinphotoapp.photolist

import com.softpian.kotlinphotoapp.data.source.PhotoRepository
import com.softpian.kotlinphotoapp.photolist.adapter.PhotoRecyclerViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class PhotoListPresenter(val photoRepository: PhotoRepository, val view: PhotoListContract.View) : PhotoListContract.Presenter {

    init {
        view.presenter = this
    }

    private lateinit var photoRecyclerViewModel: PhotoRecyclerViewModel

    private val disposables = CompositeDisposable()

    override fun start() {
        loadFlickrImage((view as PhotoListFragment).searchFor, 1, 30)
    }

    override fun stop() {
        disposables.clear()
    }

    override fun setPhotoRecyclerViewModel(photoRecyclerViewModel: PhotoRecyclerViewModel) {
        this.photoRecyclerViewModel = photoRecyclerViewModel
    }

    override fun loadFlickrImage(searchFor: String, page: Int, perPage: Int) {
        disposables.add(
                photoRepository.getFlickrPhotoSearch(searchFor, page, perPage)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe { view.showProgress() }
                        .doOnTerminate { view.hideProgress() }
                        .subscribe({
                            if ("ok".equals(it.stat)) {
                                photoRecyclerViewModel.addAllItem(it.photos.photo)
                                photoRecyclerViewModel.notifyDataSetChangeWrapper()
                            } else {
                                view.notifyLoadingFailed(it.code, it.message)
                            }
                        }) {
                            view.notifyLoadingFailed(it)
                        }
        )
    }
}
