package com.acg.mangalive.catalog.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.acg.mangalive.catalog.databinding.FragmentCatalogSingleSelectFilterBinding
import com.acg.mangalive.catalog.ui.bottomSheets.SelectBottomSheetItem
import com.acg.mangalive.catalog.ui.bottomSheets.SingleSelectBottomSheet
import kotlin.properties.Delegates


class CatalogSingleSelectFilter : Fragment() {
    private lateinit var binding: FragmentCatalogSingleSelectFilterBinding

    private lateinit var title: String
    private lateinit var itemsValues: List<String>
    private var defaultItemIndex by Delegates.notNull<Int>()

    private lateinit var bottomSheet: SingleSelectBottomSheet

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCatalogSingleSelectFilterBinding.inflate(inflater, container, false)

        val titleId = arguments?.getInt(TITLE_ID_ARG)
        val itemsId = arguments?.getInt(ITEMS_ID_ARG)
        defaultItemIndex = arguments?.getInt(DEFAULT_ITEM_INDEX_ARG) ?: 0

        title = titleId?.let { resources.getString(it) } ?: ""
        itemsValues = itemsId?.let { resources.getStringArray(it).toList() } ?: listOf()

        bottomSheet = SingleSelectBottomSheet(title, itemsValues)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.chip.text = title
        binding.chip.isCheckable = false
        binding.chip.isChecked = true

        bottomSheet.setOnItemSelectListener(::onItemSelect)

        binding.chip.setOnClickListener(::onOpenBottomSheet)

        binding.chip.setOnCloseIconClickListener(::onReset)
    }

    private fun onItemSelect(item: SelectBottomSheetItem) {
        binding.chip.text = item.value
        updateSelectedItemIndex(item.position)
    }

    private fun onOpenBottomSheet(view: View?) {
        if (!bottomSheet.isVisible) {
            bottomSheet.show(parentFragmentManager, SingleSelectBottomSheet.TAG)
        }
    }

    private fun onReset(view: View?) {
        binding.chip.text = itemsValues[defaultItemIndex]
        updateSelectedItemIndex(defaultItemIndex)
    }

    private fun updateSelectedItemIndex(index: Int) {
//        val args = Bundle()
//        args.putInt(ITEM_INDEX_RESULT_ARG, index)
    }

    companion object {
        const val TAG = "CATALOG_SINGLE_SELECT_FILTER"

        private const val TITLE_ID_ARG = "TITLE_ID_ARG"
        private const val ITEMS_ID_ARG = "ITEMS_ID_ARG"
        private const val DEFAULT_ITEM_INDEX_ARG = "DEFAULT_ITEM_INDEX_ARG"

        fun newInstance(
            titleId: Int,
            itemsId: Int,
            defaultItemIndex: Int
        ): CatalogSingleSelectFilter {
            val args = Bundle()
            args.putInt(TITLE_ID_ARG, titleId)
            args.putInt(ITEMS_ID_ARG, itemsId)
            args.putInt(DEFAULT_ITEM_INDEX_ARG, defaultItemIndex)

            val instance = CatalogSingleSelectFilter()
            instance.arguments = args

            return instance
        }
    }
}