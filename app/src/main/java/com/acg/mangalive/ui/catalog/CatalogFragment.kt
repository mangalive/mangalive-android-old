package com.acg.mangalive.ui.catalog

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.acg.mangalive.R
import com.acg.mangalive.databinding.FragmentCatalogBinding
import com.acg.mangalive.viewModel.CatalogViewModel
import com.acg.mangalive.viewModel.DEFAULT_SORTING_MENU_STATE
import com.acg.mangalive.viewModel.SortingMenuState
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class CatalogFragment : Fragment() {
    private var _binding: FragmentCatalogBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CatalogViewModel by viewModels()

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

        binding.notificationsButton.setOnClickListener {
            findNavController().navigate(R.id.bottomNavigation_notifications)
        }

        sortingMenu.setOnMenuItemClickListener {
            viewModel.setSortingMenuState(convertSortingMenuItemIdToState(it.itemId))
            true
        }

        viewModel.uiState.observe(viewLifecycleOwner) {
            binding.sortingMenuButton.setText(convertSortingMenuStateToValue(it.sortingMenuState))
        }

        binding.sortingMenuButton.setOnClickListener {
            sortingMenu.show()
        }
    }

    private fun createSortingMenu() = PopupMenu(
        requireContext(), binding.sortingMenuButton, Gravity.END,
        androidx.appcompat.R.attr.popupMenuStyle,
        androidx.appcompat.R.style.Base_Widget_AppCompat_PopupMenu
    ).also {
        it.menuInflater.inflate(R.menu.catalog_sorting_menu, it.menu)
    }

    private fun convertSortingMenuItemIdToState(itemId: Int): SortingMenuState = when (itemId) {
        R.id.catalogSortingMenu_popularity -> SortingMenuState.Popularity
        R.id.catalogSortingMenu_views -> SortingMenuState.Views
        R.id.catalogSortingMenu_novelty -> SortingMenuState.Novelty
        R.id.catalogSortingMenu_likes -> SortingMenuState.Likes
        R.id.catalogSortingMenu_recentUpdates -> SortingMenuState.RecentUpdate
        R.id.sortingMenu_chapterCount -> SortingMenuState.ChapterCount
        R.id.sortingMenu_random -> SortingMenuState.Random
        else -> DEFAULT_SORTING_MENU_STATE
    }

    private fun convertSortingMenuStateToValue(state: SortingMenuState): Int = when (state) {
        SortingMenuState.Popularity -> R.string.sortingMenu_popularity
        SortingMenuState.Views -> R.string.sortingMenu_views
        SortingMenuState.Novelty -> R.string.sortingMenu_novelty
        SortingMenuState.Likes -> R.string.sortingMenu_likes
        SortingMenuState.RecentUpdate -> R.string.sortingMenu_recent_updates
        SortingMenuState.ChapterCount -> R.string.sortingMenu_chapter_count
        SortingMenuState.Random -> R.string.sortingMenu_random
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
