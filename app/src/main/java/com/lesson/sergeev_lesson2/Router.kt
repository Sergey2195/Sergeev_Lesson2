package com.lesson.sergeev_lesson2

import androidx.fragment.app.Fragment

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