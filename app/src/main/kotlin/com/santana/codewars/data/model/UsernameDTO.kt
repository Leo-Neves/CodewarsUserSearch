package com.santana.codewars.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UsernameDTO (
    @SerializedName("username") val username: String,
    @SerializedName("url") val url: String
): Parcelable