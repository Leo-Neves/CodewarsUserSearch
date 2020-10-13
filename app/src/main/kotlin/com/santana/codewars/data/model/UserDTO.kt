package com.santana.codewars.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserDTO(
    @SerializedName("username") val username: String,
    @SerializedName("name") val name: String,
    @SerializedName("honor") val honor: Int,
    @SerializedName("clan") val clan: String,
    @SerializedName("leaderboardPosition") val leaderboardPosition: Int?,
    @SerializedName("skills") val skills: List<String>?,
    @SerializedName("ranks") val ranks: RankUserDTO,
    @SerializedName("codeChallenges") val codeChallenges: TotalChallengesDTO
): Parcelable