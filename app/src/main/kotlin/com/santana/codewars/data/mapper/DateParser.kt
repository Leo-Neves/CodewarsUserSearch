package com.santana.codewars.data.mapper

import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

fun String.toDateWithTimezone(): Date?{
    return try {
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        formatter.parse(this)
    }catch (e: ParseException){
        null;
    }
}