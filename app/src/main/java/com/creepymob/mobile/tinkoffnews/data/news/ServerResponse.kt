package com.creepymob.mobile.tinkoffnews.data.news

import com.google.gson.JsonElement

/**
 * User: andrey
 * Date: 09.03.2018
 * Time: 19:45
 *
 */
class ServerResponse(val resultCode: String, val payload: JsonElement)

const val RESULT_OK = "OK"
const val RESULT_ERROR = "ERROR"