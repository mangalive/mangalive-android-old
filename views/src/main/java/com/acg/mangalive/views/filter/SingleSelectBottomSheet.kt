package com.acg.mangalive.views.filter

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.acg.mangalive.views.databinding.SingleSelectBottomSheetBinding
import com.acg.mangalive.views.databinding.SingleSelectBottomSheetItemBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SingleSelectBottomSheet(
    var title: String,
    var entries: List<String>,
) : BottomSheetDialogFragment() {

    interface OnEntrySelectListener {
        fun onEntrySelect(item: Entry)
    }

    private var binding: SingleSelectBottomSheetBinding? = null

    private var onEntrySelectCallback: ((Entry) -> Unit)? = null


    fun setOnEntrySelectListener(listener: (Entry) -> Unit) {
        onEntrySelectCallback = listener
    }

    fun setOnEntrySelectListener(listener: OnEntrySelectListener) {
        onEntrySelectCallback = listener::onEntrySelect
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = SingleSelectBottomSheetBinding
        .inflate(inflater, container, false).let {
            binding = it
            it.root
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.headline?.text = title
        binding?.recyclerView?.adapter = Adapter()
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
                SingleSelectBottomSheetItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ),
            )

        override fun onBindViewHolder(holder: ViewHolder, index: Int) =
            holder.bind(entries[index], index)

        override fun getItemCount(): Int = entries.size
    }

    inner class ViewHolder(
        private val binding: SingleSelectBottomSheetItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(value: String, index: Int) = with(binding) {
            text.text = value

            card.setOnClickListener {
                onEntrySelectCallback?.invoke(Entry(index, value))
                dismiss()
            }
        }
    }

    companion object {
        const val TAG = "SingleSelectBottomSheet"
    }
}