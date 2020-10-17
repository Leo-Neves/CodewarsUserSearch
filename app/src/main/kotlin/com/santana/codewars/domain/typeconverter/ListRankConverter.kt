package com.santana.codewars.domain.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.santana.codewars.domain.model.RankBO
import java.lang.reflect.Type

class ListRankConverter {

    @TypeConverter
    fun rankListToString(list: List<RankBO?>?): String?{
        val listType: Type = object : TypeToken<List<RankBO?>?>() {}.getType()
        return Gson().toJson(list, listType);
    }

    @TypeConverter
    fun stringToRankList(string: String?): List<RankBO?>?{
        val listType: Type = object : TypeToken<List<RankBO?>?>() {}.getType()
        return Gson().fromJson(string, listType);
    }
}