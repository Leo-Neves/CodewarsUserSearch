package com.santana.codewars.data.dao

import androidx.room.*
import com.santana.codewars.domain.model.UserBO
import io.reactivex.Single

@Dao
interface UserDao {
    @Query("SELECT * from UserBO order by updateAt desc limit 5")
    fun listUsersOrderByRecent(): Single<List<UserBO>>

    @Query("SELECT * from UserBO order by honor desc limit 5")
    fun listUsersOrderByRank(): Single<List<UserBO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrReplace(user: UserBO)
}