package com.softpian.kotlinphotoapp.photolist

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jakewharton.rxbinding2.widget.RxTextView
import com.softpian.kotlinphotoapp.R
import com.softpian.kotlinphotoapp.data.source.PhotoRepository
import com.softpian.kotlinphotoapp.photoinfo.PhotoInfoFragment
import com.softpian.kotlinphotoapp.photoinfo.PhotoInfoPresenter
import com.softpian.kotlinphotoapp.photolist.adapter.PhotoListAdapter
import com.softpian.kotlinphotoapp.photolist.adapter.PhotoRecyclerViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_photo_list.*




class PhotoListFragment : Fragment(), PhotoListContract.View {

    override lateinit var presenter: PhotoListContract.Presenter

    private val viewDisposables = CompositeDisposable()

    lateinit var searchFor: String

    companion object {
        @JvmStatic
        fun newInstance() = PhotoListFragment()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.fragment_photo_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchFor = "Beautiful+New+Zealand"

        Glide.with(view)
                .load(R.drawable.unsplash)
                .apply(RequestOptions()
                        .centerCrop())
                .into(ivCollapsingArea)

        rvPhotoList.setHasFixedSize(true)
        rvPhotoList.layoutManager = GridLayoutManager(context, 3) as RecyclerView.LayoutManager?
        rvPhotoList.adapter = PhotoListAdapter { photoId  ->
            val photoInfoFragment = PhotoInfoFragment.newInstance(photoId)
            val photoInfoPresenter = PhotoInfoPresenter(PhotoRepository(activity?.application!!), photoInfoFragment)

            fragmentManager?.beginTransaction()?.run {
                add(R.id.screen_container, photoInfoFragment)
                addToBackStack("photoInfo")
                commit()
            }
        }

        presenter.setPhotoRecyclerViewModel(rvPhotoList.adapter as PhotoRecyclerViewModel)

        viewDisposables.addAll(
                RxTextView.editorActions(etSearch)
                        .filter {
                            it == EditorInfo.IME_ACTION_DONE
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            presenter.loadFlickrImage(searchFor, 1, 30)
                            val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                            inputMethodManager.hideSoftInputFromWindow(etSearch.windowToken, 0)
                        },

                RxTextView.afterTextChangeEvents(etSearch)
                        .filter {
                            it.editable().toString().isNotEmpty()
                        }
                        .map {
                            it.editable().toString().trim().replace("\\s+".toRegex(), "+")
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            searchFor = it
                        }
        )

        etSearch.setOnClickListener {
            etSearch.text.clear()
        }

        etSearch.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }
    }

    override fun onResume() {
        super.onResume()

        presenter.start()
    }

    override fun onStop() {
        super.onStop()

        presenter.stop()

        if (activity?.isFinishing!!) {
            viewDisposables.clear()
        }
    }

    override fun hideProgress() {
        pbIsLoading.visibility = View.GONE
    }

    override fun showProgress() {
        pbIsLoading.visibility = View.VISIBLE
    }

    override fun notifyLoadingFailed() {
        if (!isDetached) {
            Toast.makeText(context, "Loading photo failed", Toast.LENGTH_LONG).show()
        }
    }

    override fun notifyLoadingFailed(code: Int, message: String) {
        if (!isDetached) {
            Toast.makeText(context, "Loading photos failed", Toast.LENGTH_SHORT).show()
            Toast.makeText(context, "code: $code, message: $message", Toast.LENGTH_LONG).show()
        }
    }

    override fun notifyLoadingFailed(t: Throwable) {
        if (!isDetached) {
            Toast.makeText(context, "Loading photos failed, Exception occurred!", Toast.LENGTH_SHORT).show()
            Toast.makeText(context, "Error message: " + t.message, Toast.LENGTH_LONG).show()
        }
    }
}
