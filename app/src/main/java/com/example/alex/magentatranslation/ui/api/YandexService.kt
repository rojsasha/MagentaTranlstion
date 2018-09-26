package com.example.alex.magentatranslation.ui.api

import com.example.alex.magentatranslation.BuildConfig.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class YandexService {


    companion object GetService {

        private var mService: YandexTranslateApi? = null
        fun initRetrofit(): YandexTranslateApi? {
            if (mService == null) {
                return Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(YandexTranslateApi::class.java)
            }
            return mService

        }
    }

}