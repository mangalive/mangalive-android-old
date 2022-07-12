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
class SearchView(context: Context, attrs: AttributeSet?, defStyle: Int) :
    View(context, attrs, defStyle) {
    private var _surfaceColor = 0
    private var _placeholderColor = 0
    private var _placeholderText: String? = null
    private var _placeholderTextSize = 0f

    private var placeholderWidth = 0f
    private var placeholderHeight = 0f

    private var placeholderPaint: TextPaint? = null
    private var surfacePaint: Paint? = null
    private var iconDrawable: Drawable? = null

    var surfaceCornerRadius = 0f
    var iconColor = 0

    var surfaceColor: Int
        get() = _surfaceColor
        set(value) {
            _surfaceColor = value
            updateSurfacePaint()
        }

    var placeholderColor: Int
        get() = _placeholderColor
        set(value) {
            _placeholderColor = value
            updatePlaceholderPaintAndMeasurements()
        }

    var placeholderText: String?
        get() = _placeholderText
        set(value) {
            _placeholderText = value
            updateSurfacePaint()
        }

    var placeholderTextSize: Float
        get() = _placeholderTextSize
        set(value) {
            _placeholderTextSize = value
            updateSurfacePaint()
        }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(
        context,
        attrs,
        R.style.Widget_Theme_Mangalive_SearchView
    )

    init {
        val a = context.obtainStyledAttributes(
            attrs, R.styleable.SearchView, defStyle, 0
        )

        _placeholderText = a.getString(
            R.styleable.SearchView_placeholderText
        )

        _placeholderColor = a.getColor(
            R.styleable.SearchView_placeholderColor,
            placeholderColor
        )

        _placeholderTextSize = a.getDimension(
            R.styleable.SearchView_placeholderTextSize,
            _placeholderTextSize
        )

        _surfaceColor = a.getColor(
            R.styleable.SearchView_surfaceColor,
            surfaceColor
        )

        surfaceCornerRadius = a.getFloat(
            R.styleable.SearchView_surfaceCornerRadius,
            surfaceCornerRadius
        )

        iconColor = a.getColor(R.styleable.SearchView_iconColor, iconColor)

        iconDrawable = a.getDrawable(R.styleable.SearchView_icon)

        a.recycle()

        placeholderPaint = TextPaint().apply {
            flags = Paint.ANTI_ALIAS_FLAG
            textAlign = Paint.Align.LEFT
        }

        surfacePaint = Paint().apply {
            flags = Paint.ANTI_ALIAS_FLAG
            style = Paint.Style.FILL
        }

        updatePlaceholderPaintAndMeasurements()
        updateSurfacePaint()
    }

    private fun updatePlaceholderPaintAndMeasurements() {
        placeholderPaint?.let {
            it.textSize = placeholderTextSize
            it.color = placeholderColor
            placeholderWidth = it.measureText(placeholderText)
            placeholderHeight = it.fontMetrics.bottom
        }
    }

    private fun updateSurfacePaint() {
        surfacePaint?.color = surfaceColor
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val contentWidth = width - paddingLeft - paddingRight
        val contentHeight = height - paddingTop - paddingBottom

        val surfaceRect = RectF().apply {
            left = paddingLeft.toFloat()
            right = (paddingLeft + contentWidth).toFloat()
            top = paddingTop.toFloat()
            bottom = (paddingTop + contentHeight).toFloat()
        }

        val iconDrawableLeft = paddingLeft + (surfaceCornerRadius / 2).toInt()

        val measureNum = placeholderPaint?.breakText(
            placeholderText,
            true,
            (paddingLeft + contentWidth).toFloat() - surfaceCornerRadius - (iconDrawableLeft.toFloat() + (iconDrawable?.intrinsicWidth
                ?: 0).toFloat() + placeholderTextSize / 2),
            null
        ) ?: 0

        val placeholderTextGutted =  if (measureNum < (placeholderText?.length ?: 0)) {
            placeholderText?.substring(0, measureNum - 1) + "..."
        } else {
            placeholderText?.substring(0, measureNum) + ""
        }

        surfacePaint?.let {
            canvas.drawRoundRect(
                surfaceRect,
                surfaceCornerRadius,
                surfaceCornerRadius,
                it
            )
        }

        iconDrawable?.let {
            it.setBounds(
                iconDrawableLeft,
                paddingTop + (contentHeight - it.intrinsicHeight) / 2,
                iconDrawableLeft
                        + it.intrinsicWidth,
                paddingTop + (contentHeight + it.intrinsicHeight) / 2
            )
            it.setTint(iconColor)
            it.draw(canvas)
        }

        placeholderPaint?.let {
            canvas.drawText(
                placeholderTextGutted,
                iconDrawableLeft.toFloat() + (iconDrawable?.intrinsicWidth
                    ?: 0).toFloat() + placeholderTextSize / 2,
                paddingTop + (contentHeight + placeholderHeight * 2) / 2,
                it
            )
        }
    }
}