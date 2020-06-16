package ua.willeco.clicon.mvp.repository

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ua.willeco.clicon.enums.AppRequestEventType
import ua.willeco.clicon.http.ApiRequests
import ua.willeco.clicon.model.getRequestsModels.FacilityCRUDResponse
import ua.willeco.clicon.model.getRequestsModels.GetRoomListResponse
import ua.willeco.clicon.singletons.CurrentUserSingleton
import java.net.URLEncoder

class FacilityRepository constructor(private val api: ApiRequests):BaseResponseRepositoryInterface{

    fun getFacilityListResponse(onFinishedListener: BaseResponseRepositoryInterface.OnFinishedRequest) {
        val call = api.getFacilitiesList(CurrentUserSingleton.getUserID())
        call.enqueue(object : Callback<GetRoomListResponse> {
            override fun onFailure(call: Call<GetRoomListResponse>, t: Throwable) {
                onFinishedListener.onFailureRequest(t.message.toString())
            }

            override fun onResponse(call: Call<GetRoomListResponse>, response: Response<GetRoomListResponse>) {
                if (response.code() == 200){
                    response.body()?.let { onFinishedListener.onFinishedRequest(it,AppRequestEventType.GET_FACILITY_LIST) }
                }else{
                    onFinishedListener.onFailureRequest(response.message())
                }
            }
        })
    }

    fun createFacilityResponse(onFinishedListener: BaseResponseRepositoryInterface.OnFinishedRequest, name:String){
        val call = api.createFacility(CurrentUserSingleton.getUserID(),URLEncoder.encode(name,"utf-8"))
        call.enqueue(object : Callback<FacilityCRUDResponse> {
            override fun onFailure(call: Call<FacilityCRUDResponse>, t: Throwable) {
                onFinishedListener.onFailureRequest(t.message.toString())
            }

            override fun onResponse(call: Call<FacilityCRUDResponse>, responseFacility: Response<FacilityCRUDResponse>) {
                if (responseFacility.code() == 200){
                    responseFacility.body()?.let { onFinishedListener.onFinishedRequest(it,AppRequestEventType.ADD_FACILITY) }
                }else{
                    onFinishedListener.onFailureRequest(responseFacility.message())
                }
            }
        })
    }

    fun deleteFacilityResponse(onFinishedListener: BaseResponseRepositoryInterface.OnFinishedRequest, mac:String){
        val call = api.removeFacility(CurrentUserSingleton.getUserID(),mac)
        call.enqueue(object : Callback<FacilityCRUDResponse> {
            override fun onFailure(call: Call<FacilityCRUDResponse>, t: Throwable) {
                onFinishedListener.onFailureRequest(t.message.toString())
            }

            override fun onResponse(call: Call<FacilityCRUDResponse>, responseFacility: Response<FacilityCRUDResponse>) {
                if (responseFacility.code() == 200){
                    responseFacility.body()?.let { onFinishedListener.onFinishedRequest(it,AppRequestEventType.DELETE_FACILITY) }
                }else{
                    onFinishedListener.onFailureRequest(responseFacility.message())
                }
            }
        })
    }

    fun updateFacilityResponse(onFinishedListener: BaseResponseRepositoryInterface.OnFinishedRequest, name:String, mac:String){
        val call = api.updateFacility(CurrentUserSingleton.getUserID(),URLEncoder.encode(name,"utf-8"),mac)
        call.enqueue(object : Callback<FacilityCRUDResponse> {
            override fun onFailure(call: Call<FacilityCRUDResponse>, t: Throwable) {
                onFinishedListener.onFailureRequest(t.message.toString())
            }

            override fun onResponse(call: Call<FacilityCRUDResponse>, responseFacility: Response<FacilityCRUDResponse>) {
                if (responseFacility.code() == 200){
                    responseFacility.body()?.let { onFinishedListener.onFinishedRequest(it,AppRequestEventType.UPDATE_FACILITY) }
                }else{
                    onFinishedListener.onFailureRequest(responseFacility.message())
                }
            }
        })
    }


}