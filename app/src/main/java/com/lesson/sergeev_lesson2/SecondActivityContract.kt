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
        return if (resultCode != Activity.RESULT_OK
            || intent?.getStringExtra(SecondActivity.STRING_KEY) == null
            || (intent.getStringExtra(SecondActivity.STRING_KEY) ?: "") == ""
        ){
            null
        }else{
            intent.getStringExtra(SecondActivity.STRING_KEY)
        }
    }
}