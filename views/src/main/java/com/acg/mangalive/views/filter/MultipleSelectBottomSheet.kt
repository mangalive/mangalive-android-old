package com.acg.mangalive.views.filter

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.acg.mangalive.views.databinding.MultipleSelectBottomSheetBinding
import com.acg.mangalive.views.databinding.MultipleSelectBottomSheetItemBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MultipleSelectBottomSheet(
    var title: String,
    var entriesValues: List<String>,
) : BottomSheetDialogFragment() {

    interface OnResetListener {
        fun onReset()
    }

    interface OnCloseListener {
        fun onClose(entries: List<Entry>)
    }

    private var binding: MultipleSelectBottomSheetBinding? = null

    private var isEntrySelectArray: Array<Boolean> = Array(entriesValues.size) { false }

    private var onResetCallback: (() -> Unit)? = null

    private var onCloseCallback: ((List<Entry>) -> Unit)? = null

    fun setOnResetListener(listener: () -> Unit) {
        onResetCallback = listener
    }

    fun setOnResetListener(listener: OnResetListener) {
        onResetCallback = listener::onReset
    }

    fun setOnCloseListener(listener: OnCloseListener) {
        onCloseCallback = listener::onClose
    }

    fun setOnCloseListener(listener: (List<Entry>) -> Unit) {
        onCloseCallback = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = MultipleSelectBottomSheetBinding
        .inflate(inflater, container, false).let {
            binding = it
            it.root
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.headline?.text = title

        binding?.recyclerView?.adapter = Adapter()

        binding?.resetBtn?.setOnClickListener {
            onResetCallback?.invoke()
            reset()
            dismiss()
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)

        val selectedEntries = isEntrySelectArray
            .asList()
            .mapIndexed { index, isSelected ->
                if (isSelected) Entry(index, entriesValues[index]) else null
            }
            .filterNotNull()

        onCloseCallback?.invoke(selectedEntries)
    }

    fun reset() {
        isEntrySelectArray = Array(entriesValues.size) { false }
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
                MultipleSelectBottomSheetItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ),
            )

        override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(entriesValues[position], position)

        override fun getItemCount(): Int = entriesValues.size
    }

    inner class ViewHolder(
        private val binding: MultipleSelectBottomSheetItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(value: String, position: Int) = with(binding) {
            text.text = value

            checkbox.isChecked = isEntrySelectArray[position]
            card.setOnClickListener {
                checkbox.isChecked = !checkbox.isChecked
                isEntrySelectArray[position] = checkbox.isChecked
            }
        }
    }

    companion object {
        const val TAG = "MultipleSelectBottomSheet"
    }
}