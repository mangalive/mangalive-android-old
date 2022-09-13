package com.acg.mangalive.catalog.ui.bottomSheets

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.acg.mangalive.catalog.databinding.FragmentSingleSelectBottomSheetBinding
import com.acg.mangalive.catalog.databinding.SingleSelectBottomSheetItemBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SingleSelectBottomSheet(
    private val title: String,
    private val items: List<String>,
) : BottomSheetDialogFragment() {
    interface OnItemSelectListener {
        fun onItemSelect(item: SelectBottomSheetItem)
    }

    private var _binding: FragmentSingleSelectBottomSheetBinding? = null
    private val binding get() = _binding!!

    private var onItemSelectCallback: ((SelectBottomSheetItem) -> Unit)? = null


    fun setOnItemSelectListener(listener: (SelectBottomSheetItem) -> Unit) {
        onItemSelectCallback = listener
    }

    fun setOnItemSelectListener(listener: OnItemSelectListener) {
        onItemSelectCallback = listener::onItemSelect
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentSingleSelectBottomSheetBinding
        .inflate(inflater, container, false).let {
            _binding = it
            it.root
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.headline.text = title

        val adapter = Adapter()
        binding.recyclerView.adapter = adapter
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

        override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(items[position], position)

        override fun getItemCount(): Int = items.size
    }

    inner class ViewHolder(
        private val binding: SingleSelectBottomSheetItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(value: String, position: Int) = with(binding) {
            text.text = value

            card.setOnClickListener {
                onItemSelectCallback?.invoke(SelectBottomSheetItem(position, value))
                dismiss()
            }
        }
    }

    companion object {
        const val TAG = "SingleSelectBottomSheet"
    }
}