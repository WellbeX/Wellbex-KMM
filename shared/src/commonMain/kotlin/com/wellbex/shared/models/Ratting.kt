package com.wellbex.core.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Ratting(
    var question: String = "",
    var experienceRate: Int = 0,
    var recommendationsRate: Int = 0,
    var engagementRate: Int = 0,
    var doItAgain: Boolean = false,
    var doItAgainCount: Int = 0,
    var rateBy: String = "",
    var extraNote: String = ""
):Parcelable