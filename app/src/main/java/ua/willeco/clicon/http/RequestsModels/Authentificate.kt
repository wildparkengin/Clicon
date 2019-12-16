package ua.willeco.clicon.http.RequestsModels

import java.io.Serializable

class Authentificate:Serializable{
    val access:Boolean? = false
    val message:String? = ""
    val user_id:Long = 0
    val lang:String = ""
}