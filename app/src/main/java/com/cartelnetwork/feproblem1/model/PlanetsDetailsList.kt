package com.cartelnetwork.feproblem1.model

import com.google.gson.annotations.SerializedName

data class PlanetsDetailsList(
    val planets : List<Planets>
)

data class  VehiclesDetailsList(
    val vehicles : List<Vehicles>
)
data class TokenBody(
    val token : List<String>
)


data class Planets(
    @SerializedName("name")val name : String,
    @SerializedName("distance")val distance : Int
)

data class Vehicles(
    val name :String,
    val total_no : Int,
    val max_distance : Int,
    val speed : Int
)
data class Token(
    @SerializedName("token")
    val token : String
)
data class PlanetsName(
    val planetNameList : List<String>
)
data class VehicleName(
    val vehicleNameList : List<String>
)
data class FindRequestBody(
    val token: String,
    @SerializedName("planet_names")
    val planet_name: ArrayList<String>,
    @SerializedName("vehicle_names")
    val vehicle_name:  ArrayList<String>


)

data class FindResponseBody(
    val planet_name : String?,
    val status : String
)

data class CustomException(var code: Int = 400, var msg: String, var isBlocking: Boolean = false)