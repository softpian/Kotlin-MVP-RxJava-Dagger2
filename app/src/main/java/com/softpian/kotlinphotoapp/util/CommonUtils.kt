package com.softpian.kotlinphotoapp.util

import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

fun getNumberFormatted(number: Long)
        = DecimalFormat("#,###").format(number)

fun getDateFromUnixTimestamp(unixTimestamp: String): String {

    val calendar= Calendar.getInstance()
    val timestamp = unixTimestamp.toLong()
    calendar.setTimeInMillis(timestamp * 1000);

    return SimpleDateFormat("dd-MM-yyyy hh:mm:ss", Locale.getDefault())
            .format(calendar.getTime());
}