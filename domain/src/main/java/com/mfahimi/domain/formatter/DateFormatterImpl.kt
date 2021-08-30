package com.mfahimi.domain.formatter

import java.text.SimpleDateFormat
import java.util.*

class DateFormatterImpl : DateFormatter {
    private val dateParser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US)
    private val dateFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US)

    override fun formatToDateTime(date: String): String {
        return try {
            val mDate = dateParser.parse(date)
            dateFormatter.format(mDate.time)
        } catch (e: Exception) {
            ""
        }
    }
}