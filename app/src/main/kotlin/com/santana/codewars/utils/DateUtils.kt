package com.santana.codewars.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date.toTimeString(): String{
    val formatter =  SimpleDateFormat ("yyyy.MM.dd 'at' hh:mm:ss a zzz", Locale.UK)
    return formatter.format(this)
}