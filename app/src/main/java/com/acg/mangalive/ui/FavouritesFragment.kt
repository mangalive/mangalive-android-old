package com.acg.mangalive.ui

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.acg.mangalive.R
import com.acg.mangalive.databinding.FragmentFavouritesBinding
import com.acg.mangalive.viewModel.CategoriesMenuState
import com.acg.mangalive.viewModel.DEFAULT_CATEGORY_MENU_STATE
import com.acg.mangalive.viewModel.FavouritesViewModel

class FavouritesFragment : Fragment() {
    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavouritesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoriesMenu = createCategoriesMenu()

        categoriesMenu.setOnMenuItemClickListener {
            viewModel.setCategoryMenuState(convertCategoriesMenuItemIdToState(it.itemId))
            true
        }

        viewModel.uiState.observe(viewLifecycleOwner) {
            binding.categoriesMenuButton.setText(convertCategoriesMenuStateToValue(it.categoriesMenuState))
        }

        binding.categoriesMenuButton.setOnClickListener {
            categoriesMenu.show()
        }
    }

    private fun createCategoriesMenu() = PopupMenu(
        requireContext(), binding.categoriesMenuButton, Gravity.END,
        androidx.appcompat.R.attr.popupMenuStyle,
        androidx.appcompat.R.style.Base_Widget_AppCompat_PopupMenu
    ).also {
        it.menuInflater.inflate(R.menu.favourites_categories_menu, it.menu)
    }

    private fun convertCategoriesMenuItemIdToState(itemId: Int): CategoriesMenuState = when (itemId) {
        R.id.favouritesCategoriesMenu_completed -> CategoriesMenuState.Completed
        R.id.favouritesCategoriesMenu_dropped -> CategoriesMenuState.Dropped
        R.id.favouritesCategoriesMenu_currentlyReading -> CategoriesMenuState.CurrentlyReading
        R.id.favouritesCategoriesMenu_onHold -> CategoriesMenuState.OnHold
        R.id.favouritesCategoriesMenu_planToRead -> CategoriesMenuState.PlanToRead
        else -> DEFAULT_CATEGORY_MENU_STATE
    }

    private fun convertCategoriesMenuStateToValue(state: CategoriesMenuState): Int = when (state) {
        CategoriesMenuState.Completed -> R.string.categoryMenu_completed
        CategoriesMenuState.Dropped -> R.string.categoryMenu_dropped
        CategoriesMenuState.CurrentlyReading -> R.string.categoryMenu_currently_reading
        CategoriesMenuState.OnHold -> R.string.categoryMenu_on_hold
        CategoriesMenuState.PlanToRead -> R.string.categoryMenu_plan_to_read
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}