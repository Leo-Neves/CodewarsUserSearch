package com.santana.codewars.data

import com.santana.codewars.data.model.CodeChallengeDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CodewarsApi {
    @GET("code-challenges/{challengeId}")
    fun codeChallenge(@Path("challengeId") challengeId: String): Call<CodeChallengeDTO>

    fun userInfo(@Path("userId") userId: String)
}