package com.cartelnetwork.feproblem1.application

import android.app.Application

class MyApplication : Application() {

    lateinit var sInstance : MyApplication
    init {
        sInstance = this
    }
    override fun onCreate() {
        super.onCreate()
        sInstance = this
    }

}