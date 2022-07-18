package com.acg.mangalive.ui.favourites

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.view.*
import android.widget.Button
import com.acg.mangalive.R
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.acg.mangalive.databinding.FragmentFavouritesBinding

class FavouritesFragment : Fragment() {

    private var _binding: FragmentFavouritesBinding? = null
    private var _categoryMenuState : Int = 0
    private var categoryMenuState
    get() = _categoryMenuState
    set(value) {
        _categoryMenuState = value
        binding.categoryMenuButton.setText(value)
    }
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val favouriteViewModel =
            ViewModelProvider(this)[FavouritesViewModel::class.java]

        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        categoryMenuState = R.string.categoryMenu_currently_reading

        binding.categoryMenuButton.setOnClickListener {
            showPopUp(it)
        }

        return root
    }

    fun showPopUp(view: View) {
        val popupMenu = context?.let { PopupMenu(it, view) }
        val inflater = popupMenu?.menuInflater
        inflater?.inflate(R.menu.category_menu, popupMenu.menu)
        popupMenu?.show()

        popupMenu?.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.categoriesMenu_currentlyReading -> {
                    categoryMenuState = R.string.categoryMenu_currently_reading
                }
                R.id.categoriesMenu_planToRead -> {
                    categoryMenuState = R.string.categoryMenu_plan_to_read
                }
                R.id.categoriesMenu_onHold -> {
                    categoryMenuState = R.string.categoryMenu_on_hold
                }
                R.id.categoriesMenu_completed -> {
                    categoryMenuState = R.string.categoryMenu_completed
                }
                R.id.categoriesMenu_dropped -> {
                    categoryMenuState = R.string.categoryMenu_dropped
                }
            }
            true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}