package com.mfahimi.tvshowmanager.ext

import android.text.ParcelableSpan
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView

open class SpanModel(val text: String, val span: ParcelableSpan)

fun stringListToSpan(vararg spans: SpanModel): SpannableStringBuilder {

    val span = SpannableStringBuilder()
    for (i in spans.indices) {
        span.append(spans[i].text)
        span.setSpan(
            spans[i].span,
            span.length - spans[i].text.length,
            span.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    return span
}