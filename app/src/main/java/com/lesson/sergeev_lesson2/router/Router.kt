package com.lesson.sergeev_lesson2.router

import androidx.fragment.app.Fragment
import com.lesson.sergeev_lesson2.R
import com.lesson.sergeev_lesson2.activity.MainActivity
import com.lesson.sergeev_lesson2.fragments.LoginFragment
import com.lesson.sergeev_lesson2.fragments.MainFragment

class Router {
    private var mainActivity: MainActivity? = null

    fun onCreate(activity: MainActivity){
        mainActivity = activity
    }

    fun openMainScreen(){
        openFragment(MainFragment.newInstance(), false)
    }

    fun openLoginScreen(){
        openFragment(LoginFragment.newInstance(), false)
    }

    private fun openFragment(fragment: Fragment, addToBackStack: Boolean){
        val transaction = mainActivity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.mainFragmentContainerView, fragment)
        if (addToBackStack) transaction?.addToBackStack(null)
        transaction?.commit()

    }

    fun onDestroy(){
        mainActivity = null
    }
}