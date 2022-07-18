package com.acg.mangalive

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.graphics.drawable.RippleDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatTextView

class SearchView(context: Context, attrs: AttributeSet?, defStyle: Int) :
    View(context, attrs, defStyle, R.style.Widget_Theme_Mangalive_SearchView) {
    private var placeholderWidth = 0f
    private var placeholderHeight = 0f

    private var placeholderPaint: TextPaint? = null
    private var surfacePaint: Paint? = null
    private var iconDrawable: Drawable? = null

    var iconColor = 0

    var iconSize = 0

    private var _textAppearanceId = 0
    var textAppearanceId
        get() = _textAppearanceId
        set(value) {
            _textAppearanceId = value
            updatePlaceholderPaint()
        }

    private var _fontFamily: Typeface? = null
    var fontFamily
        get() = _fontFamily
        set(value) {
            _fontFamily = value
            updatePlaceholderPaint()
        }

    private var _textSize = 0f
    var textSize
        get() = _textSize
        set(value) {
            _textSize = value
            updatePlaceholderPaint()
        }

    private var _letterSpacing = 0f
    var letterSpacing
        get() = _letterSpacing
        set(value) {
            _letterSpacing = value
            updatePlaceholderPaint()
        }

    private var _textAllCaps = false
    var textAllCaps
        get() = _textAllCaps
        set(value) {
            _textAllCaps = value
            updatePlaceholderPaint()
        }

    private var _textColor = 0
    var textColor
        get() = _textColor
        set(value) {
            _textColor = value
            updatePlaceholderPaint()
        }

    private var _text: String? = null
    var text
        get() = _text
        set(value) {
            _text = value
            updatePlaceholderPaint()
        }

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

    var surfaceCornerRadius = 0f

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
            attrs, R.styleable.SearchView, defStyle, R.attr.searchViewStyle
        )

        _rippleColor = a.getColor(R.styleable.SearchView_rippleColor, _rippleColor)

        _rippleAlpha = a.getInteger(R.styleable.SearchView_rippleAlpha, _rippleAlpha)

        _textAppearanceId =
            a.getResourceId(R.styleable.SearchView_android_textAppearance, _textAppearanceId)

        _text = a.getString(R.styleable.SearchView_android_text)

        _textColor = a.getColor(R.styleable.SearchView_android_textColor, _textColor)

        _textSize = a.getDimension(R.styleable.SearchView_android_textSize, _textSize)

        _textAllCaps = a.getBoolean(R.styleable.SearchView_android_textAllCaps, _textAllCaps)

        _fontFamily = a.getFont(R.styleable.SearchView_android_fontFamily)

        _letterSpacing =
            a.getDimension(R.styleable.SearchView_android_letterSpacing, _letterSpacing)

        _surfaceColor = a.getColor(
            R.styleable.SearchView_surfaceColor,
            _surfaceColor
        )

        surfaceCornerRadius = a.getDimension(
            R.styleable.SearchView_surfaceCornerRadius,
            surfaceCornerRadius
        )

        iconColor = a.getColor(R.styleable.SearchView_iconColor, iconColor)

        iconDrawable = a.getDrawable(R.styleable.SearchView_icon)

        iconSize = a.getDimensionPixelSize(R.styleable.SearchView_iconSize, iconSize)

        a.recycle()
    }

    private fun getRippleMask(): ShapeDrawable {
        val outerRadii = FloatArray(8) {
            surfaceCornerRadius
        }
        val shape = RoundRectShape(outerRadii, null, null)

        return ShapeDrawable(shape)
    }

    private fun updateRippleEffect() {
        val stateList =
            ColorStateList.valueOf(rippleColor)
                .withAlpha(rippleAlpha)

        val rippleDrawable = RippleDrawable(stateList, null, getRippleMask())
        foreground = rippleDrawable
    }

    private fun updatePlaceholderPaintByTextAppearance() {
        val textView = AppCompatTextView(context)
        textView.setTextAppearance(textAppearanceId)

        _text?.let {
            if (textView.isAllCaps) {
                _text = it.uppercase()
            }
        }

        placeholderPaint?.let {
            it.textSize = textView.textSize
            it.typeface = textView.typeface
            it.letterSpacing = textView.letterSpacing
            it.color = textView.currentTextColor

            placeholderWidth = it.measureText(text)
            placeholderHeight = it.fontMetrics.bottom
        }
    }

    private fun updatePlaceholderPaintByAttrs() {
        _text?.let {
            if (textAllCaps) {
                _text = it.uppercase()
            }
        }

        placeholderPaint?.let {
            it.textSize = textSize
            it.typeface = fontFamily
            it.letterSpacing = letterSpacing
            placeholderWidth = it.measureText(text)
            placeholderHeight = it.fontMetrics.bottom
        }
    }

    private fun updatePlaceholderPaint() {
        if (textAppearanceId != 0) {
            updatePlaceholderPaintByTextAppearance()
        } else {
            updatePlaceholderPaintByAttrs()
        }

        if (textColor != 0) {
            placeholderPaint?.color = textColor
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

    private fun getIconHeight(): Int {
        return if (iconSize != 0) iconSize else (iconDrawable?.intrinsicHeight ?: 0)
    }

    private fun getIconWidth(): Int {
        return if (iconSize != 0) iconSize else (iconDrawable?.intrinsicWidth ?: 0)
    }

    private fun drawIcon(canvas: Canvas, contentHeight: Int) {
        val iconDrawableLeft = paddingLeft + (surfaceCornerRadius / 2).toInt()
        val iconDrawableRight = iconDrawableLeft + getIconWidth()
        val iconDrawableTop = paddingTop + (contentHeight - getIconHeight()) / 2
        val iconDrawableBottom = iconDrawableTop + getIconHeight()

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
        val surfaceRightX = (paddingLeft + contentWidth).toFloat()

        val placeholderTop = paddingTop + (contentHeight + placeholderHeight * 2) / 2
        val placeholderLeft =
            paddingLeft + (surfaceCornerRadius / 2) + getIconWidth() + textSize / 2

        val maxWidth = surfaceRightX - surfaceCornerRadius - placeholderLeft

        val measureNum = placeholderPaint?.breakText(
            text,
            true,
            maxWidth,
            null
        ) ?: 0

        val placeholderTextSubstring = if (measureNum < (text?.length ?: 0)) {
            text?.substring(0, measureNum - 1) + "..."
        } else {
            text + ""
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