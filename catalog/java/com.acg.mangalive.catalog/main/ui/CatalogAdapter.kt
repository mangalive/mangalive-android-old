package com.acg.mangalive.catalog.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.acg.mangalive.catalog.R
import com.acg.mangalive.catalog.databinding.CardMangaBinding
import com.acg.mangalive.catalog.domain.model.MangaCard
import com.acg.mangalive.share.R as shareR

class CatalogAdapter(private val context: Context) :
    PagingDataAdapter<MangaCard, CatalogAdapter.ViewHolder>(DiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        context, CardMangaBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))


    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.onDetach()
    }


    inner class ViewHolder(private val context: Context, private val binding: CardMangaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(card: MangaCard?) = with(binding) {
            card?.also {
                title.text = it.title
                rating.text = parseRating(it.rating)
                views.text = parseViewsNumber(it.views)
                chapters.text = it.chapters.toString()
                description.text = it.description
            }

            description.viewTreeObserver.addOnGlobalLayoutListener(::updateLinesNumber)

            if ((card?.id!! - 1.toLong())!! % 3 == 0.toLong()) {
                image.setImageResource(shareR.drawable.manga_card_label)
            }

            if ((card?.id!! - 1.toLong())!! % 3 == 1.toLong()) {
                image.setImageResource(shareR.drawable.manga_card_omniscient_reader)
            }

            if ((card?.id!! - 1.toLong())!! % 3 == 2.toLong()) {
                image.setImageResource(shareR.drawable.manga_card_solo_leveling)
            }
        }

        private fun parseViewsNumber(views: Int) = with(context.resources) {
            when (views) {
                in 0..999 -> getString(R.string.views_up_to_thousand, views)
                in 1000..999999 -> getString(R.string.views_up_to_million, views / 1000)
                else -> getString(R.string.views_up_to_billion, views / 1000000)
            }
        }

        private fun parseRating(rating: Float) =
            String
                .format("%.1f", rating)
                .replace(",", ".")


        private fun updateLinesNumber() = with(binding) {
            val freeSpace = image.bottom - description.top
            val linesNumber = freeSpace / description.lineHeight + 1
            description.setLines(linesNumber)
        }

        fun onDetach() = with(binding.description) {
            viewTreeObserver.removeOnGlobalLayoutListener(::updateLinesNumber)
        }

    }

    object DiffItemCallback : DiffUtil.ItemCallback<MangaCard>() {
        override fun areItemsTheSame(oldItem: MangaCard, newItem: MangaCard): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: MangaCard, newItem: MangaCard): Boolean =
            oldItem.id == newItem.id
    }
}