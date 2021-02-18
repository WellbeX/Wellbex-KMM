package com.wellbex.core.models

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.FirebaseFirestore
import com.wellbex.shared.AttendeeStatus
import com.wellbex.core.Constants
import com.wellbex.shared.EventType
import com.wellbex.shared.EventsStatus
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
    @get:Exclude
    var eventId: String = "",
    var eventName: String = "",
    var eventLocation: Location = Location(),
    var startingDate: Long = 0,
    var endDate: Long = 0,
    var eventPhotos: MutableList<String> = mutableListOf(),
    var eventType: String = EventType.Public.id,
    var eventDetails: String = "",
    var eventLimit: Int = -1,
    var eventsStatus: String = EventsStatus.Upcoming.id,
    var organizedBy: String = "",
    var attendeesIds: MutableList<String> = mutableListOf(),
    var activatesIds: MutableList<String> = mutableListOf(),
    var ratting: MutableList<Ratting> = mutableListOf(),
    var companyId: String = "",
    var isRecurring: Boolean = false,
    var recurringPeriod: Int = -1,
    var recurringList: List<Long> = listOf()
) : Parcelable {
    @Parcelize
    data class Location(var lat: Double = 0.0, var lng: Double = 0.0, var address: String = "") :
        Parcelable

    @Parcelize
    data class Attendant(
        var id: String = "",
        var attendeeStatus: String = AttendeeStatus.Invited.id
    ) : Parcelable

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Event>() {
            override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
                return oldItem.eventId == newItem.eventId
            }

            override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
                return oldItem == newItem
            }

        }
        val EVERY_DAY = 1
        val EVERY_WEEK = 2
        val EVERY_MONTH = 3
    }

    fun getUserAttendanceStatus(userId: String, callback: (Attendant) -> Unit) {
        FirebaseFirestore.getInstance()
            .collection(Constants.EVENTS)
            .document(eventId)
            .collection("attendees")
            .whereEqualTo("id", userId)
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                querySnapshot?.let {
                    if (it.isEmpty) {
                        callback(Attendant(userId))
                    } else {
                        callback(it.toObjects(Attendant::class.java).first())
                    }
                }
                firebaseFirestoreException?.printStackTrace()
            }
    }
}
