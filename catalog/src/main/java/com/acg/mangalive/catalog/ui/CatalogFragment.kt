package com.acg.mangalive.catalog.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.acg.mangalive.catalog.databinding.FragmentCatalogBinding
import com.acg.mangalive.share.di.lazyViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject
import com.acg.mangalive.navigation.R as navR

class CatalogFragment : Fragment() {
    private var _binding: FragmentCatalogBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: CatalogViewModel.Factory

    val viewModel: CatalogViewModel by lazyViewModel { stateHandle ->
        viewModelFactory.create(stateHandle)
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


        val adapter = CatalogAdapter(requireContext())

        binding.catalog.adapter = adapter

        binding.NotificationsBtn.setOnClickListener {
            findNavController().navigate(navR.id.NavGraph_Notifications)
        }

//        sortingMenu.setOnMenuItemClickListener {
//            viewModel.setSortingCriterion(convertMenuItemIdToSortingCriterion(it.itemId))
//            true
//        }

//        viewModel.sortingParameters.observe(viewLifecycleOwner) {
//            binding.SortingMenuBtn.setText(convertSortingCriterionToValue(it.criterion))
//        }

        viewModel.catalog.observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle, it)
        }

//        binding.SortingMenuBtn.setOnClickListener {
//            sortingMenu.show()
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
