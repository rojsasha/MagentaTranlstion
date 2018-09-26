package com.example.alex.magentatranslation.ui.ui.select_language_activity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.widget.Toast
import com.example.alex.magentatranslation.R
import java.util.ArrayList

class SelectLanguageActivity : AppCompatActivity(), SelectLanguageContract.View, RecyclerLanguageAdapter.onClickAdapter {


    private var mPresenter: SelectLanguageContract.Presenter? = null
    private var mRecyclerView: RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_language)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        mRecyclerView = findViewById(R.id.recyclerView)
        mPresenter = SelectLanguagePresenter()

        mPresenter!!.bind(this)

        mPresenter!!.setIntentData(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun submitLanguage(key: String, lang: String) {
        Toast.makeText(this, "$key $lang", Toast.LENGTH_LONG).show()
        setResult(Activity.RESULT_OK, Intent()
                .putExtra("key", key)
                .putExtra("lang", lang))
        finish()
    }


    override fun showLanguage(listKeys: ArrayList<String>, listValues: ArrayList<String>) {
        mRecyclerView!!.adapter = RecyclerLanguageAdapter(listKeys, listValues, this)
        Toast.makeText(this, " bnjkhjkhk" +
                "" + listValues.size, Toast.LENGTH_LONG).show()

        val adapter = RecyclerLanguageAdapter(listKeys, listValues, this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter!!.unbind()
    }
}
