package com.creepymob.mobile.tinkoffnews.app

import android.os.Build
import android.text.Html
import android.text.Spanned

/**
 * User: andrey
 * Date: 10.03.2018
 * Time: 1:07
 *
 */
object HtmlCompat {

    @Suppress("DEPRECATION")
    fun fromHtml(html: String): Spanned = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
            } else {
                Html.fromHtml(html)
            }

}