package com.acg.mangalive.catalog.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.acg.mangalive.catalog.R
import com.acg.mangalive.catalog.databinding.FragmentCatalogFilterBinding
import javax.inject.Inject


class CatalogFilterFragment : Fragment() {
    private var _binding: FragmentCatalogFilterBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var sortingCriterionFilter: CatalogSingleSelectFilter

    private val viewModel: CatalogViewModel by viewModels(
        { requireParentFragment() },
        null,
        { viewModelFactory },
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatalogFilterBinding.inflate(inflater, container, false)

        sortingCriterionFilter = CatalogSingleSelectFilter.newInstance(
            R.string.sortingCriterion_title,
            R.array.sortingCriterion_items,
            0
        )

        parentFragmentManager
            .beginTransaction()
            .add(
                binding.sortingCriterion.id,
                sortingCriterionFilter,
                CatalogSingleSelectFilter.TAG
            )
            .add(
                binding.type.id,
                CatalogNonSingleSelectFilter.newInstance(
                    R.string.type_title,
                    R.array.type_items,
                    R.string.type_plural_title
                ),
                CatalogNonSingleSelectFilter.TAG
            )
            .add(
                binding.genre.id,
                CatalogNonSingleSelectFilter.newInstance(
                    R.string.genre_title,
                    R.array.genre_items,
                    R.string.genre_plural_title
                )
            )
            .add(
                binding.category.id,
                CatalogNonSingleSelectFilter.newInstance(
                    R.string.category_title,
                    R.array.category_items,
                    R.string.category_plural_title,
                )
            )
            .commit()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}