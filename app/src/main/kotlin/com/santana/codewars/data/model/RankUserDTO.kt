package com.santana.codewars.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RankUserDTO (
    @SerializedName("overall") val overall: RankDTO,
    @SerializedName("languages") val languages: Map<String, RankDTO>
): Parcelable