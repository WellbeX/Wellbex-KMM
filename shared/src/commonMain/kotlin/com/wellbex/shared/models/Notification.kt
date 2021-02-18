package com.wellbex.core.models

import androidx.recyclerview.widget.DiffUtil

data class  Notification(
    var title: String = "",
    var content: String = "",
    var eventId: String = "",
    @Transient
    var id: String = ""
) {
    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Notification>() {
            override fun areItemsTheSame(oldItem: Notification, newItem: Notification): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Notification, newItem: Notification): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }
}