package ua.willeco.clicon.http

import android.content.Context
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ua.willeco.clicon.R
import ua.willeco.clicon.model.RequestsModels.AuthentificateResponse
import ua.willeco.clicon.utility.Validation

class ResponcesApi{

    companion object{

        private fun showRequestToast(context: Context,message:String){
            Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
        }

        fun authentificateRequest(context: Context, services:ApiRequests, login:String, pass:String){
//            val password = Validation.cryptPasswordAES(pass)
//            val call = services.autentificate(login,password)
//            call.enqueue(object : Callback<AuthentificateResponse> {
//
//                override fun onFailure(call: Call<AuthentificateResponse>, t: Throwable) {
//                    showRequestToast(context,context.getString(R.string.promt_fail_to_connect))
//                }
//
//                override fun onResponse(
//                    call: Call<AuthentificateResponse>, response: Response<AuthentificateResponse>) {
//                    if (response.code() == 200){
//                        val respData = response.body()
//                        respData?.access.let {
//                            if (it == true){
//
//                            }else{
//                                respData?.message?.let { s -> showRequestToast(context, s) }
//                            }
//                        }
//                    }else{
//                        showRequestToast(context,context.getString(R.string.promt_fail_to_connect))
//                    }
//                }
//            })
        }

//        fun getDeviceRequest(context: Context, services:ApiRequests, userID:Long, facilityID:Long){
//            val call = services.getDevice(userID,facilityID)
//            call.enqueue(object:Callback<AuthentificateResponse>{
//                override fun onFailure(call: Call<AuthentificateResponse>, t: Throwable) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//
//                override fun onResponse(
//                    call: Call<AuthentificateResponse>,
//                    response: Response<AuthentificateResponse>
//                ) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//            })
//        }
//
//        fun getFacilityRequest(context: Context, services:ApiRequests, userID:Long){
//            val call = services.getFacilityList(userID)
//            call.enqueue(object:Callback<AuthentificateResponse>{
//                override fun onFailure(call: Call<AuthentificateResponse>, t: Throwable) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//
//                override fun onResponse(
//                    call: Call<AuthentificateResponse>,
//                    response: Response<AuthentificateResponse>
//                ) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//            })
//        }
    }
}