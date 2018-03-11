package com.creepymob.mobile.tinkoffnews.data.network

import com.creepymob.mobile.tinkoffnews.data.news.RESULT_OK
import com.creepymob.mobile.tinkoffnews.data.news.ServerResponse
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import java.net.HttpURLConnection

/**
 * User: andrey
 * Date: 09.03.2018
 * Time: 19:43
 *
 */
class ServerResponseInterceptor(private val gson: Gson) : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val response = chain.proceed(request)

        return when (response.code()) {
            in HttpURLConnection.HTTP_OK..HttpURLConnection.HTTP_USE_PROXY -> processResponse(response)
            else -> throw ServerException("exception ${response.code()}")
        }
    }

    private fun processResponse(response: Response): Response {

        val body = response.body() ?: throw ServerException("body null")

        val bodyText = body.charStream().use { it.readText() }
        var serverResponse: ServerResponse? = null

        try {
            serverResponse = gson.fromJson(bodyText, ServerResponse::class.java)
        } catch (throwable: Throwable) {
            throw ServerException("serverResponse can't be deserialize: ${throwable.message}")
        }
        if (serverResponse.resultCode == RESULT_OK) {
            return response.newBuilder().body(ResponseBody.create(body.contentType(), serverResponse.payload.toString())).build()
        } else {
            throw ServerException("server error code")
        }
    }
}