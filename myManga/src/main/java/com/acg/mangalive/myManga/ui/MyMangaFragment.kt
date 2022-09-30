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

        // set the references of the declared objects above
        val pager = binding.pager
        val tab = binding.tabLayout

        val adapter = MyMangaPagerAdapter(childFragmentManager)

        // add fragment to the list
        adapter.addFragment(CompletedFragment())
        adapter.addFragment(DroppedFragment())
        adapter.addFragment(OnHoldFragment())
        adapter.addFragment(PlanToReadFragment())
        adapter.addFragment(ReadingFragment())

        pager.adapter = adapter

        // bind the viewPager with the TabLayout.
        tab.setupWithViewPager(pager)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}