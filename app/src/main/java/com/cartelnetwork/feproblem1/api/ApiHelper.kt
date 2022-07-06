package com.cartelnetwork.feproblem1.api

import com.cartelnetwork.feproblem1.model.FindRequestBody

class ApiHelper(private val apiService: ApiService) {

    suspend fun getToken() = apiService.getToken()
    suspend fun getPlanet() = apiService.getPlanet()
    suspend fun getVehicles() = apiService.getVehicles()
    suspend fun find(findRequestBody: FindRequestBody) = apiService.find(findRequestBody)
}