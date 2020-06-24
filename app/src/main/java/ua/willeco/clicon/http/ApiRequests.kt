package ua.willeco.clicon.http


import retrofit2.Call
import retrofit2.http.*
import ua.willeco.clicon.model.getRequestsModels.*

interface ApiRequests {

    @GET("auth")
    fun getAuthentication(
        @Query("credentials") credentials: String): Call<GetAuthentificateSimpleResponse>

    @GET("facilities")
    fun getFacilitiesList(
        @Query("user_id") user_id: Long): Call<GetFacilityListResponse>

    @PUT("add-facility")
    fun createFacility(
        @Query("user_id") user_id: Long, @Query("name", encoded = true) name: String): Call<SimpleResponse>

    @POST("update-facility")
    fun updateFacility(
        @Query("user_id") user_id: Long,
        @Query("name", encoded = true) name: String,
        @Query("mac") mac: String): Call<SimpleResponse>

    @DELETE("remove-facility")
    fun removeFacility(
        @Query("user_id") user_id: Long,
        @Query("mac") mac: String): Call<SimpleResponse>

    @GET("devices")
    fun getDevicesList(
        @Query("user_id") user_id: Long,
        @Query("mac") mac: String):Call<GetDevicesListResponse>

    @PUT("add-device")
    fun addDevice(
        @Query("user_id") user_id: Long,
        @Query("owner_id") owner_id: Long,
        @Query("device_cid") device_cid: String,
        @Query("name", encoded = true) name: String,
        @Query("type") type: String,
        @Query("mac") mac: String): Call<SimpleResponse>

    @DELETE("remove-device")
    fun removeDevice(
        @Query("user_id") user_id: Long,
        @Query("device_cid") device_cid: String): Call<SimpleResponse>
}