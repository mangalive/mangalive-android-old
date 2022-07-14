package com.acg.mangalive

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.*
import android.graphics.drawable.Drawable
import android.graphics.drawable.PaintDrawable
import android.graphics.drawable.RippleDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.res.getStringOrThrow

/**
 * TODO: document your custom view class.
 */
class SearchView(context: Context, attrs: AttributeSet?, defStyle: Int) :
    View(context, attrs, defStyle, R.style.Widget_Theme_Mangalive_SearchView) {

    private var placeholderWidth = 0f
    private var placeholderHeight = 0f

    private var placeholderPaint: TextPaint? = null
    private var surfacePaint: Paint? = null
    private var iconDrawable: Drawable? = null

    private var _rippleMask: ShapeDrawable? = null

    var surfaceCornerRadius = 0f
    var iconColor = 0

    private var _rippleColor: Int = 0
    var rippleColor: Int
        get() = _rippleColor
        set(value) {
            _rippleColor = value
            updateRippleEffect()
        }

    private var _rippleAlpha: Int = 0
    var rippleAlpha: Int
        get() = _rippleAlpha
        set(value) {
            _rippleAlpha = value
            updateRippleEffect()
        }

    private var _surfaceColor = 0
    var surfaceColor: Int
        get() = _surfaceColor
        set(value) {
            _surfaceColor = value
            updateSurfacePaint()
        }

    private var _placeholderColor = 0
    var placeholderColor: Int
        get() = _placeholderColor
        set(value) {
            _placeholderColor = value
            updatePlaceholderPaint()
        }

    private var _placeholderText: String? = null
    var placeholderText: String?
        get() = _placeholderText
        set(value) {
            _placeholderText = value
            updatePlaceholderPaint()
        }

    private var _placeholderDimension = 0f
    var placeholderDimension: Float
        get() = _placeholderDimension
        set(value) {
            _placeholderDimension = value
            updatePlaceholderPaint()
        }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(
        context,
        attrs,
        R.attr.searchViewStyle
    )

    init {
        initAttributes(context, attrs, defStyle)

        this.isClickable = true

        placeholderPaint = TextPaint().apply {
            flags = Paint.ANTI_ALIAS_FLAG
            textAlign = Paint.Align.LEFT
        }

        surfacePaint = Paint().apply {
            flags = Paint.ANTI_ALIAS_FLAG
            style = Paint.Style.FILL
        }

        updateRippleEffect()
        updatePlaceholderPaint()
        updateSurfacePaint()
    }

    private fun initAttributes(context: Context, attrs: AttributeSet?, defStyle: Int) {
        val a = context.obtainStyledAttributes(
            attrs, R.styleable.SearchView, R.attr.searchViewStyle, R.attr.searchViewStyle
        )

        _rippleColor = a.getColor(R.styleable.SearchView_rippleColor, _rippleColor)

        _rippleAlpha = a.getInteger(R.styleable.SearchView_rippleAlpha, _rippleAlpha)

        _placeholderText = a.getString(
            R.styleable.SearchView_placeholderText
        )

        _placeholderColor = a.getColor(
            R.styleable.SearchView_placeholderColor,
            _placeholderColor
        )

        _placeholderDimension = a.getDimension(
            R.styleable.SearchView_placeholderDimension,
            _placeholderDimension
        )

        _surfaceColor = a.getColor(
            R.styleable.SearchView_surfaceColor,
            _surfaceColor
        )

        surfaceCornerRadius = a.getFloat(
            R.styleable.SearchView_surfaceCornerRadius,
            surfaceCornerRadius
        )

        iconColor = a.getColor(R.styleable.SearchView_iconColor, iconColor)

        iconDrawable = a.getDrawable(R.styleable.SearchView_icon)

        a.recycle()
    }

    private fun getRippleMask(): ShapeDrawable {
        if (_rippleMask != null) {
            return _rippleMask!!
        }

        val outerRadii = FloatArray(8) {
            surfaceCornerRadius
        }
        val shape = RoundRectShape(outerRadii, null, null)

        val mask = ShapeDrawable(shape)
        _rippleMask = mask

        return mask
    }

    private fun updateRippleEffect() {
        val stateList =
            ColorStateList.valueOf(rippleColor)
                .withAlpha(rippleAlpha)

        val rippleDrawable = RippleDrawable(stateList, null, getRippleMask())
        foreground = rippleDrawable
    }

    private fun updatePlaceholderPaint() {
        placeholderPaint?.let {
            it.textSize = placeholderDimension
            it.color = placeholderColor
            placeholderWidth = it.measureText(_placeholderText)
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

        drawSurface(canvas, contentWidth, contentHeight)
        drawIcon(canvas, contentHeight)
        drawPlaceholder(canvas, contentWidth, contentHeight)
    }

    private fun drawSurface(canvas: Canvas, contentWidth: Int, contentHeight: Int) {
        val surfaceRect = RectF().apply {
            left = paddingLeft.toFloat()
            right = (paddingLeft + contentWidth).toFloat()
            top = paddingTop.toFloat()
            bottom = (paddingTop + contentHeight).toFloat()
        }

        surfacePaint?.let {
            canvas.drawRoundRect(
                surfaceRect,
                surfaceCornerRadius,
                surfaceCornerRadius,
                it
            )
        }

    }

    private fun drawIcon(canvas: Canvas, contentHeight: Int) {
        val iconDrawableHeight = iconDrawable?.intrinsicHeight ?: 0
        val iconDrawableWidth = iconDrawable?.intrinsicWidth ?: 0

        val iconDrawableLeft = paddingLeft + (surfaceCornerRadius / 2).toInt()
        val iconDrawableRight = iconDrawableLeft + iconDrawableWidth
        val iconDrawableTop = paddingTop + (contentHeight - iconDrawableHeight) / 2
        val iconDrawableBottom = iconDrawableTop + iconDrawableHeight

        iconDrawable?.let {
            it.setBounds(
                iconDrawableLeft,
                iconDrawableTop,
                iconDrawableRight,
                iconDrawableBottom
            )
            it.setTint(iconColor)
            it.draw(canvas)
        }
    }

    private fun drawPlaceholder(canvas: Canvas, contentWidth: Int, contentHeight: Int) {
        val iconDrawableWidth = iconDrawable?.intrinsicWidth ?: 0

        val surfaceRightX = (paddingLeft + contentWidth).toFloat()

        val placeholderTop = paddingTop + (contentHeight + placeholderHeight * 2) / 2
        val placeholderLeft =
            paddingLeft + (surfaceCornerRadius / 2) + iconDrawableWidth + placeholderDimension / 2

        val maxWidth = surfaceRightX - surfaceCornerRadius - placeholderLeft

        val measureNum = placeholderPaint?.breakText(
            placeholderText,
            true,
            maxWidth,
            null
        ) ?: 0

        val placeholderTextSubstring = if (measureNum < (placeholderText?.length ?: 0)) {
            placeholderText?.substring(0, measureNum - 1) + "..."
        } else {
            placeholderText + ""
        }

        placeholderPaint?.let {
            canvas.drawText(
                placeholderTextSubstring,
                placeholderLeft,
                placeholderTop,
                it
            )
        }
    }
}