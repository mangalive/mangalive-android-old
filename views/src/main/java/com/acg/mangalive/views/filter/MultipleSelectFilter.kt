package com.acg.mangalive.views.filter

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.res.getStringOrThrow
import androidx.core.content.res.getTextArrayOrThrow
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.acg.mangalive.views.R
import com.acg.mangalive.views.databinding.MultipleSelectFilterBinding

class MultipleSelectFilter : FrameLayout {

    interface OnSelectUpdateListener {
        fun onSelectUpdate(selectedEntries: List<Int>)
    }

    private var binding: MultipleSelectFilterBinding? = null

    private var _title: String? = null
    var title: String?
        set(value) {
            _title = value
            bottomSheet?.title = value ?: ""
            if (selectedEntriesIndexes.isEmpty()) binding?.chip?.text = value
        }
        get() = _title

    private var _pluralTitle: String? = null
    var pluralTitle: String?
        set(value) {
            _pluralTitle = value
            if (selectedEntriesIndexes.isNotEmpty()) binding?.chip?.text = value
        }
        get() = _pluralTitle

    private var _entries: List<String> = listOf()
    var entries: List<String>
        set(value) {
            _entries = value
            bottomSheet?.entriesValues = value
        }
        get() = _entries

    var selectedEntriesIndexes = listOf<Int>()
        private set

    private var bottomSheet: MultipleSelectBottomSheet? = null

    private var onSelectUpdateCallback: ((List<Int>) -> Unit)? = null

    private val foundFragmentManager: FragmentManager by lazy {
        val fragment = FragmentManager.findFragment<Fragment>(this@MultipleSelectFilter)
        fragment.parentFragmentManager
    }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(
        context,
        attrs,
        R.style.Widget_Mangalive_Filter_MultipleSelect
    )

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        binding = MultipleSelectFilterBinding.inflate(LayoutInflater.from(context), this)

        initAttributes(attrs, defStyleAttr)
        initChip()
        initBottomSheet()
    }

    private fun initAttributes(attrs: AttributeSet?, defStyleAttr: Int) {
        val attributes = context.obtainStyledAttributes(
            attrs,
            R.styleable.MultipleSelectFilter,
            defStyleAttr,
            R.style.Widget_Mangalive_Filter_MultipleSelect
        )

        title = attributes.getStringOrThrow(R.styleable.MultipleSelectFilter_title)

        pluralTitle = attributes.getStringOrThrow(R.styleable.MultipleSelectFilter_pluralTitle)

        entries = attributes.getTextArrayOrThrow(R.styleable.MultipleSelectFilter_entries)
            .map { it.toString() }

        attributes.recycle()
    }

    private fun initChip() = binding?.chip?.apply {
        isCheckable = false
    }

    private fun initBottomSheet() = MultipleSelectBottomSheet(title ?: "", entries).let {
        bottomSheet = it

        it.setOnCloseListener(::updateSelectedEntries)

        binding?.chip?.setOnClickListener { openBottomSheet() }
        binding?.chip?.setOnCloseIconClickListener { reset() }
    }

    private fun updateSelectedEntries(entries: List<Entry>) {
        binding?.chip?.text = if (entries.isEmpty()) title else "$pluralTitle: ${entries.size}"

        binding?.chip?.apply {
            isCloseIconVisible = entries.isNotEmpty()

            isCheckable = true
            isChecked = entries.isNotEmpty()
            isCheckable = false
        }

        if (entries.isEmpty()) bottomSheet?.reset()

        selectedEntriesIndexes = entries.map { it.index }
        onSelectUpdateCallback?.invoke(selectedEntriesIndexes)
    }


    private fun openBottomSheet() = bottomSheet?.apply {
        if (!isVisible) {
            show(foundFragmentManager, MultipleSelectBottomSheet.TAG)
        }
    }

    fun reset() =
        updateSelectedEntries(listOf())


    fun setOnSelectUpdateListener(listener: OnSelectUpdateListener) {
        onSelectUpdateCallback = listener::onSelectUpdate
    }

    fun setOnSelectUpdateListener(listener: (List<Int>) -> Unit) {
        onSelectUpdateCallback = listener
    }
}