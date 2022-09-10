package com.acg.mangalive.ui.catalog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.acg.mangalive.databinding.*
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CatalogBottomSheet(
    private val title: String,
    private val items: List<String>,
    private val isSingleSelect: Boolean,
) : BottomSheetDialogFragment() {
    data class Item(
        val position: Int,
        val value: String,
    )

    interface OnItemSelectListener {
        fun onItemSelect(item: Item)
    }

    interface OnResetListener {
        fun onReset()
    }

    interface OnItemsSelectListener {
        fun onItemsSelect(items: List<Item>)
    }

    private var _binding: FragmentCatalogBottomSheetBinding? = null
    private val binding get() = _binding!!

    private var isItemSelectedArray: Array<Boolean> = Array(items.size) { false }

    private var onItemSelectCallback: ((Item) -> Unit)? = null

    private var onResetCallback: (() -> Unit)? = null

    private var onItemsSelectCallback: ((List<Item>) -> Unit)? = null

    fun setOnItemSelectListener(listener: (Item) -> Unit) {
        onItemSelectCallback = listener
    }

    fun setOnItemSelectListener(listener: OnItemSelectListener) {
        onItemSelectCallback = listener::onItemSelect
    }

    fun setOnResetListener(listener: () -> Unit) {
        onResetCallback = listener
    }

    fun setOnResetListener(listener: OnResetListener) {
        onResetCallback = listener::onReset
    }

    fun setOnItemsSelectListener(listener: OnItemsSelectListener) {
        onItemsSelectCallback = listener::onItemsSelect
    }

    fun setOnItemsSelectListener(listener: (List<Item>) -> Unit) {
        onItemsSelectCallback = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentCatalogBottomSheetBinding.inflate(inflater, container, false).let {
        _binding = it

        binding.headline.text = title
        if (isSingleSelect) {
            binding.resetBtn.visibility = View.INVISIBLE
            binding.resetBtn.isClickable = false
        }

        it.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = Adapter()
        binding.recyclerView.adapter = adapter

        binding.resetBtn.setOnClickListener {
            onResetCallback?.invoke()
            reset()
            dismiss()
        }

    }

    override fun onDismiss(dialog: DialogInterface) {
        val selectedItems = mutableListOf<Item>()

        for (i in isItemSelectedArray.indices) {
            if (!isItemSelectedArray[i]) continue
            selectedItems.add(Item(i, items[i]))
        }

        onItemsSelectCallback?.invoke(selectedItems)

        super.onDismiss(dialog)
    }

    fun reset() {
        for (i in isItemSelectedArray.indices) {
            isItemSelectedArray[i] = false
        }
    }

    @SuppressLint("VisibleForTests")
    @Suppress("RestrictedApi")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        bottomSheetDialog.behavior.disableShapeAnimations()
        return bottomSheetDialog
    }

    inner class Adapter : RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(
                CatalogBottomSheetItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ),
            )

        override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(items[position], position)


        override fun getItemCount(): Int = items.size
    }

    inner class ViewHolder(
        private val binding: CatalogBottomSheetItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(value: String, position: Int) = with(binding) {
            text.text = value

            if (isSingleSelect) {
                bindSingleSelect(Item(position, value), binding)
            } else {
                bindNonSingleSelect(position, binding)
            }
        }

        private fun bindSingleSelect(item: Item, binding: CatalogBottomSheetItemBinding) =
            with(binding) {
                checkbox.isVisible = false
                checkbox.isClickable = false

                card.setOnClickListener {
                    onItemSelectCallback?.invoke(
                        item
                    )

                    dismiss()
                }
            }

        private fun bindNonSingleSelect(position: Int, binding: CatalogBottomSheetItemBinding) =
            with(binding) {
                binding.checkbox.isChecked = isItemSelectedArray[position]
                card.setOnClickListener {
                    checkbox.isChecked = !checkbox.isChecked
                    isItemSelectedArray[position] = checkbox.isChecked
                }
            }
    }

    companion object {
        const val TAG = "CatalogBottomSheet"
    }
}

