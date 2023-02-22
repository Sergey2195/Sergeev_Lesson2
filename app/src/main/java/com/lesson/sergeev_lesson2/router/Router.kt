package com.lesson.sergeev_lesson2.router

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.lesson.sergeev_lesson2.R
import com.lesson.sergeev_lesson2.activity.MainActivity
import com.lesson.sergeev_lesson2.fragments.LoginFragment
import com.lesson.sergeev_lesson2.fragments.MainFragment
import com.lesson.sergeev_lesson2.fragments.OfficesFragment
import com.lesson.sergeev_lesson2.fragments.VacanciesFragment

class Router {
    private var mainActivity: MainActivity? = null

    fun onCreate(activity: MainActivity) {
        mainActivity = activity
    }

    fun openMainScreen() {
        if (mainActivity == null) return
        val fragment = mainActivity!!.supportFragmentManager.findFragmentByTag("mScreen")
        if (fragment == null) {
            mainActivity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.mainFragmentContainerView, MainFragment.newInstance(), "mScreen")
                .commit()
        } else {
            mainActivity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.mainFragmentContainerView, fragment, "mScreen")
                .commit()
        }
    }

    fun openVacanciesScreen() {
        if (mainActivity == null) return
        mainActivity!!.supportFragmentManager.beginTransaction()
            .replace(R.id.mainFragmentContainerView, VacanciesFragment.newInstance())
            .addToBackStack(VACANCIES_SCREEN_NAME)
            .commit()
    }

    fun openOfficesScreen() {
        if (mainActivity == null) return
        mainActivity!!.supportFragmentManager.beginTransaction()
            .replace(R.id.mainFragmentContainerView, OfficesFragment.newInstance())
            .addToBackStack(OFFICES_SCREEN_NAME)
            .commit()
    }


    fun openLoginScreen() {
        openFragment(LoginFragment.newInstance(), false, "loginScreen")
    }

    private fun openFragment(fragment: Fragment, addToBackStack: Boolean, tag: String? = null) {
        val transaction = mainActivity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.mainFragmentContainerView, fragment, tag)
        if (addToBackStack) transaction?.addToBackStack(null)
        transaction?.commit()
    }

    fun onDestroy() {
        mainActivity = null
    }

    companion object {
        private const val MAIN_SCREEN_NAME = "main screen"
        private const val VACANCIES_SCREEN_NAME = "vacancies screen"
        private const val OFFICES_SCREEN_NAME = "offices screen"
    }
}