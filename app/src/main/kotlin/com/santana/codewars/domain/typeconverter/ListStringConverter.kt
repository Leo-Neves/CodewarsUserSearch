package com.santana.codewars.domain.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ListStringConverter {

    @TypeConverter
    fun listToString(list: List<String?>?): String?{
        val listType: Type = object : TypeToken<List<String?>?>() {}.getType()
        return Gson().toJson(list, listType);
    }

    @TypeConverter
    fun stringToList(string: String?): List<String?>?{
        val listType: Type = object : TypeToken<List<String?>?>() {}.getType()
        return Gson().fromJson(string, listType);
    }
}