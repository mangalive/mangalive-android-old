package com.acg.mangalive.ui.catalog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.acg.mangalive.R
import com.acg.mangalive.databinding.CardMangaBinding
import com.acg.mangalive.domain.model.MangaCard

class CatalogMangaAdapter(context: Context) :
    PagingDataAdapter<MangaCard, CatalogMangaAdapter.ViewHolder>(DiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            CardMangaBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(private val binding: CardMangaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(card: MangaCard?) = with(binding) {
            title.text = card?.title
            description.text = card?.description
        }
    }

    object DiffItemCallback : DiffUtil.ItemCallback<MangaCard>() {
        override fun areItemsTheSame(oldItem: MangaCard, newItem: MangaCard): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: MangaCard, newItem: MangaCard): Boolean =
            oldItem.id == newItem.id
    }
}
