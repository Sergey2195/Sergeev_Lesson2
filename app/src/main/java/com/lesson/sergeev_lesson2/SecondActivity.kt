package com.lesson.sergeev_lesson2

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lesson.sergeev_lesson2.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.saveBtn.setOnClickListener { sendResult() }
    }

    private fun sendResult() {
        val text = binding.editTextSecond.text.toString()
        val result = Intent().putExtra(STRING_KEY, text)
        if (text.isBlank()){
            setResult(Activity.RESULT_CANCELED, result)
        }else{
            setResult(Activity.RESULT_OK, result)
        }
        finish()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val text = binding.editTextSecond.text.toString()
        outState.putString(EDIT_TEXT_KEY, text)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.editTextSecond.setText(savedInstanceState.getString(EDIT_TEXT_KEY))
    }

    companion object {
        fun newInstance(context: Context): Intent = Intent(
            context, SecondActivity::class.java
        )

        private const val EDIT_TEXT_KEY = "key"
        const val STRING_KEY = "String Key"
    }
}