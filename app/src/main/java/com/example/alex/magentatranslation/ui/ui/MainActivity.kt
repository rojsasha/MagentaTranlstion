package com.example.alex.magentatranslation.ui.ui

import android.app.ProgressDialog.show
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.example.alex.magentatranslation.R
import com.example.alex.magentatranslation.ui.ui.history_fragment.HistoryFragment
import com.example.alex.magentatranslation.ui.ui.translate_fragment.AboutFragment
import com.example.alex.magentatranslation.ui.ui.translate_fragment.TranslateFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var fm: FragmentManager? = null
    private var active: Fragment? = null
    private var translateFragment: TranslateFragment? = null
    private var historyFragment: HistoryFragment? = null
    private var aboutFragment: AboutFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        addFragments()
    }

    private fun addFragments() {
        translateFragment = TranslateFragment()
        historyFragment = HistoryFragment()
        aboutFragment = AboutFragment()
        fm = supportFragmentManager
        fm!!.beginTransaction().add(R.id.fragmentContainer, aboutFragment!!, "3").hide(aboutFragment!!).commit()
        fm!!.beginTransaction().add(R.id.fragmentContainer, historyFragment!!, "2").hide(historyFragment!!).commit()
        fm!!.beginTransaction().add(R.id.fragmentContainer, translateFragment!!, "1").commit()
        active = translateFragment
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                switchFragment(translateFragment!!)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                switchFragment(historyFragment!!)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                switchFragment(aboutFragment!!)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun switchFragment(fragment: Fragment) {
        fm!!.beginTransaction().hide(active!!).show(fragment).commit()
        active = fragment
    }


}
