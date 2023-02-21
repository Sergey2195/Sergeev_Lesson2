package com.lesson.sergeev_lesson2.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.window.OnBackInvokedDispatcher
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.BuildCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.lesson.sergeev_lesson2.R
import com.lesson.sergeev_lesson2.viewModels.MainViewModel
import com.lesson.sergeev_lesson2.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.attachRouter(this)
        if (savedInstanceState == null) {
            viewModel.openOfficesScreen()
        }
        setBottomNavigationBarClickListeners()
//        onBackPressedHandling()
    }

    private fun setBottomNavigationBarClickListeners() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.mainScreenBottomBtn -> {
                    onBackPressedDispatcher.onBackPressed()
                    true
                }
                R.id.vacancyBottomBtn -> {
                    viewModel.openVacanciesScreen()
                    true
                }
                R.id.officesBottomBtn -> {
                    Log.d("ssv", "offices")
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

//    private fun onBackPressedHandling() {
//        onBackPressedDispatcher.addCallback(this) {
//            if (binding.bottomNavigation.selectedItemId == R.id.mainScreenBottomBtn){
//                hideApp()
//            }else{
//                goToMainFragment()
//            }
//        }
//    }

//    private fun goToMainFragment(){
//        val fragment = supportFragmentManager.findFragmentByTag(MAIN_SCREEN_NAME) ?: return
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.mainFragmentContainerView, fragment)
//            .commit()
//    }

//    private fun hideApp(){
//        moveTaskToBack(true)
//    }

    override fun onDestroy() {
        viewModel.detachRouter()
        super.onDestroy()
    }

    private fun startLoginScreen() {
        viewModel.openLoginScreen()
        binding.bottomNavigation.isVisible = false
    }

    fun startMainScreen() = lifecycleScope.launch {
        delay(500)
        viewModel.openMainScreen()
        binding.bottomNavigation.isVisible = true
    }

    companion object{
        const val MAIN_SCREEN_NAME = "main screen"
    }
}