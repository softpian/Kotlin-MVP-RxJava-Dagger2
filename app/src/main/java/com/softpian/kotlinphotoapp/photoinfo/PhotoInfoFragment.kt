package com.softpian.kotlinphotoapp.photoinfo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.softpian.kotlinphotoapp.R
import kotlinx.android.synthetic.main.fragment_photo_info.*

class PhotoInfoFragment : Fragment(), PhotoInfoContract.View {

    override lateinit var presenter: PhotoInfoContract.Presenter

    companion object {

        private val PHOTO_ID_KEY = "photo_id"

        @JvmStatic
        fun newInstance(photoId: String): PhotoInfoFragment {

            val fragment = PhotoInfoFragment()

            val args = Bundle()
            args.putString(PHOTO_ID_KEY, photoId)
            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            = inflater.inflate(R.layout.fragment_photo_info, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val photoId = arguments?.getString(PHOTO_ID_KEY)

        presenter.photoId = photoId!!

        ivPhoto.setOnClickListener {
            changeViewsVisibility()
        }

        ivClose.setOnClickListener {
            fragmentManager?.popBackStack()
        }

        hideProgress()
    }

    private fun changeViewsVisibility() {
        if (app_bar.visibility == View.VISIBLE) {
            app_bar.visibility = View.INVISIBLE
            detailedContentLayout.visibility = View.INVISIBLE
            view?.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE
        } else {
            app_bar.visibility = View.VISIBLE
            detailedContentLayout.visibility = View.VISIBLE
            view?.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        }
    }

    override fun onResume() {
        super.onResume()

        presenter.start()
    }

    override fun onStop() {
        super.onStop()

        presenter.stop()
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

    override fun showToolbarItem(buddyIconUrl: String, ownerName: String) {
        Glide.with(context!!)
                .load(buddyIconUrl)
                .apply(RequestOptions()
                        .placeholder(R.drawable.ic_photo_placeholder)
                        .centerCrop())
                .into(ivBuddyIcon)

        tvOwnerName.text = ownerName
    }

    override fun showEverythingWithoutToolbar(photoUrl: String, title: String, date: String, commentCount: String, viewCount: String, description: String) {
        Glide.with(context!!)
                .load(photoUrl)
                .apply(RequestOptions()
                        .placeholder(R.drawable.ic_photo_placeholder)
                        .centerCrop())
                .into(ivPhoto)

        tvTitle.text = title
        tvDate.text = date
        tvCommentCount.text = commentCount
        tvViewerCount.text = viewCount
        tvDescription.text = description
    }
}