package ua.willeco.clicon.mvp.repository

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ua.willeco.clicon.http.ApiRequests
import ua.willeco.clicon.model.RequestsModels.GetDevicesListResponse
import ua.willeco.clicon.model.RequestsModels.SimpleResponse
import ua.willeco.clicon.singletons.CurrentUserSingleton

class DevicesRepository constructor(private val api: ApiRequests):BaseResponseRepositoryInterface{

    fun getListDeviceResponse(onFinishedListener: BaseResponseRepositoryInterface.OnFinishedRequest, mac:String) {
        val call = api.getDevicesList(CurrentUserSingleton.getUserID(),mac)

        call.enqueue(object : Callback<GetDevicesListResponse> {
            override fun onFailure(call: Call<GetDevicesListResponse>, t: Throwable) {
                onFinishedListener.onFailureRequest(t.message.toString())
            }
            override fun onResponse(call: Call<GetDevicesListResponse>, response: Response<GetDevicesListResponse>) {
                if (response.code() == 200){
                    response.body()?.let { onFinishedListener.onFinishedRequest(it) }
                }else{
                    onFinishedListener.onFailureRequest(response.message())
                }
            }
        })
    }

    fun createDevice(onFinishedListener: BaseResponseRepositoryInterface.OnFinishedRequest,
        device_cid:String, name:String){
        val call = api.addDevice(CurrentUserSingleton.getUserID(),device_cid,name)

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

    fun deleteDEvice(onFinishedListener: BaseResponseRepositoryInterface.OnFinishedRequest, device_cid:String){
        val call = api.removeDevice(CurrentUserSingleton.getUserID(),device_cid)

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