package com.dariobrux.pokemon.common

import android.net.Uri

fun String?.getOffsetParameter(): Int? {
    return getParameterFromUrl("offset")?.toIntOrNull()
}

fun String?.getLimitParameter(): Int? {
    return getParameterFromUrl("limit")?.toIntOrNull()
}

private fun String?.getParameterFromUrl(parameter: String): String? {
    this ?: return null
    val uri: Uri = Uri.parse(this)
    return uri.getQueryParameter(parameter)
}