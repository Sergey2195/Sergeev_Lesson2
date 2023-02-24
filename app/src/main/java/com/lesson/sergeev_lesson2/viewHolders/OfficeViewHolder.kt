package com.lesson.sergeev_lesson2.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lesson.sergeev_lesson2.databinding.ByOfficeBinding
import com.lesson.sergeev_lesson2.databinding.RuOfficeBinding
import com.lesson.sergeev_lesson2.models.OfficeDto

abstract class OfficeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun populate(officeDto: OfficeDto, listener: ((OfficeDto)->Unit)?)
}

class RuOfficeViewHolder(private val binding: RuOfficeBinding) : OfficeViewHolder(binding.root) {

    override fun populate(officeDto: OfficeDto, listener: ((OfficeDto)->Unit)?) {
        binding.officeTitle.text = officeDto.officeName
        binding.officeCardView.setOnClickListener{
            listener?.invoke(officeDto)
        }
    }
}

class ByOfficeViewHolder(private val binding: ByOfficeBinding) : OfficeViewHolder(binding.root) {

    override fun populate(officeDto: OfficeDto, listener: ((OfficeDto)->Unit)?) {
        binding.officeTitle.text = officeDto.officeName
        binding.officeCardView.setOnClickListener{
            listener?.invoke(officeDto)
        }
    }
}