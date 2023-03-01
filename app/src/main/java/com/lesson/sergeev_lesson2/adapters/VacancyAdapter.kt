package com.lesson.sergeev_lesson2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.lesson.sergeev_lesson2.R
import com.lesson.sergeev_lesson2.cardCustomView.AstonCardView
import com.lesson.sergeev_lesson2.diffUtils.VacancyDiffUtilsCallback
import com.lesson.sergeev_lesson2.models.VacancyDto
import com.lesson.sergeev_lesson2.viewHolders.VacancyViewHolder

class VacancyAdapter : ListAdapter<VacancyDto, VacancyViewHolder>(VacancyDiffUtilsCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacancyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.vacancy_item, parent, false)
        return VacancyViewHolder(view as AstonCardView)
    }

    override fun onBindViewHolder(holder: VacancyViewHolder, position: Int) {
        val card = holder.itemView as AstonCardView
        val data = currentList[position]
        card.setTitleText(data.vacancyTitle)
        card.setSubtitleText(data.vacancyDescription)
    }
}