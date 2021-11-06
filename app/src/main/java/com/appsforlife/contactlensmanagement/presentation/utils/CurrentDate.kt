package com.appsforlife.contactlensmanagement.presentation.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

fun getCurrentDate(): String {
    val date = Calendar.getInstance().time
    val formatter = SimpleDateFormat.getDateTimeInstance()
    return formatter.format(date)
}

@SuppressLint("SimpleDateFormat")
fun getTitleCurrentDate():String{
    val sdf = SimpleDateFormat("EE, dd MMMM")
    val d = Date()
    return sdf.format(d)
}
