package com.acg.mangalive.myManga.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.acg.mangalive.myManga.databinding.FragmentOnHoldBinding
import com.acg.mangalive.myManga.databinding.FragmentPlanToReadBinding
import com.acg.mangalive.share.di.lazyViewModel
import javax.inject.Inject

class PlanToReadFragment : Fragment() {
    private var _binding: FragmentPlanToReadBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: MyMangaViewModel.Factory

    val viewModel: MyMangaViewModel by lazyViewModel {
        viewModelFactory.create(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlanToReadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val adapter = MyMangaAdapter(requireContext())
        binding.favourites.adapter = adapter
        binding.favourites.layoutManager = GridLayoutManager(requireContext(), 2)

        viewModel.favourites.observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle, it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}