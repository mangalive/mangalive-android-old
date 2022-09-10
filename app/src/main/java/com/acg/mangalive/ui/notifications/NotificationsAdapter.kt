package com.acg.mangalive.ui.notifications

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acg.mangalive.databinding.FragmentNotificationsBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.acg.mangalive.R
import com.acg.mangalive.databinding.CardNotificationsBinding
import com.acg.mangalive.domain.model.NotificationsCard

class NotificationsAdapter(context: Context) :
    PagingDataAdapter<NotificationsCard, NotificationsAdapter.ViewHolder>(DiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            CardNotificationsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(private val binding: CardNotificationsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceType")
        fun bind(card: NotificationsCard?) : Unit = with(binding) {
            title.text = card?.title + " добавлена новая глава " + card?.chapter.toString()  + " в платном доступе"
            date.text = card?.date.toString()
            if ((card?.id !!- 1.toLong()) !!% 3 == 0.toLong()) {
                image.setImageResource(R.drawable.manga_card_label)
            }

            if ((card?.id !!- 1.toLong()) !!% 3 == 1.toLong()) {
                image.setImageResource(R.drawable.manga_card_omniscient_reader)
            }

            if ((card?.id !!- 1.toLong()) !!% 3 == 2.toLong()) {
                image.setImageResource(R.drawable.manga_card_solo_leveling)
            }
        }
    }

    object DiffItemCallback : DiffUtil.ItemCallback<NotificationsCard>() {
        override fun areItemsTheSame(oldItem: NotificationsCard, newItem: NotificationsCard): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: NotificationsCard, newItem: NotificationsCard): Boolean =
            oldItem.id == newItem.id
    }
}
