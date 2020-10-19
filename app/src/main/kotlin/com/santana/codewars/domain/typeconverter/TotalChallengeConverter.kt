package com.santana.codewars.domain.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.santana.codewars.domain.model.RankBO
import com.santana.codewars.domain.model.TotalChallengesBO
import java.lang.reflect.Type

class TotalChallengeConverter {

    @TypeConverter
    fun totalChallengeToString(list: TotalChallengesBO?): String?{
        val listType: Type = object : TypeToken<TotalChallengesBO?>() {}.getType()
        return Gson().toJson(list, listType);
    }

    @TypeConverter
    fun stringToTotalChallenge(string: String?): TotalChallengesBO?{
        val listType: Type = object : TypeToken<TotalChallengesBO?>() {}.getType()
        return Gson().fromJson(string, listType);
    }
}