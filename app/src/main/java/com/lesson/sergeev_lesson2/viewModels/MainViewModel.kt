package com.lesson.sergeev_lesson2.viewModels

import androidx.lifecycle.ViewModel
import com.lesson.sergeev_lesson2.activity.MainActivity
import com.lesson.sergeev_lesson2.models.OfficeDto
import com.lesson.sergeev_lesson2.router.Router

class MainViewModel : ViewModel() {

    private var router: Router? = null

    init {
        router = Router()
    }

    fun attachRouter(mainActivity: MainActivity) {
        router?.onCreate(mainActivity)
    }

    fun detachRouter() {
        router?.onDestroy()
    }

    fun openMainScreen() {
        router?.openMainScreen()
    }

    fun openLoginScreen() {
        router?.openLoginScreen()
    }

    fun openVacanciesScreen() {
        router?.openVacanciesScreen()
    }

    fun openDetailsScreen(officeDto: OfficeDto) {
        router?.openDetailsScreen(officeDto.officeName, officeDto.officeDetails)
    }

    fun openOfficesScreen() {
        router?.openOfficesScreen()
    }

}