package com.cartelnetwork.feproblem1.BaseViewModel

import com.cartelnetwork.feproblem1.api.ApiHelper
import com.cartelnetwork.feproblem1.model.FindRequestBody


class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getUsers() = apiHelper.getToken()
    suspend fun getPlanet() = apiHelper.getPlanet()
    suspend fun getVehicle() = apiHelper.getVehicles()
    suspend fun find(findRequestBody: FindRequestBody) = apiHelper.find(findRequestBody)
}