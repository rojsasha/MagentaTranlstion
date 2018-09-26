package com.example.alex.magentatranslation.ui.ui.select_language_activity

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.alex.magentatranslation.R
import java.util.ArrayList

class RecyclerLanguageAdapter(private val listKeys: ArrayList<String>,
                              private val listValues: ArrayList<String>,
                              private val mCallback: RecyclerLanguageAdapter.onClickAdapter) : RecyclerView.Adapter<RecyclerLanguageAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(p0.context)
        val lp = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val view = layoutInflater.inflate(R.layout.item_list, null, false)
        view.layoutParams = lp
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listValues.size
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        holder.tvLanguage.text = listValues[pos]
        holder.tvLanguage.setOnClickListener { mCallback.submitLanguage(listKeys[pos],listValues[pos]) }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvLanguage = itemView.findViewById(R.id.tvLanguage) as TextView
    }

    interface onClickAdapter {
        fun submitLanguage(key: String, lang: String)
    }
}