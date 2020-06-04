package ua.willeco.clicon.mvp.repository

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ua.willeco.clicon.http.ApiRequests
import ua.willeco.clicon.model.RequestsModels.GetDevicesListResponse
import ua.willeco.clicon.model.RequestsModels.GetFacilitiesListResponse
import ua.willeco.clicon.model.RequestsModels.SimpleResponse
import ua.willeco.clicon.singletons.CurrentUserSingleton

class FacilityRepository constructor(private val api: ApiRequests):BaseResponseRepositoryInterface{

    fun getListFacilitiesResponse(onFinishedListener: BaseResponseRepositoryInterface.OnFinishedRequest) {
        val call = api.getFacilitiesList(CurrentUserSingleton.getUserID())
        call.enqueue(object : Callback<GetFacilitiesListResponse> {
            override fun onFailure(call: Call<GetFacilitiesListResponse>, t: Throwable) {
                onFinishedListener.onFailureRequest(t.message.toString())
            }

            override fun onResponse(call: Call<GetFacilitiesListResponse>, response: Response<GetFacilitiesListResponse>) {
                if (response.code() == 200){
                    response.body()?.let { onFinishedListener.onFinishedRequest(it) }
                }else{
                    onFinishedListener.onFailureRequest(response.message())
                }
            }
        })
    }

    fun createFacility(onFinishedListener: BaseResponseRepositoryInterface.OnFinishedRequest, name:String){
        val call = api.createFacility(CurrentUserSingleton.getUserID(),name)
        call.enqueue(object : Callback<SimpleResponse> {
            override fun onFailure(call: Call<SimpleResponse>, t: Throwable) {
                onFinishedListener.onFailureRequest(t.message.toString())
            }

            override fun onResponse(call: Call<SimpleResponse>, response: Response<SimpleResponse>) {
                if (response.code() == 200){
                    response.body()?.let { onFinishedListener.onFinishedRequest(it) }
                }else{
                    onFinishedListener.onFailureRequest(response.message())
                }
            }
        })
    }

    fun deleteFacility(onFinishedListener: BaseResponseRepositoryInterface.OnFinishedRequest, mac:String){
        val call = api.removeFacility(CurrentUserSingleton.getUserID(),mac)
        call.enqueue(object : Callback<SimpleResponse> {
            override fun onFailure(call: Call<SimpleResponse>, t: Throwable) {
                onFinishedListener.onFailureRequest(t.message.toString())
            }

            override fun onResponse(call: Call<SimpleResponse>, response: Response<SimpleResponse>) {
                if (response.code() == 200){
                    response.body()?.let { onFinishedListener.onFinishedRequest(it) }
                }else{
                    onFinishedListener.onFailureRequest(response.message())
                }
            }
        })
    }

    fun updateFacility(onFinishedListener: BaseResponseRepositoryInterface.OnFinishedRequest, name:String,mac:String){
        val call = api.updateFacility(CurrentUserSingleton.getUserID(),name,mac)
        call.enqueue(object : Callback<SimpleResponse> {
            override fun onFailure(call: Call<SimpleResponse>, t: Throwable) {
                onFinishedListener.onFailureRequest(t.message.toString())
            }

            override fun onResponse(call: Call<SimpleResponse>, response: Response<SimpleResponse>) {
                if (response.code() == 200){
                    response.body()?.let { onFinishedListener.onFinishedRequest(it) }
                }else{
                    onFinishedListener.onFailureRequest(response.message())
                }
            }
        })
    }


}