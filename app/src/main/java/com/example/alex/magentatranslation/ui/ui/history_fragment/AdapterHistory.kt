package com.example.alex.magentatranslation.ui.ui.history_fragment

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.alex.magentatranslation.R
import com.example.alex.magentatranslation.ui.YandexApplication
import com.example.alex.magentatranslation.ui.data.entity.TranslatedHistory


class AdapterHistory() : RecyclerView.Adapter<AdapterHistory.ViewHolder>() {
    private var listHistory: List<TranslatedHistory>? = null
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(p0.context)
        val lp = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val view = layoutInflater.inflate(R.layout.item_history, null, false)
        view.layoutParams = lp
        return ViewHolder(view)
    }

    fun setHistoryItems(listHistory: List<TranslatedHistory>) {
        this.listHistory = listHistory
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        if (listHistory != null)
            return listHistory!!.size
        return 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mTvCurrentText.text = listHistory!![position].targetText
        holder.mTvFinalText.text = listHistory!![position].finalText
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTvCurrentText = itemView.findViewById<TextView>(R.id.tvHistoryCurrent)!!
        val mTvFinalText = itemView.findViewById<TextView>(R.id.tvHistoryFinal)!!
    }
}