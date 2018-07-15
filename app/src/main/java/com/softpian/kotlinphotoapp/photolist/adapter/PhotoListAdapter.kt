package com.softpian.kotlinphotoapp.photolist.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.softpian.kotlinphotoapp.R
import com.softpian.kotlinphotoapp.data.photodata.Photo
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.photo_item_list.*

class PhotoListAdapter(val onPhotoClickedListener: (String) -> Unit) : RecyclerView.Adapter<PhotoListAdapter.PhotoListViewHolder>(), PhotoRecyclerViewModel {

    private var photoItems: MutableList<Photo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PhotoListViewHolder(parent, onPhotoClickedListener)

    override fun getItemCount() = photoItems.size

    override fun onBindViewHolder(holder: PhotoListViewHolder, position: Int) {
        photoItems[position].let { photo ->
            with(holder) {
                Glide.with(itemView.context)
                        .load(photo.getPhotoUrl())
                        .apply(RequestOptions()
                                .placeholder(R.drawable.ic_photo_placeholder)
                                .centerCrop())
                        .into(ivPhoto)

                photoId = photo.id
            }
        }
    }

    override fun addAllItem(photoItems: List<Photo>) {
        this.photoItems.clear()
        this.photoItems.addAll(photoItems)
    }

    override fun getItem(position: Int) = photoItems[position]

    override fun getItemId(position: Int) = photoItems[position].id.toLong()

    override fun notifyDataSetChangeWrapper() {
        notifyDataSetChanged()
    }

    override fun notifyItemRangeInsertedWrapper(positionStart: Int, itemCount: Int) {
        notifyItemRangeInserted(positionStart, itemCount)
    }

    // Make use of LayoutContainer in order to improve the performance
    // - Using LayoutContainer prevents Kotlin Android Extensions from calling 'findViewById'
    //   each time onBindViewHolder is called.
    // - Instead, it adopts cache mechanism
    //   because calling 'findViewById' frequently decreases the performance of RecyclerView.
    abstract class AndroidExtensionsViewHolder(override val containerView: View)
        : RecyclerView.ViewHolder(containerView), LayoutContainer

    class PhotoListViewHolder(parent: ViewGroup, val onPhotoClickedListener: (String) -> Unit)
        : AndroidExtensionsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.photo_item_list, parent, false)) {

        init {
            itemView.setOnClickListener {
                onPhotoClickedListener(photoId!!)
            }
        }

        var photoId: String? = null
    }
}