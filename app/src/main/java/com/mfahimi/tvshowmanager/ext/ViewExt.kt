package com.mfahimi.tvshowmanager.ext

import android.view.View
import androidx.annotation.StringRes


fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.enable() {
    isEnabled = true
}

fun View.disable() {
    isEnabled = false
}
fun View.string(@StringRes id: Int, vararg formatArgs: Any): String = context.resources.getString(id, formatArgs)
