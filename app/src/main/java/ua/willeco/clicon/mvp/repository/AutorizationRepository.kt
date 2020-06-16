package ua.willeco.clicon.mvp.repository

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ua.willeco.clicon.enums.AppRequestEventType
import ua.willeco.clicon.http.ApiRequests
import ua.willeco.clicon.model.getRequestsModels.GetAuthentificateSimpleResponse

class AutorizationRepository constructor(private val api: ApiRequests):BaseResponseRepositoryInterface{

    fun getResponseAuth(onFinishedListener: BaseResponseRepositoryInterface.OnFinishedRequest, credential:String) {
        val call = api.getAuthentication(credential)
        call.enqueue(object : Callback<GetAuthentificateSimpleResponse> {
            override fun onFailure(call: Call<GetAuthentificateSimpleResponse>, t: Throwable) {
                onFinishedListener.onFailureRequest(t.message.toString())
            }

            override fun onResponse(call: Call<GetAuthentificateSimpleResponse>, response: Response<GetAuthentificateSimpleResponse>) {
                if (response.code() == 200){
                    response.body()?.let { onFinishedListener.onFinishedRequest(it,AppRequestEventType.AUTHORIZATION) }
                }else{
                    onFinishedListener.onFailureRequest(response.message())
                }
            }
        })
    }
}