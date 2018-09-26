package com.example.alex.magentatranslation.ui

import android.app.Application
import android.content.Context
import com.example.alex.magentatranslation.ui.api.YandexService
import com.example.alex.magentatranslation.ui.api.YandexTranslateApi
import com.example.alex.magentatranslation.ui.data.YandexDatabase

class YandexApplication : Application() {
    var mService: YandexTranslateApi? = null
     var mDB: YandexDatabase? = null

    override fun onCreate() {
        super.onCreate()
        mService = YandexService.initRetrofit()
        mDB = YandexDatabase.getInstance(this)
    }

    companion object {
        operator fun get(context: Context): YandexApplication {
            return context.applicationContext as YandexApplication
        }
    }

    fun getService(): YandexTranslateApi? {
        return mService
    }

    fun getDB(): YandexDatabase? {
        return mDB
    }


}