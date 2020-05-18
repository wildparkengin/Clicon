package ua.willeco.clicon.model.RequestsModels

import java.io.Serializable

class AuthentificateResponse:Serializable{
    val access:Boolean? = false
    val message:String? = ""
    val user_id:Long? = 0
    val lang:String? = ""
}