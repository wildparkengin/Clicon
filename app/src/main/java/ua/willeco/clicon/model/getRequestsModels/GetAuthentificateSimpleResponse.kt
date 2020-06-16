package ua.willeco.clicon.model.getRequestsModels

import java.io.Serializable

class GetAuthentificateSimpleResponse:Serializable,SimpleResponse(){
    val user_id:Long? = 0
    val lang:String? = ""
}