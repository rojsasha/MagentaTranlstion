package com.example.alex.magentatranslation.ui.ui.select_language_activity

import android.content.Intent
import com.example.alex.magentatranslation.ui.Lifecicle
import java.util.ArrayList

interface SelectLanguageContract {
    interface View {
        fun showLanguage(listKeys: ArrayList<String>, listValues: ArrayList<String>)

    }

    interface Presenter : Lifecicle<View> {
        fun setIntentData(intent: Intent)

    }
}