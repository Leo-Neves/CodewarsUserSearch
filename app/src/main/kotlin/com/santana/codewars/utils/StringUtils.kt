package com.santana.codewars.utils

import android.app.Activity
import android.content.Context
import com.santana.codewars.R

fun List<String>.splitStringList(
    context: Context,
    emptyListString: Int,
    fullListString: Int
): String {
    return if (isEmpty()) {
        context.getString(emptyListString)
    } else {
        context.getString(
            fullListString,
            joinToString(context.getString(R.string.separator))
        )
    }
}