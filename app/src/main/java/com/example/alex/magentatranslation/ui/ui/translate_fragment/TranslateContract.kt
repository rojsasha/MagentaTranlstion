package com.example.alex.magentatranslation.ui.ui.translate_fragment


import android.content.Intent
import com.example.alex.magentatranslation.ui.Lifecicle
import com.example.alex.magentatranslation.ui.api.dto.AvailableLanguages


interface TranslateContract {
    interface View {
        fun successDownloadedLanguage()
        fun onError(text: String)
        fun changeTargetLanguage(lang: String)
        fun changeFinalLanguage(lang: String)
        fun showTranslatedText(text: String)
    }

    interface Presenter : Lifecicle<View> {
        fun getAvailableLanguage()
        fun getLangs(): HashMap<String, String>
        fun setOnActivityResult(intent: Intent, requestCode: Int)
        fun translateText(text: String)
    }
}