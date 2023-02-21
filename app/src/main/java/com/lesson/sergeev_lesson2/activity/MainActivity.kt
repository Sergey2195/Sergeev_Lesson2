package com.lesson.sergeev_lesson2.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.lesson.sergeev_lesson2.viewModels.MainViewModel
import com.lesson.sergeev_lesson2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.attachRouter(this)
        if (savedInstanceState == null){
            startLoginScreen()
        }
//        setupViews()
//        setupClickListeners()
    }
    override fun onDestroy() {
        viewModel.detachRouter()
        super.onDestroy()
    }

    private fun startLoginScreen(){
        viewModel.openLoginScreen()
        binding.bottomNavigation.isVisible = false
    }

    fun startMainScreen(){
        viewModel.openMainScreen()
        binding.bottomNavigation.isVisible = true
    }

    //
//    private fun setupClickListeners() {
//        binding.bottomButtonsGroup?.referencedIds?.forEach { view ->
//            findViewById<Button>(view).setOnClickListener { setProgressState() }
//        }
//    }
//
//    private fun setProgressState() {
//        binding.progressGroup?.visibility = View.GONE
//        binding.progressBar?.visibility = View.VISIBLE
//    }
//
//    private fun setupViews() {
//        supportActionBar?.title = getString(R.string.action_bar_title)
//    }
}