package com.acg.mangalive.catalog.ui.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.acg.mangalive.catalog.R
import com.acg.mangalive.catalog.databinding.FragmentCatalogFilterBinding
import com.acg.mangalive.share.di.lazyViewModel
import javax.inject.Inject


class FilterFragment : Fragment() {
    private var _binding: FragmentCatalogFilterBinding? = null
    private val binding get() = _binding!!

    lateinit var sortingCriterionFilter: SingleSelectFilter

    @Inject
    lateinit var viewModelFactory: FilterViewModel.Factory

    val viewModel: FilterViewModel by lazyViewModel {
        viewModelFactory.create(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatalogFilterBinding.inflate(inflater, container, false)

        sortingCriterionFilter = SingleSelectFilter.newInstance(
            "chip",
            R.string.sortingCriterion_title,
            R.array.sortingCriterion_items,
            0
        )

        parentFragmentManager
            .beginTransaction()
            .add(
                binding.sortingCriterion.id,
                sortingCriterionFilter,
                SingleSelectFilter.TAG
            )
            .add(
                binding.type.id,
                NonSingleSelectFilter.newInstance(
                    R.string.type_title,
                    R.array.type_items,
                    R.string.type_plural_title
                ),
                NonSingleSelectFilter.TAG
            )
            .add(
                binding.genre.id,
                NonSingleSelectFilter.newInstance(
                    R.string.genre_title,
                    R.array.genre_items,
                    R.string.genre_plural_title
                )
            )
            .add(
                binding.category.id,
                NonSingleSelectFilter.newInstance(
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