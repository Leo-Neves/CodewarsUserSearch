package com.santana.codewars.domain.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.santana.codewars.domain.model.RankBO
import java.lang.reflect.Type

class RankConverter {

    @TypeConverter
    fun rankToString(list: RankBO?): String?{
        val listType: Type = object : TypeToken<RankBO?>() {}.getType()
        return Gson().toJson(list, listType);
    }

    @TypeConverter
    fun stringToRank(string: String?): RankBO?{
        val listType: Type = object : TypeToken<RankBO?>() {}.getType()
        return Gson().fromJson(string, listType);
    }
}