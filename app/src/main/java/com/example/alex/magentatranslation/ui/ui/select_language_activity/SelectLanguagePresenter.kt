package com.example.alex.magentatranslation.ui.ui.select_language_activity

import android.content.Intent
import java.util.ArrayList
import java.util.HashMap

class SelectLanguagePresenter : SelectLanguageContract.Presenter {


    override fun setIntentData(intent: Intent) {
        val langs: HashMap<String, String> = intent.getSerializableExtra("langs") as HashMap<String, String>
        val listKeys = ArrayList(langs.keys)
        val listValues = ArrayList(langs.values)

        mView!!.showLanguage(listKeys, listValues)


    }

    private var mView: SelectLanguageContract.View? = null


    override fun bind(view: SelectLanguageContract.View) {
        mView = view
    }

    override fun unbind() {
        mView = null
    }
}