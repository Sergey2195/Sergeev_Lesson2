package com.lesson.sergeev_lesson2.router

import androidx.fragment.app.Fragment
import com.lesson.sergeev_lesson2.R
import com.lesson.sergeev_lesson2.activity.MainActivity
import com.lesson.sergeev_lesson2.fragments.LoginFragment
import com.lesson.sergeev_lesson2.fragments.MainFragment
import com.lesson.sergeev_lesson2.fragments.OfficesFragment
import com.lesson.sergeev_lesson2.fragments.VacanciesFragment

class Router {
    private var mainActivity: MainActivity? = null

    fun onCreate(activity: MainActivity){
        mainActivity = activity
    }

    fun openMainScreen(){
        mainActivity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.mainFragmentContainerView, MainFragment.newInstance(), MainActivity.MAIN_SCREEN_NAME)
            ?.commit()
    }

    fun openVacanciesScreen(){
        openFragment(VacanciesFragment.newInstance(), true)
    }

    fun openLoginScreen(){
        openFragment(LoginFragment.newInstance(), false)
    }

    fun openOfficesScreen(){
        openFragment(OfficesFragment.newInstance(), true)
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