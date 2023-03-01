package com.lesson.sergeev_lesson2.viewModels

import androidx.lifecycle.ViewModel
import com.lesson.sergeev_lesson2.models.VacancyDto
import com.lesson.sergeev_lesson2.utils.HardcodeData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class VacanciesViewModel : ViewModel() {
    private val _dataStateFlow = MutableStateFlow(HardcodeData.getHardcodedVacancies())
    val dataStateFlow: StateFlow<List<VacancyDto>>
        get() = _dataStateFlow.asStateFlow()

    fun textFilterChanged(filter: String) {
        val filtered = HardcodeData.getHardcodedVacancies().filter {
            it.vacancyTitle.lowercase().contains(filter.lowercase()) ||
                    it.vacancyDescription.lowercase().contains(filter.lowercase())
        }
        _dataStateFlow.value = filtered
    }
}