package com.lesson.sergeev_lesson2.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.lesson.sergeev_lesson2.R
import com.lesson.sergeev_lesson2.databinding.ActivityMainBinding
import com.lesson.sergeev_lesson2.router.Router
import com.lesson.sergeev_lesson2.viewModels.MainViewModel
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
        } else {
            if (supportFragmentManager.findFragmentByTag(Router.LOGIN_SCREEN_TAG) != null) {
                setupLoginScreen()
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
        setupActionBar(getString(R.string.toolbar_main_title), false)
        viewModel.openMainScreen()
    }


    private fun openVacanciesScreen() {
        if (binding.bottomNavigation.selectedItemId == R.id.vacancyBottomBtn) return
        setupActionBar(getString(R.string.toolbar_vacancies_title), false)
        viewModel.openVacanciesScreen()
    }

    private fun openOfficesScreen() {
        if (binding.bottomNavigation.selectedItemId == R.id.officesBottomBtn) return
        setupActionBar(getString(R.string.toolbar_offices_title), false)
        viewModel.openOfficesScreen()
    }

    fun openOfficeDetails(officeName: String) {
        viewModel.openDetailsScreen(officeName)
        setupActionBar(officeName, true)
    }

    private fun setupActionBar(title: String, backButtonVisible: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(backButtonVisible)
        supportActionBar?.title = title
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onBackPressedHandling() {
        onBackPressedDispatcher.addCallback(this) {
            when {
                isOnMainScreen() -> hideApp()
                isOnOfficeDetails() -> exitFromDetailsScreen()
                else -> binding.bottomNavigation.selectedItemId = R.id.mainScreenBottomBtn
            }
        }
    }

    private fun exitFromDetailsScreen() {
        supportFragmentManager.popBackStack()
        setupActionBar(getString(R.string.toolbar_offices_title), false)
    }

    private fun isOnOfficeDetails(): Boolean {
        return binding.bottomNavigation.selectedItemId == R.id.officesBottomBtn
                && supportFragmentManager.findFragmentByTag(Router.OFFICE_DETAILS_TAG) != null
    }

    private fun isOnMainScreen(): Boolean {
        return binding.bottomNavigation.selectedItemId == R.id.mainScreenBottomBtn
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
        setupLoginScreen()
    }

    private fun setupLoginScreen() {
        binding.bottomNavigation.visibility = View.GONE
        supportActionBar?.title = getString(R.string.toolbar_login_title)
    }

    fun startMainScreen() = lifecycleScope.launch {
        delay(1000)
        viewModel.openMainScreen()
        binding.bottomNavigation.isVisible = true
        supportActionBar?.title = getString(R.string.toolbar_main_title)
    }
}