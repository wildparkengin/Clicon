package ua.willeco.clicon.http

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import ua.willeco.clicon.model.RequestsModels.GetAuthentificateResponse
import ua.willeco.clicon.model.RequestsModels.GetFacilitiesListResponse

interface ApiRequests {

    @GET("auth")
    fun getAuthentication(
        @Query("credentials") credentials: String): Observable<GetAuthentificateResponse>

    @GET("facilities")
    fun getFacilitiesList(
        @Query("user_id") user_id: Long): Observable<GetFacilitiesListResponse>

}