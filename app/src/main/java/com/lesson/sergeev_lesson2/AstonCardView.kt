package com.lesson.sergeev_lesson2

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import androidx.appcompat.content.res.AppCompatResources
import androidx.cardview.widget.CardView
import com.lesson.sergeev_lesson2.databinding.AstonCardViewBinding

class AstonCardView(
    context: Context,
    attrSet: AttributeSet?,
    defStyleAttr: Int) : CardView(context, attrSet, defStyleAttr) {
    constructor(context: Context): this(context, null)
    constructor(context: Context, attrSet: AttributeSet?): this(context,attrSet, 0)
    private var binding: AstonCardViewBinding
    private var data: AstonCardData

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.aston_card_view, this, true)
        binding = AstonCardViewBinding.bind(this)
        data = AstonCardData()
        initAttrs(attrSet,defStyleAttr)
        initView()
    }

    private fun initView() {
        with(binding){
            astonLogo.setImageDrawable(data.imageResource)
            titleTv.text = data.titleText
            subtitleTv.text = data.subtitleText
        }
    }

    private fun initAttrs(attrSet: AttributeSet?, defStyleAttr: Int) {
        if (attrSet == null) return
        val typedArray = context.obtainStyledAttributes(attrSet, R.styleable.AstonCardView, defStyleAttr,0)
        data.titleText = typedArray.getString(R.styleable.AstonCardView_title_text) ?: ""
        data.subtitleText = typedArray.getString(R.styleable.AstonCardView_subtitle_text) ?: ""
        val logoRes = typedArray.getResourceId(R.styleable.AstonCardView_logo_image_res, 0)
        data.imageResource = if (logoRes == 0){
            null
        }else{
            AppCompatResources.getDrawable(context, logoRes)
        }
        typedArray.recycle()
    }

    fun setTitleText(text: String){
        data.titleText = text
        binding.titleTv.text = text
    }

    fun setSubtitleText(text: String){
        data.subtitleText = text
        binding.subtitleTv.text = text
    }

    fun setLogoImageResource(drawable: Drawable?){
        data.imageResource = drawable
        binding.astonLogo.setImageDrawable(drawable)
    }

}