package com.lesson.sergeev_lesson2

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class SecondActivityContract: ActivityResultContract<Unit, String?>() {
    override fun createIntent(context: Context, input: Unit): Intent {
        return SecondActivity.newInstance(context)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        return when (checkValidResult(resultCode, intent)){
            true -> intent?.getStringExtra(SecondActivity.STRING_KEY)
            false -> null
        }
    }

    private fun checkValidResult(resultCode: Int, intent: Intent?): Boolean{
        return resultCode == Activity.RESULT_OK
                && intent?.getStringExtra(SecondActivity.STRING_KEY) != null
    }
}