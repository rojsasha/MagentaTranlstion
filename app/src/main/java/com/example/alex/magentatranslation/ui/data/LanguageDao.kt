package com.example.alex.magentatranslation.ui.data

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.example.alex.magentatranslation.ui.data.entity.FinalLanguage
import com.example.alex.magentatranslation.ui.data.entity.TargetLanguage
import com.example.alex.magentatranslation.ui.data.entity.TranslatedHistory

@Dao
interface LanguageDao {

    @Insert(onConflict = REPLACE)
    fun insert(model: TranslatedHistory)

    @Query("DELETE from TranslatedHistory")
    fun deleteAll()

    @Query("SELECT * FROM TranslatedHistory")
    fun getAll(): LiveData<List<TranslatedHistory>>

    @Insert(onConflict = REPLACE)
    fun saveTargetLanguage(model: TargetLanguage)

    @Insert(onConflict = REPLACE)
    fun saveFinalLanguage(model: FinalLanguage)

    @Query("DELETE from TargetLanguage")
    fun deleteTargetLanguage()

    @Query("DELETE from FinalLanguage")
    fun deleteFinalLanguage()

    @Query("SELECT * from TargetLanguage")
    fun getTargetLanguage(): TargetLanguage

    @Query("SELECT * from FinalLanguage")
    fun getFinalLanguage(): FinalLanguage

}