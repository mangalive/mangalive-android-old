package com.acg.mangalive.catalog.ui.bottomSheets

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.acg.mangalive.catalog.databinding.FragmentNonSingleSelectBottomSheetBinding
import com.acg.mangalive.catalog.databinding.NonSingleSelectBottomSheetItemBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NonSingleSelectBottomSheet(
    private val title: String,
    private val items: List<String>,
) : BottomSheetDialogFragment() {
    interface OnResetListener {
        fun onReset()
    }

    interface OnCloseListener {
        fun onCloseSelect(items: List<SelectBottomSheetItem>): Unit
    }

    private var _binding: FragmentNonSingleSelectBottomSheetBinding? = null
    private val binding get() = _binding!!

    private var isItemSelectedArray: Array<Boolean> = Array(items.size) { false }

    private var onResetCallback: (() -> Unit)? = null

    private var onCloseCallback: ((List<SelectBottomSheetItem>) -> Unit)? = null

    fun setOnResetListener(listener: () -> Unit) {
        onResetCallback = listener
    }

    fun setOnResetListener(listener: OnResetListener) {
        onResetCallback = listener::onReset
    }

    fun setOnCloseListener(listener: OnCloseListener) {
        onCloseCallback = listener::onCloseSelect
    }

    fun setOnCloseListener(listener: (List<SelectBottomSheetItem>) -> Unit) {
        onCloseCallback = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentNonSingleSelectBottomSheetBinding
        .inflate(inflater, container, false).let {
            _binding = it
            it.root
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.headline.text = title

        val adapter = Adapter()
        binding.recyclerView.adapter = adapter

        binding.resetBtn.setOnClickListener {
            onResetCallback?.invoke()
            reset()
            dismiss()
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        val selectedItems = mutableListOf<SelectBottomSheetItem>()

        for (i in isItemSelectedArray.indices) {
            if (!isItemSelectedArray[i]) continue
            selectedItems.add(SelectBottomSheetItem(i, items[i]))
        }

        onCloseCallback?.invoke(selectedItems)

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
                NonSingleSelectBottomSheetItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ),
            )

        override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(items[position], position)


        override fun getItemCount(): Int = items.size
    }

    inner class ViewHolder(
        private val binding: NonSingleSelectBottomSheetItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(value: String, position: Int) = with(binding) {
            text.text = value

            checkbox.isChecked = isItemSelectedArray[position]
            card.setOnClickListener {
                checkbox.isChecked = !checkbox.isChecked
                isItemSelectedArray[position] = checkbox.isChecked
            }
        }
    }

    companion object {
        const val TAG = "NonSingleSelectBottomSheet"
    }
}