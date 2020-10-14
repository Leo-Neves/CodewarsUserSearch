package com.santana.codewars.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.santana.codewars.data.dao.UserDao
import com.santana.codewars.domain.model.UserBO

@Database(entities = [UserBO::class], version = 1, exportSchema = false)
abstract class CodewarsDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao
}