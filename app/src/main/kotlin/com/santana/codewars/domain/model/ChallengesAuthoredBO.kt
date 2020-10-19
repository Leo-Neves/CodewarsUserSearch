package com.santana.codewars.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ChallengesAuthoredBO (
    val id: String,
    val name: String,
    val description: String,
    val rank: Int,
    val rankName: String?,
    val languages: List<String>,
    val tags: List<String>
): Parcelable, ChallengeBO {
    override fun getChallengeId(): String {
        return id
    }

    override fun getChallengeName(): String {
        return name
    }

    override fun getChallengeLanguages(): List<String> {
        return languages
    }
}