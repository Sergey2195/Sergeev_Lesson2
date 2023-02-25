package com.lesson.sergeev_lesson2.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lesson.sergeev_lesson2.databinding.ByOfficeItemBinding
import com.lesson.sergeev_lesson2.databinding.RuOfficeItemBinding
import com.lesson.sergeev_lesson2.models.OfficeDto

abstract class OfficeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun populate(officeDto: OfficeDto, listener: ((OfficeDto) -> Unit)?)
}

class RuOfficeViewHolder(private val binding: RuOfficeItemBinding) : OfficeViewHolder(binding.root) {

    override fun populate(officeDto: OfficeDto, listener: ((OfficeDto) -> Unit)?) {
        binding.officeTitle.text = officeDto.officeName
        binding.officeCardView.setOnClickListener {
            listener?.invoke(officeDto)
        }
    }
}

class ByOfficeViewHolder(private val binding: ByOfficeItemBinding) : OfficeViewHolder(binding.root) {

    override fun populate(officeDto: OfficeDto, listener: ((OfficeDto) -> Unit)?) {
        binding.officeTitle.text = officeDto.officeName
        binding.officeCardView.setOnClickListener {
            listener?.invoke(officeDto)
        }
    }
}