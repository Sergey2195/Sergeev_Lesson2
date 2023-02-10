package com.lesson.sergeev_lesson2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.google.android.material.snackbar.Snackbar
import com.lesson.sergeev_lesson2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val activityLauncher = registerForActivityResult(SecondActivityContract()) { result ->
        when (result) {
            null -> resultFailure()
            else -> resultSuccess(result)
        }
    }

    private fun resultFailure() {
        showSnackBar()
    }

    private fun showSnackBar() {
        Snackbar.make(binding.mainLayout, getString(R.string.save_failure), Snackbar.LENGTH_SHORT)
            .show()
    }

    private fun resultSuccess(result: String) {
        binding.firstActivityTV.text = result
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupClickListeners()
        activityResultRegistry
    }

    private fun setupClickListeners() {
        binding.changeToRuButton.setOnClickListener { changeLocale(RU_LANG) }
        binding.changeToEnglishButton.setOnClickListener { changeLocale(EN_LANG) }
        binding.goToSecondActivityBtn.setOnClickListener {
            activityLauncher.launch(Unit)
        }
    }

    private fun changeLocale(localeLang: String) {
        val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags(localeLang)
        AppCompatDelegate.setApplicationLocales(appLocale)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val text = binding.firstActivityTV.text.toString()
        if (text != getString(R.string.default_text)) {
            outState.putString(TEXT_VIEW_VALUE_KEY, text)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.firstActivityTV.text = savedInstanceState.getString(
            TEXT_VIEW_VALUE_KEY,
            getString(R.string.default_text)
        )
    }

    companion object {
        private const val RU_LANG = "ru"
        private const val EN_LANG = "en"
        private const val TEXT_VIEW_VALUE_KEY = "key_tv"
    }
}