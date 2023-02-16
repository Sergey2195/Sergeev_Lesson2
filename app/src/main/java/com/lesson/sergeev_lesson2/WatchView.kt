package com.lesson.sergeev_lesson2

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.View
import kotlin.math.cos
import kotlin.math.sin


class WatchView(
    context: Context,
    attrSet: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : View(
    context,
    attrSet,
    defStyleAttr,
    defStyleRes
) {
    constructor(context: Context, attrSet: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attrSet,
        defStyleAttr,
        0
    )

    constructor(context: Context, attrSet: AttributeSet?) : this(context, attrSet, 0)
    constructor(context: Context) : this(context, null)

    var currentTime = WatchTime(0, 0, 0)
    private lateinit var circlePaint: Paint
    private lateinit var notchesPaint: Paint
    private val externalNotches: Array<PointF> = Array(12){ PointF(0f,0f) }
    private val internalNotches: Array<PointF> = Array(12){ PointF(0f,0f) }
    private var center: PointF = PointF(0f, 0f)
    private var radius: Float = 0f

    init {
        initPaints()
    }

    private fun initPaints() {
        initCirclePaint()
        initNotchesPaint()
    }

    private fun initNotchesPaint() {
        notchesPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        notchesPaint.style = Paint.Style.STROKE
        notchesPaint.strokeWidth = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            10f,
            resources.displayMetrics
        )
        notchesPaint.color = Color.BLACK
    }

    private fun initCirclePaint() {
        circlePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        circlePaint.style = Paint.Style.STROKE
        circlePaint.strokeWidth = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            8f,
            resources.displayMetrics
        )
        circlePaint.color = Color.BLACK
    }

    fun setNewTime(watchTime: WatchTime) {
        currentTime = watchTime
        invalidate()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        updateCenterAndRadius()
        updateExternalNotchesArray()
        updateInternalNotchesArray()
    }

    private fun updateInternalNotchesArray(){
        for (i in 0 until 12){
            val angle = i * (360/12)
            val x = center.x + (radius*0.85) * cos(Math.toRadians(angle.toDouble()))
            val y = center.y + (radius*0.85) * sin(Math.toRadians(angle.toDouble()))
            internalNotches[i].x = x.toFloat()
            internalNotches[i].y = y.toFloat()
        }
    }

    private fun updateExternalNotchesArray(){
        for (i in 0 until 12){
            val angle = i * (360/12)
            val x = center.x + radius * cos(Math.toRadians(angle.toDouble()))
            val y = center.y + radius * sin(Math.toRadians(angle.toDouble()))
            externalNotches[i].x = x.toFloat()
            externalNotches[i].y = y.toFloat()
        }
    }

    private fun updateCenterAndRadius(){
        center.x = width.toFloat()/2
        center.y = height.toFloat()/2
        radius = width.toFloat() / 3
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        Log.d("TAG123", "$width $height")
        drawCircle(canvas)
    }

    private fun drawCircle(canvas: Canvas) {
        canvas.drawCircle(
            center.x, center.y, radius, circlePaint
        )
        for (i in 0 until 12){
            canvas.drawLine(externalNotches[i].x, externalNotches[i].y, internalNotches[i].x, internalNotches[i].y, notchesPaint)
        }
    }
}