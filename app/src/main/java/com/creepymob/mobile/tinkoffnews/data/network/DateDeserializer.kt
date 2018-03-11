package com.creepymob.mobile.tinkoffnews.data.network

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type
import java.util.*

/**
 * User: andrey
 * Date: 09.03.2018
 * Time: 19:34
 *
 */
class DateDeserializer : JsonDeserializer<Date> {

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Date {

        if (json.isJsonObject) {
            val jsonObject = json.asJsonObject
            val milliseconds = jsonObject["milliseconds"].asLong
            return Date(milliseconds)
        }
        throw JsonParseException("cannot parse ${json.toString()}")
    }
}