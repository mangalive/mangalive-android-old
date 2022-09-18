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
import com.acg.mangalive.share.di.Factory
import com.acg.mangalive.share.di.lazyViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

inline fun <reified T : ViewModel> Fragment.lazyViewModel(
    noinline ownerProducer: (() -> ViewModelStoreOwner),
    noinline factoryProducer: (stateHandle: SavedStateHandle) -> T,
) = viewModels<T>(ownerProducer, null) {
    Factory(this, factoryProducer)
}

class NotificationsBottomSheet : BottomSheetDialogFragment() {
    interface OnItemSelectListener {
        fun onItemSelect(item: SelectBottomSheetItem)
    }

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

        

    }
}