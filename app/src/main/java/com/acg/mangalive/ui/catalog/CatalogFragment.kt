package com.acg.mangalive.ui.catalog

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.viewModels
import com.acg.mangalive.R
import com.acg.mangalive.databinding.FragmentCatalogBinding
import com.acg.mangalive.viewModel.CatalogViewModel
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.acg.mangalive.domain.model.SortingCriterion
import com.acg.mangalive.viewModel.DEFAULT_SORTING_CRITERION
import dagger.android.AndroidInjection
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

        val sortingMenu = createSortingMenu()

        binding.NotificationsBtn.setOnClickListener {
            findNavController().navigate(R.id.NavGraph_Notifications)
        }

        sortingMenu.setOnMenuItemClickListener {
            viewModel.setSortingCriterion(convertMenuItemIdToSortingCriterion(it.itemId))
            true
        }

        viewModel.sortingParameters.observe(viewLifecycleOwner) {
            binding.SortingMenuBtn.setText(convertSortingCriterionToValue(it.criterion))
        }

        binding.SortingMenuBtn.setOnClickListener {
            sortingMenu.show()
        }
    }

    private fun createSortingMenu() = PopupMenu(
        requireContext(), binding.SortingMenuBtn, Gravity.END,
        androidx.appcompat.R.attr.popupMenuStyle,
        androidx.appcompat.R.style.Base_Widget_AppCompat_PopupMenu
    ).also {
        it.menuInflater.inflate(R.menu.catalog_sorting_menu, it.menu)
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
