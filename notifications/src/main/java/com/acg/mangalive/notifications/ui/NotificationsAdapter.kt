package com.acg.mangalive.notifications.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.acg.mangalive.notifications.databinding.CardNotificationsBinding
import com.acg.mangalive.notifications.domain.model.NotificationsCard
import com.acg.mangalive.share.R as shareR

class NotificationsAdapter(context: Context, private val visibilityChangeLiveData: LiveData<Boolean>) :
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

            visibilityChangeLiveData.observe(itemView.context as LifecycleOwner) {
                checkBox.isVisible = it
            }

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

    object DiffItemCallback : DiffUtil.ItemCallback<NotificationsCard>() {
        override fun areItemsTheSame(oldItem: NotificationsCard, newItem: NotificationsCard): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: NotificationsCard, newItem: NotificationsCard): Boolean =
            oldItem.id == newItem.id
    }
}