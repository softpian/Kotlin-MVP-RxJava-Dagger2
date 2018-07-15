package com.softpian.kotlinphotoapp.photolist.adapter

import com.softpian.kotlinphotoapp.data.photodata.Photo

interface PhotoRecyclerViewModel {

    fun addAllItem(photoItems: List<Photo>)

    fun getItem(position: Int): Photo

    fun getItemCount(): Int

    fun notifyDataSetChangeWrapper()

    fun notifyItemRangeInsertedWrapper(positionStart: Int, itemCount: Int)
}