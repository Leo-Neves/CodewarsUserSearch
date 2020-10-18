package com.santana.codewars.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TotalChallengesBO(
    @SerializedName("totalAuthored") val totalAuthored: Int,
    @SerializedName("totalCompleted") val totalCompleted: Int
):Parcelable