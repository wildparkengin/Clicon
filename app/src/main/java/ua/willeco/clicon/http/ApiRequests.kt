package ua.willeco.clicon.http

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import ua.willeco.clicon.model.RequestsModels.AuthentificateResponse

interface ApiRequests {

    @GET("mobile_rest_api/auth/")
    fun autentificate(
        @Query("login") loginUser: String,
        @Query("password") passwordUser: String): Observable<AuthentificateResponse>

    @GET("mobile_rest_api/facility_list/")
    fun getFacilityList(
        @Query("user_id") user_id: Long): Observable<AuthentificateResponse>

    @GET("mobile_rest_api/devices/")
    fun getDevice(
        @Query("user_id") user_id: Long,
        @Query("facility_id:")facility_id:Long): Observable<AuthentificateResponse>
}