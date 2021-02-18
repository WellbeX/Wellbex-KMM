package com.wellbex.core.models

import androidx.recyclerview.widget.DiffUtil

data class Discussion(
    var userId: String = "", var firstName: String = "",
    var lastName: String = "", var imageUrl: String = "",
    var comment: String = "",
    var timestamp: Long = 0L
) {
    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Discussion>() {
            override fun areItemsTheSame(oldItem: Discussion, newItem: Discussion): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Discussion, newItem: Discussion): Boolean {
                return newItem.timestamp == newItem.timestamp
            }

        }
    }
}