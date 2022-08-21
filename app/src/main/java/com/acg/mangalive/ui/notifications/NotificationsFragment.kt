package com.acg.mangalive.ui.notifications

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
import com.acg.mangalive.R
import com.acg.mangalive.databinding.FragmentNotificationsBinding
import com.acg.mangalive.viewModel.NotificationsMenuState
import com.acg.mangalive.viewModel.DEFAULT_NOTIFICATIONS_MENU_STATE
import com.acg.mangalive.viewModel.FavouritesViewModel
import com.acg.mangalive.viewModel.NotificationsViewModel
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

        notificationsMenu.setOnMenuItemClickListener {
            viewModel.setNotificationsMenuState(convertNotificationsMenuItemIdToState(it.itemId))
            true
        }

        viewModel.uiState.observe(viewLifecycleOwner) {
            binding.NotificationsMenuBtn.setText(convertNotificationsMenuStateToValue(it.notificationsMenuState))
        }

        binding.NotificationsMenuBtn.setOnClickListener {
            notificationsMenu.show()
        }

        binding.NavBackBtn.setOnClickListener {
            fragmentManager?.popBackStack()
        }
    }

    private fun createNotificationsMenu() = PopupMenu(
        requireContext(), binding.NotificationsMenuBtn, Gravity.LEFT,
        androidx.appcompat.R.attr.popupMenuStyle,
        androidx.appcompat.R.style.Base_Widget_AppCompat_PopupMenu
    ).also {
        it.menuInflater.inflate(R.menu.notifications_menu, it.menu)
    }

    private fun convertNotificationsMenuItemIdToState(itemId: Int): NotificationsMenuState = when (itemId) {
        R.id.NotificationsMenu_All -> NotificationsMenuState.All
        R.id.NotificationsMenu_ForToday -> NotificationsMenuState.ForToday
        R.id.NotificationsMenu_ThisWeek -> NotificationsMenuState.ThisWeek
        R.id.NotificationsMenu_Released -> NotificationsMenuState.Released
        R.id.NotificationsMenu_Answers -> NotificationsMenuState.Answers
        else -> DEFAULT_NOTIFICATIONS_MENU_STATE
    }

    private fun convertNotificationsMenuStateToValue(state: NotificationsMenuState): Int = when (state) {
        NotificationsMenuState.All -> R.string.notificationsMenu_all
        NotificationsMenuState.ForToday -> R.string.notificationsMenu_for_today
        NotificationsMenuState.ThisWeek -> R.string.notificationsMenu_this_week
        NotificationsMenuState.Released -> R.string.notificationsMenu_released
        NotificationsMenuState.Answers -> R.string.notificationsMenu_answers
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}