package com.example.alex.magentatranslation.ui.data.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class TargetLanguage(@PrimaryKey(autoGenerate = true) var id: Long,
                          var key: String,
                          var lang: String){
    constructor() : this(1, "", "")
}

