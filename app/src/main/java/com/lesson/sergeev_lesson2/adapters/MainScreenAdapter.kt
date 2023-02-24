package com.lesson.sergeev_lesson2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lesson.sergeev_lesson2.R
import com.lesson.sergeev_lesson2.models.MainScreenDto
import com.lesson.sergeev_lesson2.viewHolders.CardViewHolder
import com.lesson.sergeev_lesson2.viewHolders.ImageViewHolder
import com.lesson.sergeev_lesson2.viewHolders.MainScreenViewHolder
import com.lesson.sergeev_lesson2.viewHolders.TextViewHolder

class MainScreenAdapter : RecyclerView.Adapter<MainScreenViewHolder>() {
    private var listData: List<MainScreenDto> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainScreenViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.main_screen_image -> {
                val view = inflater.inflate(R.layout.main_screen_image, parent, false)
                ImageViewHolder(view)
            }
            R.layout.aston_card_view -> {
                val view = inflater.inflate(R.layout.vacancy_item, parent, false)
                CardViewHolder(view)
            }
            R.layout.main_screen_text -> {
                val view = inflater.inflate(R.layout.main_screen_text, parent, false)
                TextViewHolder(view)
            }
            else -> throw java.lang.RuntimeException("onCreateViewHolder unknown viewType")
        }
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: MainScreenViewHolder, position: Int) {
        if (holder.itemViewType == R.layout.main_screen_text) {
            val textView = holder.itemView.findViewById<TextView>(R.id.mainScreenTextItem)
            textView.text = listData[position].content
            val style =
                if (listData[position].contentType == 0) R.style.TitleStyle else R.style.SubtitleStyle
            textView.setTextAppearance(style)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return listData[position].viewType
    }

    fun submitList(list: List<MainScreenDto>) {
        listData = list
        notifyDataSetChanged()
    }
}