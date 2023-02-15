package com.lesson.sergeev_lesson2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.lesson.sergeev_lesson2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.bottomButtonsGroup.referencedIds.forEach { view->
            findViewById<Button>(view).setOnClickListener{setProgressState()}
        }
    }

    private fun setProgressState(){
        binding.progressGroup.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun setupViews() {
        supportActionBar?.title = getString(R.string.action_bar_title)
    }
}