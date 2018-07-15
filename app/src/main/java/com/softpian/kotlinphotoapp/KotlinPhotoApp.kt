package com.softpian.kotlinphotoapp

import android.app.Application
import com.softpian.kotlinphotoapp.di.AppComponent
import com.softpian.kotlinphotoapp.di.DaggerAppComponent

class KotlinPhotoApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
    }
}