package ua.willeco.clicon.http

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ua.willeco.clicon.http.RequestsModels.Authentificate

interface Requests {

    @GET("mobile_rest_api/auth/")
    fun autentificate(
        @Query("login") loginUser: String,
        @Query("password") passwordUser: String): Call<Authentificate>

    @GET("mobile_rest_api/facility_list/")
    fun getFacilityList(
        @Query("user_id") user_id: Long): Call<Authentificate>

    @GET("mobile_rest_api/devices/")
    fun getDevice(
        @Query("user_id") user_id: Long,
        @Query("facility_id:")facility_id:Long): Call<Authentificate>


}