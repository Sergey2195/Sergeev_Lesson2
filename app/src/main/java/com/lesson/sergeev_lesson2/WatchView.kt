package com.lesson.sergeev_lesson2

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import kotlin.math.cos
import kotlin.math.sin
import kotlin.properties.Delegates


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
        R.style.WatchViewDefaultStyle
    )

    constructor(context: Context, attrSet: AttributeSet?) : this(
        context,
        attrSet,
        R.style.WatchViewDefaultStyle
    )

    constructor(context: Context) : this(context, null)

    private var currentTime = WatchTime(0, 0, 0)
    private lateinit var circlePaint: Paint
    private lateinit var notchesPaint: Paint
    private val externalNotches: Array<PointF> = Array(12) { PointF(0f, 0f) }
    private val internalNotches: Array<PointF> = Array(12) { PointF(0f, 0f) }
    private lateinit var hoursPaint: Paint
    private lateinit var minutesPaint: Paint
    private lateinit var secondsPaint: Paint
    private lateinit var shadowPaint: Paint
    private lateinit var shader: LinearGradient
    private var customBlack = Color.rgb(128, 128, 128)
    private var center: PointF = PointF(0f, 0f)
    private var radius: Float = 0f
    private var hourColor by Delegates.notNull<Int>()
    private var minutesColor by Delegates.notNull<Int>()
    private var secondsColor by Delegates.notNull<Int>()
    private var hourWidth by Delegates.notNull<Float>()
    private var minutesWidth by Delegates.notNull<Float>()
    private var secondsWidth by Delegates.notNull<Float>()
    private var hoursRatio by Delegates.notNull<Float>()
    private var minutesRatio by Delegates.notNull<Float>()
    private var secondsRatio by Delegates.notNull<Float>()

    init {
        parsingAttrs(attrSet, defStyleAttr, defStyleRes)
        initPaints()
    }

    private fun parsingAttrs(attrSet: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        if (attrSet == null) return
        val typedArray = context.obtainStyledAttributes(
            attrSet,
            R.styleable.WatchView,
            defStyleAttr,
            defStyleRes
        )
        hourColor = typedArray.getColor(R.styleable.WatchView_hour_color, Color.BLACK)
        minutesColor = typedArray.getColor(R.styleable.WatchView_minutes_color, Color.BLACK)
        secondsColor = typedArray.getColor(R.styleable.WatchView_seconds_color, Color.BLACK)
        hourWidth = typedArray.getFloat(R.styleable.WatchView_hour_width, 8f)
        minutesWidth = typedArray.getFloat(R.styleable.WatchView_minutes_width, 6f)
        secondsWidth = typedArray.getFloat(R.styleable.WatchView_seconds_width, 3f)
        hoursRatio = typedArray.getFloat(R.styleable.WatchView_hour_ratio, 0.4f)
        secondsRatio = typedArray.getFloat(R.styleable.WatchView_seconds_ratio, 0.8f)
        minutesRatio = typedArray.getFloat(R.styleable.WatchView_minutes_ratio, 0.6f)
        typedArray.recycle()
    }

    private fun initPaints() {
        initCirclePaint()
        initNotchesPaint()
        initArrowsPaint()
        initShadow()
    }

    private fun initShadow() {
        shadowPaint = Paint()
    }

    private fun initArrowsPaint() {
        hoursPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        hoursPaint.style = Paint.Style.STROKE
        hoursPaint.strokeWidth = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            hourWidth,
            resources.displayMetrics
        )
        hoursPaint.color = hourColor

        minutesPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        minutesPaint.style = Paint.Style.STROKE
        minutesPaint.strokeWidth = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            minutesWidth,
            resources.displayMetrics
        )
        minutesPaint.color = minutesColor

        secondsPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        secondsPaint.style = Paint.Style.STROKE
        secondsPaint.strokeWidth = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            secondsWidth,
            resources.displayMetrics
        )
        secondsPaint.color = secondsColor
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

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        updateCenterAndRadius()
        updateExternalNotchesArray()
        updateInternalNotchesArray()
        shader = LinearGradient(
            width.toFloat() / 2,
            height.toFloat(),
            width.toFloat() / 2,
            height * 0.4f,
            customBlack,
            Color.WHITE,
            Shader.TileMode.CLAMP
        )
        shadowPaint.shader = shader
    }

    private fun updateInternalNotchesArray() {
        for (i in 0 until 12) {
            val angle = i * (360 / 12)
            val x = center.x + radius * 0.85 * cos(Math.toRadians(angle.toDouble()))
            val y = center.y + radius * 0.85 * sin(Math.toRadians(angle.toDouble()))
            internalNotches[i].x = x.toFloat()
            internalNotches[i].y = y.toFloat()
        }
    }

    private fun drawArrow(
        angle: Double,
        canvas: Canvas,
        paint: Paint,
        ratioBig: Double,
        ratioSmall: Double
    ) {
        val pairBig = calcXY(angle, ratioBig)
        val pairSmall = calcXY(angle + 180, ratioSmall)
        canvas.drawLine(center.x, center.y, pairBig.first, pairBig.second, paint)
        canvas.drawLine(center.x, center.y, pairSmall.first, pairSmall.second, paint)
    }

    private fun calcXY(angle: Double, radiusC: Double): Pair<Float, Float> {
        val trueAngle = angle - 90
        val x = (center.x + radius * radiusC * cos(Math.toRadians(trueAngle))).toFloat()
        val y = (center.y + radius * radiusC * sin(Math.toRadians(trueAngle))).toFloat()
        return Pair(x, y)
    }

    private fun updateExternalNotchesArray() {
        for (i in 0 until 12) {
            val angle = i * (360 / 12)
            val x = center.x + radius * cos(Math.toRadians(angle.toDouble()))
            val y = center.y + radius * sin(Math.toRadians(angle.toDouble()))
            externalNotches[i].x = x.toFloat()
            externalNotches[i].y = y.toFloat()
        }
    }

    private fun updateCenterAndRadius() {
        center.x = width.toFloat() / 2
        center.y = height.toFloat() / 2
        radius = width.toFloat() / 3
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(0f, height.toFloat() * 0.4f, width.toFloat(), height.toFloat(), shadowPaint)
        drawCircle(canvas)
        drawArrows(canvas)
    }

    private fun drawArrows(canvas: Canvas) {
        val hourAngle = calcHourAngle(currentTime.hour, currentTime.minutes)
        val minutesAngle = calcMinutesAngle(currentTime.minutes, currentTime.seconds)
        val secondsAngle = calcSecondsAngle(currentTime.seconds)
        drawArrow(secondsAngle, canvas, secondsPaint, secondsRatio.toDouble(), 0.4)
        drawArrow(minutesAngle, canvas, minutesPaint, minutesRatio.toDouble(), 0.2)
        drawArrow(hourAngle, canvas, hoursPaint, hoursRatio.toDouble(), 0.15)
    }

    private fun calcHourAngle(hour: Int, minutes: Int): Double {
        val num = hour * 60 + minutes
        return 0.5 * num
    }

    private fun calcMinutesAngle(minutes: Int, seconds: Int): Double {
        return 0.1 * (minutes * 60 + seconds)
    }

    private fun calcSecondsAngle(seconds: Int): Double {
        return (6 * seconds).toDouble()
    }

    private fun drawCircle(canvas: Canvas) {
        canvas.drawCircle(
            center.x, center.y, radius, circlePaint
        )
        for (i in 0 until 12) {
            canvas.drawLine(
                externalNotches[i].x,
                externalNotches[i].y,
                internalNotches[i].x,
                internalNotches[i].y,
                notchesPaint
            )
        }
    }

    fun updateHourColor(color: Int) {
        hoursPaint.color = color
    }

    fun updateMinutesColor(color: Int) {
        minutesPaint.color = color
    }

    fun updateSecondsColor(color: Int) {
        secondsPaint.color = color
    }

    fun updateHoursWidth(width: Float) {
        hoursPaint.strokeWidth = updateWidth(width)
    }

    fun updateMinutesWidth(width: Float) {
        minutesPaint.strokeWidth = updateWidth(width)
    }

    fun updateSecondsWidth(width: Float) {
        secondsPaint.strokeWidth = updateWidth(width)
    }

    private fun updateWidth(width: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            width,
            resources.displayMetrics
        )
    }

    fun updateHoursRatio(ratio: Float) {
        hoursRatio = ratio
    }

    fun updateMinutesRatio(ratio: Float) {
        minutesRatio = ratio
    }

    fun updateSecondsRatio(ratio: Float) {
        secondsRatio = ratio
    }
}