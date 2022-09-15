package com.acg.mangalive.catalog.ui.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.acg.mangalive.catalog.databinding.FragmentCatalogNonSingleSelectFilterBinding
import com.acg.mangalive.catalog.ui.bottomSheets.NonSingleSelectBottomSheet
import com.acg.mangalive.catalog.ui.bottomSheets.SelectBottomSheetItem
import com.acg.mangalive.share.di.lazyViewModel
import javax.inject.Inject

class NonSingleSelectFilter : Fragment() {
    private lateinit var binding: FragmentCatalogNonSingleSelectFilterBinding

    private lateinit var title: String
    private lateinit var pluralTitle: String
    private lateinit var itemsValues: List<String>

    private lateinit var bottomSheet: NonSingleSelectBottomSheet

    @Inject
    lateinit var viewModelFactory: FilterViewModel.Factory

    val viewModel: FilterViewModel by lazyViewModel({ requireParentFragment() }, {
        viewModelFactory.create(it)
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCatalogNonSingleSelectFilterBinding.inflate(inflater, container, false)

        val titleId = arguments?.getInt(TITLE_ID_ARG)
        val itemsId = arguments?.getInt(ITEMS_ID_ARG)
        val pluralTitleId = arguments?.getInt(PLURAL_TITLE_ID_ARG)

        title = titleId?.let { resources.getString(it) } ?: ""
        pluralTitle = pluralTitleId?.let { resources.getString(it) } ?: ""
        itemsValues = itemsId?.let { resources.getStringArray(it).toList() } ?: listOf()

        bottomSheet = NonSingleSelectBottomSheet(title, itemsValues)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.chip.text = title
        binding.chip.isCheckable = false

        bottomSheet.setOnCloseListener(::onItemsSelect)

        bottomSheet.setOnResetListener(::onReset)

        binding.chip.setOnClickListener(::onOpenBottomSheet)

        binding.chip.setOnCloseIconClickListener(::onReset)
    }

    private fun onItemsSelect(items: List<SelectBottomSheetItem>) = with(binding) {
        updateSelectedItemsIndexes(items.map { it.position })

        if (items.isEmpty()) {
            onReset()
        } else {
            chip.text = "$pluralTitle: ${items.size}"

            chip.isCloseIconVisible = true

            chip.isCheckable = true
            chip.isChecked = true
            chip.isCheckable = false
        }
    }

    private fun onReset() = onReset(null)

    private fun onReset(view: View?) = with(binding) {
        bottomSheet.reset()
        updateSelectedItemsIndexes(listOf())

        chip.isCheckable = true
        chip.isChecked = false
        chip.isCheckable = false

        chip.text = title
        chip.isCloseIconVisible = false
    }

    private fun onOpenBottomSheet(view: View?) {
        if (!bottomSheet.isVisible) {
            bottomSheet.show(parentFragmentManager, NonSingleSelectBottomSheet.TAG)
        }
    }

    private fun convertListToArrayList(list: List<Int>): ArrayList<Int> {
        val arrayList = arrayListOf<Int>()

        for (i in list.indices) {
            arrayList.add(list[i])
        }

        return arrayList
    }

    private fun updateSelectedItemsIndexes(indexes: List<Int>) {
    }

    companion object {
        const val TAG = "CATALOG_SINGLE_SELECT_FILTER"

        private const val TITLE_ID_ARG = "TITLE_ID_ARG"
        private const val PLURAL_TITLE_ID_ARG = "PLURAL_TITLE_ID_ARG"
        private const val ITEMS_ID_ARG = "ITEMS_ID_ARG"

        fun newInstance(
            titleId: Int,
            itemsId: Int,
            pluralTitleId: Int,
        ): NonSingleSelectFilter {
            val args = Bundle()
            args.putInt(TITLE_ID_ARG, titleId)
            args.putInt(ITEMS_ID_ARG, itemsId)
            args.putInt(PLURAL_TITLE_ID_ARG, pluralTitleId)

            val instance = NonSingleSelectFilter()
            instance.arguments = args

            return instance
        }
    }
}
