package com.dariobrux.pokemon.common.extension

import android.net.Uri

/**
 *
 * Created by Dario Bruzzese on 27/11/2020.
 *
 * This file contains all the extended methods for Pokemon related objects.
 *
 */

/**
 * Given an url, extract the 'offset' parameter.
 * @return the corresponding value of the 'offset' parameter or null if it doesn't exist.
 */
fun String?.getOffsetParameter(): Int? {
    return getParameterFromUrl("offset")?.toIntOrNull()
}

/**
 * Given an url, extract the 'limit' parameter.
 * @return the corresponding value of the 'limit' parameter or null if it doesn't exist.
 */
fun String?.getLimitParameter(): Int? {
    return getParameterFromUrl("limit")?.toIntOrNull()
}

/**
 * Given an url, extract a parameter.
 * @return the corresponding value of the parameter or null if it doesn't exist.
 */
private fun String?.getParameterFromUrl(parameter: String): String? {
    this ?: return null
    val uri: Uri = Uri.parse(this)
    return uri.getQueryParameter(parameter)
}