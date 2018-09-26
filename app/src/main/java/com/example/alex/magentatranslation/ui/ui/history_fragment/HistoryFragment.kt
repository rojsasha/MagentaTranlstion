package com.example.alex.magentatranslation.ui.ui.history_fragment

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alex.magentatranslation.R
import com.example.alex.magentatranslation.ui.YandexApplication
import com.example.alex.magentatranslation.ui.data.entity.TranslatedHistory

class HistoryFragment : Fragment() {
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: AdapterHistory? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_select_language, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewElement(view)
        mAdapter = AdapterHistory()
        mRecyclerView!!.adapter = mAdapter
        observe()
    }

    private fun observe() {
        val db = YandexApplication[context!!].getDB()
        val historyObserver: Observer<List<TranslatedHistory>> = Observer {
            mAdapter!!.setHistoryItems(it!!)
        }

        val translated = db!!.languageDao().getAll()
        translated.observe(this, historyObserver)
    }


    private fun initViewElement(view: View) {
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.title = "история поиска"
        mRecyclerView = view.findViewById(R.id.recyclerView)



    }
}