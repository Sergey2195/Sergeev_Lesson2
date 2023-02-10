package com.lesson.sergeev_lesson2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.lesson.sergeev_lesson2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupClickListeners()
    }

    private fun setupClickListeners(){
        binding.changeToRuButton.setOnClickListener { changeLocale(RU_LANG) }
        binding.changeToEnglishButton.setOnClickListener { changeLocale(EN_LANG) }
        binding.goToSecondActivityBtn.setOnClickListener {
            startActivity(SecondActivity.newInstance(this))
        }
    }

    private fun changeLocale(localeLang: String) {
        val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags(localeLang)
        AppCompatDelegate.setApplicationLocales(appLocale)
    }

    companion object {
        private const val RU_LANG = "ru"
        private const val EN_LANG = "en"
    }
}