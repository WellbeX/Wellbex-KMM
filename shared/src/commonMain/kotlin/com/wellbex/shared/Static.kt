package com.wellbex.shared

sealed class EventsStatus(val id: String = "", val name: String = "") {
    object Upcoming : EventsStatus("eventUpcomingId", "Upcoming")
    object Happening : EventsStatus("eventHappeningId", "Happening")
    object Past : EventsStatus("eventPastId", "Past")
    companion object {
        fun fromString(id: String): EventsStatus {
            return when (id) {
                Upcoming.id -> Upcoming
                Happening.id -> Happening
                Past.id -> Past
                else -> Upcoming
            }
        }
    }

}


sealed class AttendeeStatus(val id: String = "", val name: String = "") {
    object Invited : AttendeeStatus("invitedId", "Not Going")
    object Going : AttendeeStatus("goingId", "Going")
    companion object {
        fun fromString(id: String): AttendeeStatus {
            return when (id) {
                Invited.id -> Invited
                Going.id -> Going
                else -> Invited
            }
        }
    }

    fun reverse(): AttendeeStatus {
        return if (this is Invited)
            Going
        else
            Invited
    }
}

sealed class EventType(val id: String = "", val name: String = "") {
    object Private : EventType("privateId", "Private")
    object Public : EventType("publicId", "Public")
    companion object {
        fun fromString(id: String): EventType {
            return when (id) {
                Private.id -> Private
                Public.id -> Public
                else -> Public
            }
        }
    }
}
