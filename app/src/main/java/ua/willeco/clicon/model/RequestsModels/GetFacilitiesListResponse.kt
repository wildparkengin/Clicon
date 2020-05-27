package ua.willeco.clicon.model.RequestsModels

import com.google.gson.annotations.SerializedName
import ua.willeco.clicon.model.Facility
import java.io.Serializable

class GetFacilitiesListResponse : Serializable {
    val access:Boolean = false
    val message:String = ""
    val facilityList: ArrayList<Facility>? = null
}