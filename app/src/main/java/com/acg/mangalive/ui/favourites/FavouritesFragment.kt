package com.acg.mangalive.ui.favourites

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.acg.mangalive.R
import com.acg.mangalive.databinding.FragmentFavouritesBinding
import com.acg.mangalive.viewModel.CatalogViewModel
import com.acg.mangalive.viewModel.CategoriesMenuState
import com.acg.mangalive.viewModel.DEFAULT_CATEGORY_MENU_STATE
import com.acg.mangalive.viewModel.FavouritesViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class FavouritesFragment : Fragment() {
    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel: FavouritesViewModel by viewModels {
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
            binding.CategoriesMenuBtn.setText(convertCategoriesMenuStateToValue(it.categoriesMenuState))
        }

        binding.CategoriesMenuBtn.setOnClickListener {
            categoriesMenu.show()
        }

        binding.NotificationsBtn.setOnClickListener {
            findNavController().navigate(R.id.NavGraph_Notifications)
        }
    }

    private fun createCategoriesMenu() = PopupMenu(
        requireContext(), binding.CategoriesMenuBtn, Gravity.LEFT,
        androidx.appcompat.R.attr.popupMenuStyle,
        androidx.appcompat.R.style.Base_Widget_AppCompat_PopupMenu
    ).also {
        it.menuInflater.inflate(R.menu.favourites_categories_menu, it.menu)
    }

    private fun convertCategoriesMenuItemIdToState(itemId: Int): CategoriesMenuState =
        when (itemId) {
            R.id.FavouritesCategoriesMenu_Completed -> CategoriesMenuState.Completed
            R.id.FavouritesCategoriesMenu_Dropped -> CategoriesMenuState.Dropped
            R.id.FavouritesCategoriesMenu_CurrentlyReading -> CategoriesMenuState.CurrentlyReading
            R.id.FavouritesCategoriesMenu_OnHold -> CategoriesMenuState.OnHold
            R.id.FavouritesCategoriesMenu_PlanToRead -> CategoriesMenuState.PlanToRead
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