package ua.willeco.clicon.model.getRequestsModels

import ua.willeco.clicon.model.Facility
import java.io.Serializable

class GetRoomListResponse : Serializable {
    val access: Boolean? = null
    val message: String? = null
    val facilityList: ArrayList<Facility>? = null
}