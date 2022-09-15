package com.acg.mangalive.views.filter

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.res.getIntOrThrow
import androidx.core.content.res.getTextArrayOrThrow
import androidx.core.content.res.getTextOrThrow
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.acg.mangalive.views.R
import com.acg.mangalive.views.databinding.SingleSelectFilterBinding

class SingleSelectFilter : FrameLayout {

    interface OnSelectUpdateListener {
        fun onSelectUpdate(index: Int)
    }

    private var binding: SingleSelectFilterBinding? = null

    private var _title: String? = null
    var title: String?
        set(value) {
            _title = value
            value?.let { bottomSheet?.title = it }
        }
        get() = _title

    private var _entries: List<String> = listOf()
    var entries: List<String>
        set(value) {
            _entries = value
            bottomSheet?.entries = value
        }
        get() = _entries

    private var _defaultEntryIndex = 0
    var defaultEntryIndex
        set(value) {
            _defaultEntryIndex = value

            if (selectedEntryIndex == value) {
                binding?.chip?.text = entries[value]
            }
        }
        get() = _defaultEntryIndex

    var selectedEntryIndex = defaultEntryIndex
        private set

    private var bottomSheet: SingleSelectBottomSheet? = null

    private var onSelectUpdateCallback: ((Int) -> Unit)? = null

    private val foundFragmentManager: FragmentManager by lazy {
        val fragment = FragmentManager.findFragment<Fragment>(this@SingleSelectFilter)
        fragment.parentFragmentManager
    }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(
        context,
        attrs,
        R.style.Widget_Mangalive_Filter_SingleSelect
    )

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        binding = SingleSelectFilterBinding.inflate(LayoutInflater.from(context), this)

        initAttributes(attrs, defStyleAttr)
        initChip()
        initBottomSheet()
    }

    private fun initAttributes(attrs: AttributeSet?, defStyleAttr: Int) {
        val attributes = context.obtainStyledAttributes(
            attrs,
            R.styleable.SingleSelectFilter,
            defStyleAttr,
            R.style.Widget_Mangalive_Filter_SingleSelect
        )

        title = attributes.getTextOrThrow(R.styleable.SingleSelectFilter_title).toString()

        entries = attributes.getTextArrayOrThrow(R.styleable.SingleSelectFilter_entries)
            .map { it.toString() }

        defaultEntryIndex =
            attributes.getIntOrThrow(R.styleable.SingleSelectFilter_defaultEntryIndex)

        attributes.recycle()
    }

    private fun initChip() = binding?.chip?.apply {
        text = entries[defaultEntryIndex]
        isChecked = true
        isCheckable = false
    }

    private fun initBottomSheet() = SingleSelectBottomSheet(title ?: "", entries).let {
        bottomSheet = it

        it.setOnEntrySelectListener(::updateSelectedEntry)

        binding?.chip?.setOnClickListener { openBottomSheet() }
        binding?.chip?.setOnCloseIconClickListener { reset() }
    }

    private fun updateSelectedEntry(item: Entry) {
        binding?.chip?.text = item.value
        selectedEntryIndex = item.index
        onSelectUpdateCallback?.invoke(selectedEntryIndex)
    }

    private fun openBottomSheet() = bottomSheet?.apply {
        if (!isVisible) {
            show(foundFragmentManager, SingleSelectBottomSheet.TAG)
        }
    }

    fun reset() =
        updateSelectedEntry(Entry(defaultEntryIndex, entries[defaultEntryIndex]))


    fun setOnSelectUpdateListener(listener: OnSelectUpdateListener) {
        onSelectUpdateCallback = listener::onSelectUpdate
    }

    fun setOnSelectUpdateListener(listener: (Int) -> Unit) {
        onSelectUpdateCallback = listener
    }
}