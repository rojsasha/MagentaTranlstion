package com.example.alex.magentatranslation.ui.ui.translate_fragment

import android.content.Intent
import com.example.alex.magentatranslation.ui.api.YandexTranslateApi
import com.example.alex.magentatranslation.ui.api.dto.AvailableLanguages
import com.example.alex.magentatranslation.ui.api.dto.Translation
import com.example.alex.magentatranslation.ui.data.YandexDatabase
import com.example.alex.magentatranslation.ui.data.entity.FinalLanguage
import com.example.alex.magentatranslation.ui.data.entity.TargetLanguage
import com.example.alex.magentatranslation.ui.data.entity.TranslatedHistory
import com.example.alex.magentatranslation.ui.utils.YandexSharedPreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.HashMap


class TranslatePresenter(private val service: YandexTranslateApi?,
                         private val db: YandexDatabase?,
                         private val yandexSharedPreference: YandexSharedPreference)
    : TranslateContract.Presenter {

    private var timer: Timer = Timer()
    private val DELAY: Long = 700
    private var mView: TranslateContract.View? = null
    private var langs: HashMap<String, String>? = null

    private var keyTarget: String = ""
    private var keyFinal: String = ""
    private var langTarget: String = ""
    private var langFinal: String = ""
    private var textTarget: String = ""
    private var textFinal: String = ""

    private val API_KEY = "trnsl.1.1.20180914T153201Z.6679527382133e40.ce7a2189aad395c7383323ced80e8f56f38b19ad"

    override fun getAvailableLanguage() {
        service!!.getLanguage(API_KEY
                , "ru").enqueue(object : Callback<AvailableLanguages> {
            override fun onFailure(call: Call<AvailableLanguages>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<AvailableLanguages>?, response: Response<AvailableLanguages>?) {
                if (response != null && response.isSuccessful) {
                    if (isViewAttached()) {
                        mView!!.successDownloadedLanguage()
                        langs = response.body()!!.langs
                        savedLanguage()
                    }
                }
            }
        })
    }

    private fun savedLanguage() {
        val targetModel: TargetLanguage = db!!.languageDao().getTargetLanguage()
        val finalModel: FinalLanguage = db.languageDao().getFinalLanguage()
        if (targetModel != null) {
            mView!!.changeTargetLanguage(targetModel.lang)
            keyTarget = targetModel.key
        }

        if (finalModel != null) {
            mView!!.changeFinalLanguage(finalModel.lang)
            keyFinal = finalModel.key
        }

    }

    override fun translateText(text: String) {
        if (isLanguageSelected()) {
            timer.cancel()
            timer = Timer()
            timer.schedule(
                    object : TimerTask() {
                        override fun run() {
                            translate(text)
                        }
                    },
                    DELAY
            )
        } else {
            mView!!.onError("Вы не выбрали язык")
        }

    }

    private fun isLanguageSelected(): Boolean {
        return !keyFinal.isEmpty() && !keyTarget.isEmpty()
    }

    private fun translate(text: String) {
        textTarget = text
        service!!.getTranslate(API_KEY, text, "$keyTarget-$keyFinal", "html")
                .enqueue(object : Callback<Translation> {
                    override fun onFailure(call: Call<Translation>?, t: Throwable?) {
                    }

                    override fun onResponse(call: Call<Translation>?, response: Response<Translation>?) {
                        if (response?.body() != null && response.isSuccessful) {
                            if (isViewAttached()) {
                                mView!!.showTranslatedText(response.body()!!.text[0])
                                textFinal = response.body()!!.text[0]
                                saveToHistory()
                            }
                        }
                    }
                })

    }

    private fun saveToHistory() {
        db!!.languageDao().insert(TranslatedHistory(textTarget, textFinal))
    }

    override fun getLangs(): HashMap<String, String> {
        return langs!!
    }

    override fun setOnActivityResult(intent: Intent, requestCode: Int) {
        when (requestCode) {
            1 -> {

                mView!!.changeTargetLanguage(intent.getStringExtra("lang"))
                db!!.languageDao().saveTargetLanguage(TargetLanguage(1, intent.getStringExtra("key"),
                        intent.getStringExtra("lang")))
                keyTarget = intent.getStringExtra("key")
            }
            2 -> {

                mView!!.changeFinalLanguage(intent.getStringExtra("lang"))
                db!!.languageDao().saveFinalLanguage(FinalLanguage(1, intent.getStringExtra("key"),
                        intent.getStringExtra("lang")))
                keyFinal = intent.getStringExtra("key")
            }
        }
    }

    override fun bind(view: TranslateContract.View) {
        mView = view
    }

    override fun unbind() {
        mView = null
    }

    private fun isViewAttached(): Boolean {
        return mView != null
    }
}