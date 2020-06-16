package ua.willeco.clicon.model.getRequestsModels

import java.io.Serializable

abstract class SimpleResponse:Serializable {
    val access:Boolean = false
    val message:String = ""
}