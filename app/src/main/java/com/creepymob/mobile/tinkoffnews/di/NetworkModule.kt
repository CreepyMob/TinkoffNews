package com.creepymob.mobile.tinkoffnews.di

import com.creepymob.mobile.tinkoffnews.data.network.DateDeserializer
import com.creepymob.mobile.tinkoffnews.data.network.ServerResponseInterceptor
import com.creepymob.mobile.tinkoffnews.data.network.TinkoffApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * User: andrey
 * Date: 09.03.2018
 * Time: 18:00
 *
 */
@Module
class NetworkModule {

    companion object {
        private const val TIMEOUT_CONNECT_SEC = 30L
        private const val TIMEOUT_READ_SEC = 30L
        private const val TIMEOUT_WRITE_SEC = 30L
    }

    @Singleton
    @Provides
    fun provideGson(dateDeserializer: JsonDeserializer<Date>): Gson =
            GsonBuilder().registerTypeAdapter(Date::class.java, dateDeserializer).create()

    @Provides
    @Singleton
    fun provideCallAdapterFactory(): CallAdapter.Factory = RxJava2CallAdapterFactory.create()

    @Provides
    @Singleton
    fun provideConverterFactory(gson: Gson): Converter.Factory =
            GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideDateSerializerAdapter(): JsonDeserializer<Date> = DateDeserializer()

    @Provides
    @Singleton
    fun provideOkHttpClient(gson: Gson): OkHttpClient {

        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY


        val serverResponseInterceptor = ServerResponseInterceptor(gson)

        return OkHttpClient.Builder()
                .addInterceptor(logInterceptor)
                .addInterceptor(serverResponseInterceptor)
                .connectTimeout(TIMEOUT_CONNECT_SEC, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_READ_SEC, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_WRITE_SEC, TimeUnit.SECONDS)
                .build()
    }

    @Provides
    @Singleton
    fun provideTinkoffApi(callAdapterFactory: CallAdapter.Factory, converterFactory: Converter.Factory, okHttpClient: OkHttpClient): TinkoffApi {

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.tinkoff.ru/v1/")
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .client(okHttpClient)
                .build()

        return retrofit.create(TinkoffApi::class.java)
    }


    /*@Provides
    @Singleton
    fun provideQpayHttpClient(context: Context,
                              apiConfig: ApiConfiguration,
                              sessionDataSource: SessionDataSource,
                              errorInterceptor: Interceptor): OkHttpClient =
            createHttpClient(context, QpayHeaderInterceptor(Headers.of(apiConfig.qpayHeaders), sessionDataSource), errorInterceptor)

    @Provides
    @Singleton
    fun provideErrorInterceptor(gson: Gson, exceptionFactory: ExceptionFactory): Interceptor =
            ErrorInterceptorImpl(gson, exceptionFactory)

    @Provides
    @Singleton
    @Qpay
    fun provideQpayRetrofit(apiConfiguration: ApiConfiguration,
                            callAdapterFactory: CallAdapter.Factory,
                            convertFactory: Converter.Factory,
                            @Qpay qpayHttpClient: OkHttpClient): Retrofit =
            createRetrofit(apiConfiguration.apiUrl, callAdapterFactory, convertFactory, qpayHttpClient)

    @Provides
    @Singleton
    @Ags
    fun provideAgsRetrofit(apiConfiguration: ApiConfiguration,
                           callAdapterFactory: CallAdapter.Factory,
                           convertFactory: Converter.Factory,
                           @Ags agsHttpClient: OkHttpClient): Retrofit =
            createRetrofit(apiConfiguration.agsUrl, callAdapterFactory, convertFactory, agsHttpClient)

    private fun createRetrofit(baseUrl: String,
                               callAdapterFactory: CallAdapter.Factory,
                               convertFactory: Converter.Factory,
                               okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(callAdapterFactory)
                    .addConverterFactory(convertFactory)
                    .client(okHttpClient)
                    .build()

    private fun createHttpClient(context: Context, headerInterceptor: Interceptor, errorInterceptor: Interceptor): OkHttpClient {

        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
                .addInterceptor(headerInterceptor)
                .addInterceptor(logInterceptor)
                .addInterceptor(errorInterceptor)
                .putMockInterceptor(context)
                .connectTimeout(TIMEOUT_CONNECT_SEC, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_READ_SEC, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_WRITE_SEC, TimeUnit.SECONDS)
                .build()
    }*/

}