package com.santana.codewars.data

import com.santana.codewars.data.model.CodeChallengeDTO
import com.santana.codewars.data.model.UserChallengesAuthoredDTO
import com.santana.codewars.data.model.UserChallengesCompletedDTO
import com.santana.codewars.data.model.UserDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CodewarsApi {
    @GET("code-challenges/{challengeId}")
    fun codeChallenge(@Path("challengeId") challengeId: String): Single<CodeChallengeDTO>

    @GET("users/{userId}")
    fun user(@Path("userId") userId: String): Single<UserDTO>

    @GET("users/{userId}/code-challenges/completed")
    fun completedChallenges(
        @Path("userId") userId: String,
        @Query("page") page: Int = 0
    ): Single<UserChallengesCompletedDTO>

    @GET("users/{userId}/code-challenges/authored")
    fun authoredChallenges(
        @Path("userId") userId: String,
        @Query("page") page: Int = 0
    ): Single<UserChallengesAuthoredDTO>
}