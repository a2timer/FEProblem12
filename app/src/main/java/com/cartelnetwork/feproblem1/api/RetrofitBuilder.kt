package com.cartelnetwork.feproblem1.api

import com.cartelnetwork.feproblem1.utils.AppConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.Interceptor
import okhttp3.Request


object RetrofitBuilder {

    private const val BASE_URL = AppConstants.BASE_URL

    private var httpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()

    private fun getRetrofit(): Retrofit {

        httpBuilder.networkInterceptors().add(Interceptor { chain ->
            val requestBuilder: Request.Builder = chain.request().newBuilder()
            requestBuilder.header("Accept", "application/json")
            requestBuilder.header("Content-Type", "application/json")
            chain.proceed(requestBuilder.build())
        })
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpBuilder.build())
            .build() //Doesn't require the adapter


    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)

}