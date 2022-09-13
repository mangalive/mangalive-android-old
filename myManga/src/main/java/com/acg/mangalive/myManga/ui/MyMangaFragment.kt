package com.acg.mangalive.myManga.ui

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.acg.mangalive.myManga.R
import com.acg.mangalive.myManga.databinding.FragmentMyMangaBinding
import com.acg.mangalive.myManga.domain.model.SortingCriterion
import com.acg.mangalive.myManga.domain.model.SortingParameters
import com.acg.mangalive.share.di.lazyViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MyMangaFragment : Fragment() {
    private var _binding: FragmentMyMangaBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: MyMangaViewModel.Factory

    val viewModel: MyMangaViewModel by lazyViewModel {
        viewModelFactory.create(it)
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
        _binding = FragmentMyMangaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoriesMenu = createCategoriesMenu()

        val adapter = MyMangaAdapter(requireContext())
        binding.favourites.adapter = adapter
        binding.favourites.layoutManager = GridLayoutManager(requireContext(), 2)
        categoriesMenu.setOnMenuItemClickListener {
            viewModel.setCategoryMenuState(convertCategoriesMenuItemIdToState(it.itemId))
            true
        }
        viewModel.uiState.observe(viewLifecycleOwner) {
            binding.CategoriesMenuBtn.setText(convertCategoriesMenuStateToValue(it.favouritesMenuState.criterion))
        }

        viewModel.favourites.observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle, it)
        }

        binding.CategoriesMenuBtn.setOnClickListener {
            categoriesMenu.show()
        }
    }

    private fun createCategoriesMenu() = PopupMenu(
        requireContext(), binding.CategoriesMenuBtn, Gravity.LEFT,
        androidx.appcompat.R.attr.popupMenuStyle,
        androidx.appcompat.R.style.Base_Widget_AppCompat_PopupMenu
    ).also {
        it.menuInflater.inflate(R.menu.my_manga_categories_menu, it.menu)
    }

    private fun convertCategoriesMenuItemIdToState(itemId: Int): SortingParameters =
        when (itemId) {
            R.id.FavouritesCategoriesMenu_Completed -> SortingParameters(
                SortingCriterion.Completed
            )
            R.id.FavouritesCategoriesMenu_Dropped -> SortingParameters(
                SortingCriterion.Dropped
            )
            R.id.FavouritesCategoriesMenu_CurrentlyReading -> SortingParameters(
                SortingCriterion.CurrentlyReading
            )
            R.id.FavouritesCategoriesMenu_OnHold -> SortingParameters(
                SortingCriterion.OnHold
            )
            R.id.FavouritesCategoriesMenu_PlanToRead -> SortingParameters(
                SortingCriterion.PlanToRead
            )
            else -> DEFAULT_SORTING_PARAMETERS_STATE
        }

    private fun convertCategoriesMenuStateToValue(state: SortingCriterion): Int =
        when (state) {
            SortingCriterion.CurrentlyReading -> R.string.categoryMenu_currently_reading
            SortingCriterion.Completed -> R.string.categoryMenu_completed
            SortingCriterion.Dropped -> R.string.categoryMenu_dropped
            SortingCriterion.OnHold -> R.string.categoryMenu_on_hold
            SortingCriterion.PlanToRead -> R.string.categoryMenu_plan_to_read
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}