package com.example.alex.magentatranslation.ui.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.alex.magentatranslation.ui.data.entity.FinalLanguage
import com.example.alex.magentatranslation.ui.data.entity.TargetLanguage
import com.example.alex.magentatranslation.ui.data.entity.TranslatedHistory

@Database(entities = arrayOf((TranslatedHistory::class),
    (TargetLanguage::class),
    (FinalLanguage::class)), version = 1)

abstract class YandexDatabase : RoomDatabase() {
    abstract fun languageDao(): LanguageDao


    companion object {
        private var INSTANCE: YandexDatabase? = null

        fun getInstance(context: Context): YandexDatabase? {
            if (INSTANCE == null) {
                return Room.databaseBuilder(context,
                        YandexDatabase::class.java, "yandex.db")
                        .allowMainThreadQueries()
                        .build()

            }
            return INSTANCE
        }

    }
}