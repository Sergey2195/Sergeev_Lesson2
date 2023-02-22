package com.lesson.sergeev_lesson2.viewModels

import androidx.lifecycle.ViewModel
import com.lesson.sergeev_lesson2.activity.MainActivity
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

    fun openDetailsScreen(officeName: String) {
        router?.openDetailsScreen(officeName, getHardcodedDescription(officeName))
    }

    private fun getHardcodedDescription(officeName: String): String {
        return when (officeName) {
            "Moscow" -> "Presnenskaya naberezhnaya, d.6, str. 2, BTS «Bashnya Imperiya», 3 podyezd, 53 etazh, ofis 13"
            "Москва" -> "Пресненская набережная, д.6, стр. 2, БЦ «Башня Империя», 3 подъезд, 53 этаж, офис 13"
            "Kazan" -> "st. N. Ershova, 76/1, office. 213"
            "Казань" -> "ул. Н. Ершова, 76/1, оф. 213"
            "Ростов-На-Дону" -> "ул. Текучева, 246, 3 этаж"
            "Rostov-On-Don" -> "st. Tekucheva, 246, 3rd floor"
            "Minsk" -> "Pobediteley Ave, 7A"
            "Минск" -> "пр-т Победителей, 7А"
            "Gomel" -> "st. Sovetskya, 41B"
            "Гомель" -> "ул. Советская, 41Б"
            else -> ""
        }
    }

    fun openOfficesScreen() {
        router?.openOfficesScreen()
    }

}