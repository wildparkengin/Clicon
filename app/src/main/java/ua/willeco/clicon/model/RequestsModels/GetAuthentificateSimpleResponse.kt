package ua.willeco.clicon.model.RequestsModels

import java.io.Serializable

class GetAuthentificateSimpleResponse:Serializable,SimpleResponse{
    val user_id:Long? = 0
    val lang:String? = ""
    override val access: Boolean? = null
    override val message: String? = null
}