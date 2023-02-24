package com.lesson.sergeev_lesson2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lesson.sergeev_lesson2.R
import com.lesson.sergeev_lesson2.databinding.ByOfficeItemBinding
import com.lesson.sergeev_lesson2.databinding.RuOfficeItemBinding
import com.lesson.sergeev_lesson2.models.OfficeDto
import com.lesson.sergeev_lesson2.viewHolders.ByOfficeViewHolder
import com.lesson.sergeev_lesson2.viewHolders.OfficeViewHolder
import com.lesson.sergeev_lesson2.viewHolders.RuOfficeViewHolder

class OfficeAdapter : RecyclerView.Adapter<OfficeViewHolder>() {

    private var listOffices = emptyList<OfficeDto>()
    var clickListener: ((OfficeDto)->Unit)? = null

    fun submitList(list: List<OfficeDto>) {
        listOffices = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfficeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return if (viewType == R.layout.ru_office_item) {
            val binding = RuOfficeItemBinding.inflate(layoutInflater, parent, false)
            RuOfficeViewHolder(binding)
        } else {
            val binding = ByOfficeItemBinding.inflate(layoutInflater, parent, false)
            ByOfficeViewHolder(binding)
        }
    }

    override fun getItemCount() = listOffices.size


    override fun onBindViewHolder(holder: OfficeViewHolder, position: Int) {
        holder.populate(listOffices[position], clickListener)
    }

    override fun getItemViewType(position: Int): Int {
        return listOffices[position].type
    }
}