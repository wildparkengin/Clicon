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
        @Query("user_id") user_id: Long): Call<GetRoomListResponse>

    @PUT("add-facility")
    fun createFacility(
        @Query("user_id") user_id: Long, @Query("name", encoded = true) name: String): Call<FacilityCRUDResponse>

    @POST("update-facility")
    fun updateFacility(
        @Query("user_id") user_id: Long,
        @Query("name", encoded = true) name: String,
        @Query("mac") mac: String): Call<FacilityCRUDResponse>

    @DELETE("remove-facility")
    fun removeFacility(
        @Query("user_id") user_id: Long,
        @Query("mac") mac: String): Call<FacilityCRUDResponse>

    @GET("devices")
    fun getDevicesList(
        @Query("user_id") user_id: Long,
        @Query("mac") mac: String):Call<GetDevicesListResponse>

    @PUT("add-device")
    fun addDevice(
        @Query("user_id") user_id: Long,
        @Query("device_cid") device_cid: String,
        @Query("name", encoded = true) name: String): Call<DeviceCRUDResponce>

    @DELETE("remove-device")
    fun removeDevice(
        @Query("user_id") user_id: Long,
        @Query("device_cid") device_cid: String): Call<DeviceCRUDResponce>
}