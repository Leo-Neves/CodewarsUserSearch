package com.santana.codewars.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ChallengesAuthoredDTO (
    val id: String,
    val name: String,
    val description: String,
    val rank: Int,
    val rankName: String,
    val languages: List<String>,
    val tags: List<String>
): Parcelable