package com.example.alex.magentatranslation.ui.api.dto

import java.util.HashMap

data class AvailableLanguages(var dirs: Array<String>,
                              var langs: HashMap<String, String>? = null)
