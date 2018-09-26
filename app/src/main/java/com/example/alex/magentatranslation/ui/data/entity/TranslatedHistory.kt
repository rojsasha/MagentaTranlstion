package com.example.alex.magentatranslation.ui.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class TranslatedHistory(

                             @ColumnInfo(name = "targetText") var targetText: String,
                             @ColumnInfo(name = "finalText") var finalText: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

    constructor() : this( "", "")
}