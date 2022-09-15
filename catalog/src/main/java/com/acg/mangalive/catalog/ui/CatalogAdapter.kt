package com.acg.mangalive.catalog.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.acg.mangalive.catalog.databinding.CardMangaBinding
import com.acg.mangalive.catalog.domain.model.MangaCard
import com.acg.mangalive.share.R as shareR

class CatalogAdapter(context: Context) :
    PagingDataAdapter<MangaCard, CatalogAdapter.ViewHolder>(DiffItemCallback) {

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

            if ((card?.id !!- 1.toLong()) !!% 3 == 0.toLong()) {
                image.setImageResource(shareR.drawable.manga_card_label)
            }

            if ((card?.id !!- 1.toLong()) !!% 3 == 1.toLong()) {
                image.setImageResource(shareR.drawable.manga_card_omniscient_reader)
            }

            if ((card?.id !!- 1.toLong()) !!% 3 == 2.toLong()) {
                image.setImageResource(shareR.drawable.manga_card_solo_leveling)
            }
        }
    }

    object DiffItemCallback : DiffUtil.ItemCallback<MangaCard>() {
        override fun areItemsTheSame(oldItem: MangaCard, newItem: MangaCard): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: MangaCard, newItem: MangaCard): Boolean =
            oldItem.id == newItem.id
    }
}