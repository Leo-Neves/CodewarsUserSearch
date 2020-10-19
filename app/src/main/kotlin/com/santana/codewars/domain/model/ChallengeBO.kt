package com.santana.codewars.domain.model

interface ChallengeBO {
    fun getChallengeId(): String
    fun getChallengeName(): String
    fun getChallengeLanguages(): List<String>
}