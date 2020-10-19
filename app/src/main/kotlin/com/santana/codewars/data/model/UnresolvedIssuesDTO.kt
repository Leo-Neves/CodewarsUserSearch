package com.santana.codewars.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UnresolvedIssuesDTO(
    @SerializedName("issues") val issues: Int,
    @SerializedName("suggestions") val suggestions: Int
) : Parcelable