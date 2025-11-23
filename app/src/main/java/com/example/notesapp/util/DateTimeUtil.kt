package com.example.notesapp.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateTimeUtil {
    
    fun formatDate(timeInMillis: Long): String {
        val date = Date(timeInMillis)
        val format = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
        return format.format(date)
    }
    
    fun formatDateTime(timeInMillis: Long): String {
        val date = Date(timeInMillis)
        val format = SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault())
        return format.format(date)
    }
}
