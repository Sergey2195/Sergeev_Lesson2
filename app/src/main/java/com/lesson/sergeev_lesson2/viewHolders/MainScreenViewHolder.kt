package com.lesson.sergeev_lesson2.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder

abstract class MainScreenViewHolder(view: View): ViewHolder(view) {
}

class ImageViewHolder(view: View): MainScreenViewHolder(view)
class CardViewHolder(view: View): MainScreenViewHolder(view)
class TextViewHolder(view: View): MainScreenViewHolder(view)