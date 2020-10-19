package com.santana.codewars.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CodeChallengeDTO (
    val id: String,
    val name: String,
    val slug: String,
    val category: String,
    val publishedAt: String,
    val approvedAt: String,
    val languages: List<String>,
    val url: String,
    val rank: RankDTO,
    val createdBy: UsernameDTO,
    val approvedBy: UsernameDTO,
    val description: String,
    val totalAttempts: Int,
    val totalCompleted: Int,
    val totalStars: Int,
    val voteScore: Int,
    val tags: List<String>,
    val unresolved: UnresolvedIssuesDTO?
): Parcelable