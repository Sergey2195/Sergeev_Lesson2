package com.lesson.sergeev_lesson2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class DifferentLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_layout)
    }

    companion object {
        fun newInstance(context: Context): Intent =
            Intent(context, DifferentLayoutActivity::class.java)
    }
}