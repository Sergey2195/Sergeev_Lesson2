package com.lesson.sergeev_lesson2.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
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
import com.lesson.sergeev_lesson2.fragments.OfficesFragment
import com.lesson.sergeev_lesson2.fragments.VacanciesFragment
import com.lesson.sergeev_lesson2.router.Router
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
            startLoginScreen()
        }else{
            if (supportFragmentManager.findFragmentByTag(Router.LOGIN_SCREEN_TAG) != null) {
                binding.bottomNavigation.visibility = View.GONE
            }
        }
        setBottomNavigationBarClickListeners()
        onBackPressedHandling()
    }

    private fun setBottomNavigationBarClickListeners() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.mainScreenBottomBtn -> {
                    openMainScreen()
                    true
                }
                R.id.vacancyBottomBtn -> {
                    openVacanciesScreen()
                    true
                }
                R.id.officesBottomBtn -> {
                    openOfficesScreen()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun openMainScreen() {
        if (binding.bottomNavigation.selectedItemId == R.id.mainScreenBottomBtn) return
        viewModel.openMainScreen()
    }


    private fun openVacanciesScreen() {
        if (binding.bottomNavigation.selectedItemId == R.id.vacancyBottomBtn) return
        viewModel.openVacanciesScreen()
    }

    private fun openOfficesScreen() {
        if (binding.bottomNavigation.selectedItemId == R.id.officesBottomBtn) return
        viewModel.openOfficesScreen()
    }

    private fun onBackPressedHandling() {
        onBackPressedDispatcher.addCallback(this) {
            if (binding.bottomNavigation.selectedItemId == R.id.mainScreenBottomBtn) {
                hideApp()
            } else {
                binding.bottomNavigation.selectedItemId = R.id.mainScreenBottomBtn
            }
        }
    }

    private fun hideApp() {
        moveTaskToBack(true)
    }

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
}