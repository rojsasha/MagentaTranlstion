package com.example.alex.magentatranslation.ui.api

import com.example.alex.magentatranslation.ui.api.dto.AvailableLanguages
import com.example.alex.magentatranslation.ui.api.dto.Translation
import retrofit2.Call
import retrofit2.http.*

interface YandexTranslateApi {
    @GET("v1.5/tr.json/getLangs")
    fun getLanguage(@Query("key") apikey: String,
                    @Query("ui") uiLangCode: String): Call<AvailableLanguages>

    @FormUrlEncoded
    @POST("v1.5/tr.json/translate")
    fun getTranslate(@Query("key") apikey: String,
                     @Field("text") text: String,
                     @Query("lang") lang: String,
                     @Query("format") format: String): Call<Translation>


}