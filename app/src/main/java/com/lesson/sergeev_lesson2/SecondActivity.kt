package com.lesson.sergeev_lesson2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.lesson.sergeev_lesson2.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d("SSV", "onSaveInstanceState")
        val text = binding.editTextTextPersonName.text.toString()
        outState.putString(EDIT_TEXT_KEY, text)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Log.d("SSV", "onRestoreInstanceState")
        super.onRestoreInstanceState(savedInstanceState)
        binding.editTextTextPersonName.setText(savedInstanceState.getString(EDIT_TEXT_KEY))
    }

    companion object {
        fun newInstance(context: Context): Intent = Intent(
            context, SecondActivity::class.java
        )
        private const val EDIT_TEXT_KEY = "key"
    }
}