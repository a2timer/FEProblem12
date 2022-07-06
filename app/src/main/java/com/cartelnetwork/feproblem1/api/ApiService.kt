package com.cartelnetwork.feproblem1.api


import com.cartelnetwork.feproblem1.model.*
import retrofit2.http.*

interface ApiService {


    @POST("token")
     suspend fun getToken(): Token

     @GET("planets")
     suspend fun getPlanet() : List<Planets>

    @GET("vehicles")
    suspend fun getVehicles() : List<Vehicles>

    @POST("find")
    suspend fun find(@Body findRequestBody: FindRequestBody): FindResponseBody

}