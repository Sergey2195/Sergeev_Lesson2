package com.lesson.sergeev_lesson2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lesson.sergeev_lesson2.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        thread {
            Thread.sleep(3000)
            runOnUiThread{
                binding.watchView.setNewTime(WatchTime(3,15,4))
            }
        }
    }
}