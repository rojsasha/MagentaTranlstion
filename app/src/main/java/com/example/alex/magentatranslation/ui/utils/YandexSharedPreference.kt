package com.example.alex.magentatranslation.ui.utils

import android.content.Context

class YandexSharedPreference(context: Context) {
    private val PREFERENCE_NAME = "yandexPreference"
    private val CURRENT_LANG = "currentLang"
    private val FINAL_LANG = "finalLang"

    val preference = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)!!


    fun saveTargetLanguage(lang: String) {
        preference.edit().putString(CURRENT_LANG, lang).apply()
    }

    fun saveFinalLanguage(lang: String) {
        preference.edit().putString(FINAL_LANG, lang).apply()
    }

    fun getTargetLanguage(): String {
       return preference.getString(CURRENT_LANG, "")

    }

    fun getFinalLanguage():String {
        return preference.getString(FINAL_LANG, "")
    }


}