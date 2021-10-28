package com.appsforlife.contactlensmanagement.presentation.utils

import java.text.SimpleDateFormat
import java.util.*

fun getCurrentDate(): String {
    val date = Calendar.getInstance().time
    val formatter = SimpleDateFormat.getDateTimeInstance()
    return formatter.format(date)
}
