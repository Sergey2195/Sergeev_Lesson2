package com.lesson.sergeev_lesson2.diffUtils

import androidx.recyclerview.widget.DiffUtil
import com.lesson.sergeev_lesson2.models.VacancyDto

class VacancyDiffUtilsCallback: DiffUtil.ItemCallback<VacancyDto>() {
    override fun areItemsTheSame(oldItem: VacancyDto, newItem: VacancyDto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: VacancyDto, newItem: VacancyDto): Boolean {
        return oldItem == newItem
    }
}