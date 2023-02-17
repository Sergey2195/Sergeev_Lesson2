package com.lesson.sergeev_lesson2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.lesson.sergeev_lesson2.databinding.ActivityMainBinding
import java.util.Calendar
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val time = WatchTime(0,0,0)
        var i = 0
        thread{
            while (true){
                val currTime = Calendar.getInstance()
                val hour = currTime.get(Calendar.HOUR)
                val minutes = currTime.get(Calendar.MINUTE)
                val seconds = currTime.get(Calendar.SECOND)
                time.setTime(hour, minutes, seconds)
                binding.watchView.setNewTime(time)
                if (i ==3){
                    runOnUiThread{
                        with(binding.watchView){
                            updateHoursRatio(1f)
                            updateMinutesRatio(1.2f)
                            updateSecondsRatio(1.4f)
                        }
                    }
                }
                Thread.sleep(1000)
                i++
            }
        }
    }
}