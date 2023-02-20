package com.lesson.sergeev_lesson2

import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private var router: Router? = null

    init {
        router = Router()
    }

    fun attachRouter(mainActivity: MainActivity){
        router?.onCreate(mainActivity)
    }

    fun detachRouter(){
        router?.onDestroy()
    }

    fun openMainScreen(){
        router?.openMainScreen()
    }
}