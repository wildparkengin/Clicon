package ua.willeco.clicon.model.getRequestsModels

import ua.willeco.clicon.model.Boiler100
import ua.willeco.clicon.model.Boiler500
import ua.willeco.clicon.model.Clicon
import java.io.Serializable

class GetDevicesListResponse: Serializable{
    val access: Boolean? = null
    val message: String? = null

    val boiler100List: ArrayList<Boiler100>? = null
    val boiler500List: ArrayList<Boiler500>? = null
    val cliconList: ArrayList<Clicon>? = null

}