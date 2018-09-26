package com.example.alex.magentatranslation.ui.ui.translate_fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.alex.magentatranslation.R
import com.example.alex.magentatranslation.ui.YandexApplication
import com.example.alex.magentatranslation.ui.api.dto.AvailableLanguages
import com.example.alex.magentatranslation.ui.ui.select_language_activity.SelectLanguageActivity
import com.example.alex.magentatranslation.ui.utils.YandexSharedPreference

class TranslateFragment : Fragment(), TranslateContract.View {

    private var mPresenter: TranslateContract.Presenter? = null
    private var mTvTargetText: TextView? = null
    private var mTvFinalText: TextView? = null
    private var etInputLanguage: EditText? = null
    private var tvResultTranslate: TextView? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_translate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewElement(view)
        mPresenter = TranslatePresenter(YandexApplication[context!!].getService(),
                YandexApplication[context!!].getDB(),
                YandexSharedPreference(context!!))
        mPresenter!!.bind(this)
        mPresenter!!.getAvailableLanguage()
        Toast.makeText(context, "satrt", Toast.LENGTH_LONG).show()
    }

    private fun initViewElement(view: View?) {
        mTvFinalText = view!!.findViewById(R.id.tvFinalText)
        mTvTargetText = view.findViewById(R.id.tvTargetText)
        etInputLanguage = view.findViewById(R.id.etInputLanguage)
        tvResultTranslate = view.findViewById(R.id.tvResultTranslate)

        etInputLanguage!!.addTextChangedListener(editor)

    }

    private val editor = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            val text = s.toString()
            mPresenter!!.translateText(text)
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {


        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val text = s.toString()
            Handler().postDelayed({}, 700)

        }
    }

    private fun intentSelectLanguage(requestCode: Int) {
        startActivityForResult(Intent(context, SelectLanguageActivity::class.java)
                .putExtra("langs", mPresenter!!.getLangs()), requestCode)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null) {
            mPresenter!!.setOnActivityResult(data, requestCode)
        }
    }

    override fun changeTargetLanguage(lang: String) {
        mTvTargetText!!.text = lang
    }

    override fun changeFinalLanguage(lang: String) {
        mTvFinalText!!.text = lang
    }

    override fun showTranslatedText(text: String) {
        tvResultTranslate!!.text = text
    }

    override fun successDownloadedLanguage() {
        mTvTargetText!!.setOnClickListener { intentSelectLanguage(1) }
        mTvFinalText!!.setOnClickListener { intentSelectLanguage(2) }
    }

    override fun onError(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter!!.unbind()
    }
}
