package com.acg.mangalive.ui.notifications

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.paging.map
import com.acg.mangalive.R
import com.acg.mangalive.databinding.FragmentNotificationsBinding
import com.acg.mangalive.domain.model.SortingCriterionNotifications
import com.acg.mangalive.domain.model.SortingParametersNotifications
import com.acg.mangalive.ui.catalog.CatalogMangaAdapter
import com.acg.mangalive.viewModel.*
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class NotificationsFragment : Fragment() {
    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel: NotificationsViewModel by viewModels {
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
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val notificationsMenu = createNotificationsMenu()

        val adapter = NotificationsAdapter(requireContext())

        binding.notifications.adapter = adapter

        notificationsMenu.setOnMenuItemClickListener {
            viewModel.setSortingCriterion(convertNotificationsMenuItemIdToState(it.itemId))
            true
        }

        viewModel.sortingParameters.observe(viewLifecycleOwner) {
            binding.NotificationsMenuBtn.setText(convertNotificationsMenuStateToValue(it.criterion))
        }

        viewModel.notifications.observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle, it)
        }

        binding.NotificationsMenuBtn.setOnClickListener {
            notificationsMenu.show()
        }

//        binding.NavBackBtn.setOnClickListener {
//
//        }
    }

    private fun createNotificationsMenu() = PopupMenu(
        requireContext(), binding.NotificationsMenuBtn, Gravity.LEFT,
        androidx.appcompat.R.attr.popupMenuStyle,
        androidx.appcompat.R.style.Base_Widget_AppCompat_PopupMenu
    ).also {
        it.menuInflater.inflate(R.menu.notifications_menu, it.menu)
    }

    private fun convertNotificationsMenuItemIdToState(itemId: Int): SortingCriterionNotifications = when (itemId) {
        R.id.NotificationsMenu_All -> SortingCriterionNotifications.All
        R.id.NotificationsMenu_ForToday -> SortingCriterionNotifications.ForToday
        R.id.NotificationsMenu_ThisWeek -> SortingCriterionNotifications.ThisWeek
        R.id.NotificationsMenu_Released -> SortingCriterionNotifications.Released
        R.id.NotificationsMenu_Answers -> SortingCriterionNotifications.Answers
        else -> DEFAULT_SORTING_CRITERION_NOTIFICATIONS
    }

    private fun convertNotificationsMenuStateToValue(state: SortingCriterionNotifications): Int = when (state) {
        SortingCriterionNotifications.All -> R.string.notificationsMenu_all
        SortingCriterionNotifications.ForToday -> R.string.notificationsMenu_for_today
        SortingCriterionNotifications.ThisWeek -> R.string.notificationsMenu_this_week
        SortingCriterionNotifications.Released -> R.string.notificationsMenu_released
        SortingCriterionNotifications.Answers -> R.string.notificationsMenu_answers
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}