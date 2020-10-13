package com.santana.codewars.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TotalChallengesDTO(
    @SerializedName("totalAuthored") val totalAuthored: Int,
    @SerializedName("totalCompleted") val totalCompleted: Int
): Parcelable