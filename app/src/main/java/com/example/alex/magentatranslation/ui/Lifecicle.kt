package com.example.alex.magentatranslation.ui

interface Lifecicle<V> {
    fun bind(view: V)

    fun unbind()
}