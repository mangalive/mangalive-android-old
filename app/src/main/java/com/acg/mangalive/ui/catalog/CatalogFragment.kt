package com.acg.mangalive.ui.catalog

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import com.acg.mangalive.R
import com.acg.mangalive.databinding.FragmentCatalogBinding
import com.acg.mangalive.viewModel.CatalogViewModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.acg.mangalive.domain.model.SortingCriterion
import com.acg.mangalive.viewModel.DEFAULT_SORTING_CRITERION
import com.acg.mangalive.viewModel.DEFAULT_SORTING_CRITERION_VALUE
import com.google.android.material.chip.Chip
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class CatalogFragment : Fragment() {
    private var _binding: FragmentCatalogBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel: CatalogViewModel by viewModels {
        viewModelFactory
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)

        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatalogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSingleSelectBottomSheet(
            binding.sortingCriterionChip,
            R.string.sortingCriterion_title,
            R.array.sortingCriterion_items
        )

        initNonSingleSelectBottomSheet(
            binding.typeChip,
            R.string.type_title,
            R.string.type_plural_title,
            R.array.type_items
        )

        initNonSingleSelectBottomSheet(
            binding.genreChip,
            R.string.genre_title,
            R.string.genre_plural_title,
            R.array.genre_items
        )

        initNonSingleSelectBottomSheet(
            binding.categoryChip,
            R.string.category_title,
            R.string.category_plural_title,
            R.array.category_items
        )

        val adapter = CatalogMangaAdapter(requireContext())

        binding.catalog.adapter = adapter

        binding.NotificationsBtn.setOnClickListener {
            findNavController().navigate(R.id.NavGraph_Notifications)
        }

//        sortingMenu.setOnMenuItemClickListener {
//            viewModel.setSortingCriterion(convertMenuItemIdToSortingCriterion(it.itemId))
//            true
//        }

//        viewModel.sortingParameters.observe(viewLifecycleOwner) {
//            binding.SortingMenuBtn.setText(convertSortingCriterionToValue(it.criterion))
//        }

        viewModel.catalog.observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle, it)
        }

//        binding.SortingMenuBtn.setOnClickListener {
//            sortingMenu.show()
//        }
    }

    private fun initSingleSelectBottomSheet(chip: Chip, titleId: Int, itemsId: Int) {
        val title = resources.getString(titleId)
        val items = resources.getStringArray(itemsId).toList()

        chip.isCheckable = false
        chip.isChecked = true

        val bottomSheet = CatalogBottomSheet(
            title,
            items,
            isSingleSelect = true,
        )

        bottomSheet.setOnItemSelectListener {
            chip.text = it.value
        }

        chip.setOnClickListener {
            if (!bottomSheet.isVisible) {
                bottomSheet.show(parentFragmentManager, CatalogBottomSheet.TAG)
            }
        }

        chip.setOnCloseIconClickListener {
            if (!bottomSheet.isVisible) {
                bottomSheet.show(parentFragmentManager, CatalogBottomSheet.TAG)
            } else {
                chip.text = items[0]
            }
        }

        bottomSheet.setOnResetListener {
            chip.text = items[0]
        }
    }

    private fun initNonSingleSelectBottomSheet(
        chip: Chip,
        titleId: Int,
        pluralTitleId: Int,
        itemsId: Int
    ) {
        val title = resources.getString(titleId)
        val pluralTitle = resources.getString(pluralTitleId)
        val items = resources.getStringArray(itemsId).toList()

        val bottomSheet = CatalogBottomSheet(
            title,
            items,
            isSingleSelect = false,
        )

        chip.isCheckable = false

        bottomSheet.setOnItemsSelectListener {
            if (it.isEmpty()) {
                onNonSingleBottomSheetReset(chip, title)
            } else {
                onBottomSheetItemsSelect(chip, pluralTitle, it)
            }
        }

        chip.setOnClickListener {
            if (!bottomSheet.isVisible) {
                bottomSheet.show(parentFragmentManager, CatalogBottomSheet.TAG)
            }
        }

        chip.setOnCloseIconClickListener {
            if (!bottomSheet.isVisible && !chip.isChecked) {
                bottomSheet.show(parentFragmentManager, CatalogBottomSheet.TAG)
            } else {
                bottomSheet.reset()
                onNonSingleBottomSheetReset(chip, title)
            }
        }

        bottomSheet.setOnResetListener {
            onNonSingleBottomSheetReset(chip, title)
        }
    }


    private fun onNonSingleBottomSheetReset(chip: Chip, title: String) {
        chip.isCheckable = true
        chip.isChecked = false
        chip.isCheckable = false

        chip.text = title
        chip.isCloseIconVisible = false
    }

    private fun onBottomSheetItemsSelect(chip: Chip, pluralTitle: String, items: List<CatalogBottomSheet.Item>) {
        chip.text = "$pluralTitle: ${items.size}"

        chip.isCloseIconVisible = true

        chip.isCheckable = true
        chip.isChecked = true
        chip.isCheckable = false
    }

    private fun convertMenuItemIdToSortingCriterion(itemId: Int) = when (itemId) {
        R.id.CatalogSortingMenu_Novelty -> SortingCriterion.novelty
        R.id.CatalogSortingMenu_Views -> SortingCriterion.views
        R.id.CatalogSortingMenu_Popularity -> SortingCriterion.popularity
        R.id.CatalogSortingMenu_RecentUpdates -> SortingCriterion.recentUpdates
        R.id.CatalogSortingMenu_ChapterCount -> SortingCriterion.chapterCount
        else -> DEFAULT_SORTING_CRITERION
//        R.id.CatalogSortingMenu_Likes
//        R.id.SortingMenu_Random
    }

    private fun convertMenuItemIdToValue(itemId: Int) = when (itemId) {
        R.id.CatalogSortingMenu_Novelty -> R.string.sortingMenu_novelty
        R.id.CatalogSortingMenu_Views -> R.string.sortingMenu_views
        R.id.CatalogSortingMenu_Popularity -> R.string.sortingMenu_popularity
        R.id.CatalogSortingMenu_RecentUpdates -> R.string.sortingMenu_recent_updates
        R.id.CatalogSortingMenu_ChapterCount -> R.string.sortingMenu_chapter_count
        else -> DEFAULT_SORTING_CRITERION_VALUE
//        R.id.CatalogSortingMenu_Likes
//        R.id.SortingMenu_Random
    }

    private fun convertSortingCriterionToValue(criterion: SortingCriterion) = when (criterion) {
        SortingCriterion.popularity -> R.string.sortingMenu_popularity
        SortingCriterion.views -> R.string.sortingMenu_views
        SortingCriterion.novelty -> R.string.sortingMenu_novelty
        SortingCriterion.recentUpdates -> R.string.sortingMenu_recent_updates
        SortingCriterion.chapterCount -> R.string.sortingMenu_chapter_count
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
