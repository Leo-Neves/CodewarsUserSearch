package com.santana.codewars.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RankBO (
    @SerializedName("id") val id: String? = null,
    @SerializedName("languageName") val languageName: String? = null,
    @SerializedName("rank") val rank: Int = 0,
    @SerializedName("name") val name: String,
    @SerializedName("color") val color: String,
    @SerializedName("score") val score: Int = 0
):Parcelable