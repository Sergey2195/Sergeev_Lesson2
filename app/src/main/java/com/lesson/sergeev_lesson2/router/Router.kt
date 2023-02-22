package com.lesson.sergeev_lesson2.router

import androidx.fragment.app.Fragment
import com.lesson.sergeev_lesson2.R
import com.lesson.sergeev_lesson2.activity.MainActivity
import com.lesson.sergeev_lesson2.fragments.*

class Router {
    private var mainActivity: MainActivity? = null

    fun onCreate(activity: MainActivity) {
        mainActivity = activity
    }

    fun openMainScreen() {
        if (mainActivity == null) return
        val fragment = mainActivity!!.supportFragmentManager.findFragmentByTag(MAIN_SCREEN_TAG)
        val mainFragment = fragment ?: MainFragment.newInstance()
        openFragment(mainFragment, false, MAIN_SCREEN_TAG, null)
    }

    fun openVacanciesScreen() {
        openFragment(VacanciesFragment.newInstance(), true, null, VACANCIES_SCREEN_NAME)
    }

    fun openOfficesScreen() {
        openFragment(OfficesFragment.newInstance(), true, null, OFFICES_SCREEN_NAME)
    }


    fun openLoginScreen() {
        openFragment(LoginFragment.newInstance(), false, LOGIN_SCREEN_TAG, null)
    }

    fun openDetailsScreen(officeName: String, officeDescription: String){
        openFragment(
            OfficeDetailsFragment.newInstance(officeName, officeDescription),
            true,
            OFFICE_DETAILS_TAG,
            null)
    }

    private fun openFragment(
        fragment: Fragment,
        addToBackStack: Boolean,
        tag: String? = null,
        backStackName: String?
    ) {
        val transaction = mainActivity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.mainFragmentContainerView, fragment, tag)
        if (addToBackStack) transaction?.addToBackStack(backStackName)
        transaction?.commit()
    }

    fun onDestroy() {
        mainActivity = null
    }

    companion object {
        private const val VACANCIES_SCREEN_NAME = "vacancies screen"
        private const val OFFICES_SCREEN_NAME = "offices screen"
        private const val MAIN_SCREEN_TAG = "mScreen"
        const val LOGIN_SCREEN_TAG = "loginScreen"
        const val OFFICE_DETAILS_TAG = "office details"
    }
}