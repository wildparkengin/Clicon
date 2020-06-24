package ua.willeco.clicon.model.getRequestsModels

import java.io.Serializable

class GetAuthentificateSimpleResponse:Serializable{
    val user_id:Long? = 0
    val lang:String? = ""
    val access:Boolean = false
    val message:String = ""
}