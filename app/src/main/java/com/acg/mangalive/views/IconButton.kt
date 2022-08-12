package com.acg.mangalive.views

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.graphics.drawable.RippleDrawable
import android.graphics.drawable.VectorDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.res.getDrawableOrThrow
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable
import com.acg.mangalive.R
import kotlin.math.pow
import kotlin.math.sqrt

class IconButton(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    AppCompatImageButton(context, attrs, defStyleAttr) {
    private var _iconDrawable: Drawable? = null
    var iconDrawable
        get() = _iconDrawable
        set(value) {
            _iconDrawable = value
            updateIcon()
        }

    private var _iconColor = 0
    var iconColor
        get() = _iconColor
        set(value) {
            _iconColor = value
            updateIcon()
        }

    private var _iconSize = 0
    var iconSize
        get() = _iconSize
        set(value) {
            _iconSize = value
            updateIcon()
        }

    private var _rippleColor = 0
    var rippleColor
        get() = _iconColor
        set(value) {
            _rippleColor = value
            updateRipple()
        }

    private var _rippleAlpha = 0
    var rippleAlpha
        get() = _rippleAlpha
        set(value) {
            _rippleAlpha = value
            updateRipple()
        }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(
        context,
        attrs,
        R.attr.iconButtonStyle
    )

    init {
        isClickable = true

        initAttrs(context, attrs, defStyleAttr)
        updateIcon()
        updateRipple()
    }

    private fun initAttrs(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        val a = context.obtainStyledAttributes(
            attrs,
            R.styleable.IconButton,
            defStyleAttr,
            R.attr.iconButtonStyle
        )

        _iconDrawable = a.getDrawableOrThrow(R.styleable.IconButton_icon)

        _iconColor = a.getColor(R.styleable.IconButton_iconColor, _iconColor)

        _iconSize = a.getDimensionPixelSize(R.styleable.IconButton_iconSize, _iconSize)

        _rippleColor =
            a.getColor(R.styleable.IconButton_rippleColor, _rippleColor)

        _rippleAlpha =
            a.getInteger(R.styleable.IconButton_rippleAlpha, _rippleAlpha)

        a.recycle()
    }

    private fun updateIcon() {
        _iconDrawable?.let {
            it.setTint(_iconColor)

            val icon = if (iconSize != 0) {
                (it as VectorDrawable).toBitmap(iconSize, iconSize, null).toDrawable(resources)
            } else {
                it
            }

            setImageDrawable(icon)
        }
    }

    private fun getIconWidth(): Int {
        return if (iconSize != 0) iconSize else (iconDrawable?.intrinsicWidth ?: 0)
    }

    private fun getIconHeight(): Int {
        return if (iconSize != 0) iconSize else (iconDrawable?.intrinsicHeight ?: 0)
    }

    private fun updateRipple() {
        val colorList = ColorStateList.valueOf(_rippleColor).withAlpha(_rippleAlpha)

        val rippleDrawable = RippleDrawable(
            colorList, null, null
        )

        rippleDrawable.radius = iconDrawable?.let {
            sqrt(
                (getIconHeight() / 2.0).pow(2.0) + (getIconWidth() / 2.0)
                    .pow(2.0)
            ).toInt()
        } ?: 0

        background = rippleDrawable
    }
}