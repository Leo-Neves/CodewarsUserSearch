package com.santana.codewars.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class ChallengesCompletedBO (
    val id: String,
    val name: String,
    val slug: String,
    val completedAt: Date,
    val completedLanguages: List<String>
): Parcelable, ChallengeBO {
    override fun getChallengeId(): String {
        return id
    }

    override fun getChallengeName(): String {
        return name
    }

    override fun getChallengeLanguages(): List<String> {
        return completedLanguages
    }
}