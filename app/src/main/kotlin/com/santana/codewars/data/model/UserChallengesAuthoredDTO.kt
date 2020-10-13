package com.santana.codewars.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.santana.codewars.domain.model.ChallengesAuthoredBO
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserChallengesAuthoredDTO (
    @SerializedName("data") val data: List<ChallengesAuthoredDTO>
): Parcelable