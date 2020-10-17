package com.santana.codewars.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.santana.codewars.data.dao.UserDao
import com.santana.codewars.domain.model.UserBO
import com.santana.codewars.domain.typeconverter.ListRankConverter
import com.santana.codewars.domain.typeconverter.ListStringConverter
import com.santana.codewars.domain.typeconverter.RankConverter
import com.santana.codewars.domain.typeconverter.TotalChallengeConverter

@Database(entities = [UserBO::class], version = 1, exportSchema = false)
@TypeConverters(ListStringConverter::class, RankConverter::class, ListRankConverter::class, TotalChallengeConverter::class)
abstract class CodewarsDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao
}