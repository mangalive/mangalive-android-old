package com.acg.mangalive

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View

/**
 * TODO: document your custom view class.
 */
class SearchView : View {

    private var _surfaceColor = R.color.panel
    private var _placeholderColor = R.color.white
    private var _placeholder: String? = null
    private var _placeholderDimension = 0f

    private var placeholderWidth = 0f
    private var placeholderHeight = 0f

    private lateinit var placeholderPaint: TextPaint
    private lateinit var surfacePaint: Paint
    private var drawable: Drawable? = null

    var surfaceCornerRadius = 0f
    var iconColor = R.color.primary

    var color: Int
        get() = _surfaceColor
        set(value) {
            _surfaceColor = value
            invalidateSurfacePaint()
        }

    var placeholderColor: Int
        get() = _placeholderColor
        set(value) {
            _placeholderColor = value
            invalidatePlaceholderTextPaintAndMeasurements()
        }

    var placeholder: String?
        get() = _placeholder
        set(value) {
            _placeholder = value
            invalidatePlaceholderTextPaintAndMeasurements()
        }

    var placeholderDimension: Float
        get() = _placeholderDimension
        set(value) {
            _placeholderDimension = value
            invalidatePlaceholderTextPaintAndMeasurements()
        }

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        val a = context.obtainStyledAttributes(
            attrs, R.styleable.SearchView, defStyle, 0
        )

        _placeholder = a.getString(
            R.styleable.SearchView_placeholder,
        )

        _placeholderColor = a.getColor(
            R.styleable.SearchView_placeholderColor,
            placeholderColor
        )

        _placeholderDimension = a.getDimension(
            R.styleable.SearchView_placeholderDimension,
            placeholderDimension
        )

        _surfaceColor = a.getColor(
            R.styleable.SearchView_surfaceColor,
            color
        )

        surfaceCornerRadius = a.getFloat(
            R.styleable.SearchView_surfaceCornerRadius,
            surfaceCornerRadius
        )

        iconColor = a.getColor(R.styleable.SearchView_iconColor, iconColor)

        if (a.hasValue(R.styleable.SearchView_icon)) {
            drawable = a.getDrawable(R.styleable.SearchView_icon)
            drawable?.callback = this
        }

        a.recycle()

        placeholderPaint = TextPaint().apply {
            flags = Paint.ANTI_ALIAS_FLAG
            textAlign = Paint.Align.LEFT
        }

        surfacePaint = Paint().apply {
            flags = Paint.ANTI_ALIAS_FLAG
            style = Paint.Style.FILL
        }

        invalidatePlaceholderTextPaintAndMeasurements()
        invalidateSurfacePaint()
    }

    private fun invalidatePlaceholderTextPaintAndMeasurements() {
        placeholderPaint.let {
            it.textSize = placeholderDimension
            it.color = placeholderColor
            placeholderWidth = it.measureText(placeholder)
            placeholderHeight = it.fontMetrics.bottom
        }
    }

    private fun invalidateSurfacePaint() {
        surfacePaint.color = color
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val paddingLeft = paddingLeft
        val paddingTop = paddingTop
        val paddingRight = paddingRight
        val paddingBottom = paddingBottom

        val contentWidth = width - paddingLeft - paddingRight
        val contentHeight = height - paddingTop - paddingBottom

        val surfaceRect = RectF().apply {
            left = paddingLeft.toFloat()
            right = (paddingLeft + contentWidth).toFloat()
            top = paddingTop.toFloat()
            bottom = (paddingTop + contentHeight).toFloat()
        }

        val drawableLeft = paddingLeft + (surfaceCornerRadius / 2).toInt()
        val measureNum = placeholderPaint.breakText(
            placeholder,
            true,
            (paddingLeft + contentWidth).toFloat() - surfaceCornerRadius - (drawableLeft.toFloat() + placeholderDimension / 2 + (drawable?.intrinsicWidth?.toFloat()
                ?: 0f)),
            null
        )

        var pl = (placeholder?.substring(0, measureNum - 1) ?: "")

        if (measureNum < (placeholder?.length ?: 0)) {
            if (pl.last() == ',' || pl.last() == '.') {
                pl = pl.substring(0, pl.length - 1)
            }
            pl += "..."
        }

        canvas.drawRoundRect(
            surfaceRect,
            surfaceCornerRadius,
            surfaceCornerRadius,
            surfacePaint
        )

        drawable?.let {
            it.setBounds(
                drawableLeft, paddingTop + (contentHeight - it.intrinsicHeight) / 2, drawableLeft
                        + it.intrinsicWidth, paddingTop + (contentHeight + it.intrinsicHeight) / 2
            )
            it.setTint(iconColor)
            it.draw(canvas)
        }

        placeholder?.let {
            canvas.drawText(
                pl,
                drawableLeft.toFloat() + placeholderDimension / 2 + (drawable?.intrinsicWidth?.toFloat()
                    ?: 0f),
                paddingTop + (contentHeight + placeholderHeight * 2) / 2,
                placeholderPaint
            )
        }

    }
}