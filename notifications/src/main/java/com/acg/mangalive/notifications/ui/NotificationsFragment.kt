package com.acg.mangalive.notifications.ui

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.acg.mangalive.notifications.R
import com.acg.mangalive.notifications.databinding.FragmentNotificationsBinding
import com.acg.mangalive.notifications.domain.model.SortingCriterionNotifications
import com.acg.mangalive.share.di.lazyViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject
import com.acg.mangalive.navigation.R as navR

class NotificationsFragment : Fragment() {
    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    private var checkBoxVisibilityChange = MutableLiveData(false)

    @Inject
    lateinit var viewModelFactory: NotificationsViewModel.Factory

    private val viewModel: NotificationsViewModel by lazyViewModel {
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
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val notificationsMenu = createNotificationsMenu()
        val selectNotificationsMenu = createSelectNotificationsMenu()

        val adapter = NotificationsAdapter(requireContext(), checkBoxVisibilityChange)

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

        binding.SelectMenuNotificationsBtn.setOnClickListener {
            selectNotificationsMenu.show()
        }

        selectNotificationsMenu.setOnMenuItemClickListener {
            if (it.itemId == R.id.SelectNotificationsMenu_Select) {
                checkBoxVisibilityChange.value = true
                binding.NavBackBtn.isVisible = false
                binding.SelectionCloseBtn.isVisible = true
            }
            true
        }

        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (checkBoxVisibilityChange.value == true) {
                    checkBoxVisibilityChange.value = false
                    binding.NavBackBtn.isVisible = true
                    binding.SelectionCloseBtn.isVisible = false
                } else {
                    findNavController().navigate(navR.id.NavGraph_Catalog)
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            onBackPressedCallback
        )

        binding.NavBackBtn.setOnClickListener {
            findNavController().navigate(navR.id.NavGraph_Catalog)
        }

        binding.SelectionCloseBtn.setOnClickListener {
            checkBoxVisibilityChange.value = false
            binding.NavBackBtn.isVisible = true
            binding.SelectionCloseBtn.isVisible = false
        }

        binding.NotificationsMenuBtn.setOnClickListener {
            notificationsMenu.show()
        }
    }


    private fun createNotificationsMenu() = PopupMenu(
        requireContext(), binding.NotificationsMenuBtn, Gravity.LEFT,
        androidx.appcompat.R.attr.popupMenuStyle,
        androidx.appcompat.R.style.Base_Widget_AppCompat_PopupMenu
    ).also {
        it.menuInflater.inflate(R.menu.notifications_menu, it.menu)
    }

    private fun createSelectNotificationsMenu() = PopupMenu(
        requireContext(), binding.SelectMenuNotificationsBtn, Gravity.RIGHT,
        androidx.appcompat.R.attr.popupMenuStyle,
        androidx.appcompat.R.style.Base_Widget_AppCompat_PopupMenu
    ).also {
        it.menuInflater.inflate(R.menu.select_notifications_menu, it.menu)
    }

    private fun convertNotificationsMenuItemIdToState(itemId: Int): SortingCriterionNotifications =
        when (itemId) {
            R.id.NotificationsMenu_All -> SortingCriterionNotifications.All
            R.id.NotificationsMenu_ForToday -> SortingCriterionNotifications.ForToday
            R.id.NotificationsMenu_ThisWeek -> SortingCriterionNotifications.ThisWeek
            R.id.NotificationsMenu_Released -> SortingCriterionNotifications.Released
            R.id.NotificationsMenu_Answers -> SortingCriterionNotifications.Answers
            else -> DEFAULT_SORTING_CRITERION_NOTIFICATIONS
        }

    private fun convertNotificationsMenuStateToValue(state: SortingCriterionNotifications): Int =
        when (state) {
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
