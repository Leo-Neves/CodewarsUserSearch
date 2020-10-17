package com.santana.codewars.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.santana.codewars.domain.model.UserBO

@Dao
interface UserDao {
    @Query("SELECT * from UserBO order by updateAt desc limit 5")
    fun listUsersOrderByRecent(): List<UserBO>

    @Query("SELECT * from UserBO order by honor desc limit 5")
    fun listUsersOrderByRank(): List<UserBO>

    @Update
    fun insertOrReplace(user: UserBO)
}