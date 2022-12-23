package com.naser.blogs.core.utils

import java.text.DateFormat
import java.text.SimpleDateFormat

object Time {

    fun getTime(time: String) : String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val date = sdf.parse(time)
        return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(date)
    }

}