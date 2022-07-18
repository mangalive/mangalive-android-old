package com.acg.mangalive.ui.catalog

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.acg.mangalive.R
import com.acg.mangalive.databinding.FragmentCatalogBinding

class CatalogFragment : Fragment() {

    private var _binding: FragmentCatalogBinding? = null
    private var _sortingMenuState: Int = 0
    private var sortingMenuState
        get() = _sortingMenuState
        set(value) {
            _sortingMenuState = value
            binding.sortingMenuButton.setText(value)
        }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val catalogViewModel =
            ViewModelProvider(this)[CatalogViewModel::class.java]

        _binding = FragmentCatalogBinding.inflate(inflater, container, false)
        val root: View = binding.root

        sortingMenuState = R.string.sortingMenu_popularity
        val popupMenu = createPopUp()

        binding.sortingMenuButton.setOnClickListener() {
            popupMenu?.show()
        }

        return root
    }

    fun createPopUp(): PopupMenu? {
        val popupMenu = context?.let {
            PopupMenu(
                it,
                binding.sortingMenuButton,
                Gravity.END,
                androidx.appcompat.R.attr.popupMenuStyle,
                androidx.appcompat.R.attr.popupMenuStyle
            )
        }
        val inflater = popupMenu?.menuInflater
        inflater?.inflate(R.menu.sorting_menu, popupMenu.menu)

        popupMenu?.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.sortingMenu_popularity -> {
                    sortingMenuState = R.string.sortingMenu_popularity
                }
                R.id.sortingMenu_likes -> {
                    sortingMenuState = R.string.sortingMenu_likes
                }
                R.id.sortingMenu_views -> {
                    sortingMenuState = R.string.sortingMenu_views
                }
                R.id.sortingMenu_chapterCount -> {
                    sortingMenuState = R.string.sortingMenu_chapter_count
                }
                R.id.sortingMenu_novelty -> {
                    sortingMenuState = R.string.sortingMenu_novelty
                }
                R.id.sortingMenu_recentUpdates -> {
                    sortingMenuState = R.string.sortingMenu_recent_updates
                }
                R.id.sortingMenu_random -> {
                    sortingMenuState = R.string.sortingMenu_random
                }
            }
            true
        }

        return popupMenu
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}