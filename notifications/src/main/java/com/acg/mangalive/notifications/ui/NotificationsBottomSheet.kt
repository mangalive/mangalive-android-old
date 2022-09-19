package com.acg.mangalive.notifications.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.acg.mangalive.catalog.ui.bottomSheets.SelectBottomSheetItem
import com.acg.mangalive.notifications.databinding.BottomSheetBinding
import com.acg.mangalive.notifications.domain.model.SortingCriterionNotifications
import com.acg.mangalive.share.di.Factory
import com.acg.mangalive.share.di.lazyViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.AndroidInjection.inject
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

inline fun <reified T : ViewModel> Fragment.lazyViewModel(
    noinline ownerProducer: (() -> ViewModelStoreOwner),
    noinline factoryProducer: (stateHandle: SavedStateHandle) -> T,
) = viewModels<T>(ownerProducer, null) {
    Factory(this, factoryProducer)
}

class NotificationsBottomSheet : BottomSheetDialogFragment() {

    private var binding: BottomSheetBinding? = null

    @Inject
    lateinit var viewModelFactory: NotificationsViewModel.Factory

    private val viewModel: NotificationsViewModel by lazyViewModel ({requireParentFragment()} ,{
        viewModelFactory.create(it)
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetBinding.inflate(inflater, container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.all?.setOnClickListener {
            viewModel.updateSortingParameters(SortingCriterionNotifications.All)
        }

        binding?.answers?.setOnClickListener {
            viewModel.updateSortingParameters(SortingCriterionNotifications.Answers)
        }

        binding?.forToday?.setOnClickListener {
            viewModel.updateSortingParameters(SortingCriterionNotifications.ForToday)
        }

        binding?.released?.setOnClickListener {
            viewModel.updateSortingParameters(SortingCriterionNotifications.Released)
        }

        binding?.thisWeek?.setOnClickListener {
            viewModel.updateSortingParameters(SortingCriterionNotifications.ThisWeek)
        }
    }

    companion object {
        const val TAG = "NotificationsBottomSheet"
    }
}