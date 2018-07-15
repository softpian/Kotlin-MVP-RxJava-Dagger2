package com.softpian.kotlinphotoapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.softpian.kotlinphotoapp.data.source.PhotoRepository
import com.softpian.kotlinphotoapp.photolist.PhotoListFragment
import com.softpian.kotlinphotoapp.photolist.PhotoListPresenter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val photoListFragment = PhotoListFragment()
        supportFragmentManager.beginTransaction()
                .replace(R.id.screen_container, photoListFragment)
                .commit()

        val photoListPresenter = PhotoListPresenter(PhotoRepository(this.application), photoListFragment)
    }
}
