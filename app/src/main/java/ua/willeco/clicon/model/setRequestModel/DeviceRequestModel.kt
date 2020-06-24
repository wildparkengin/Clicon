package ua.willeco.clicon.model.setRequestModel

import ua.willeco.clicon.enums.DeviceType
import java.io.Serializable

data class DeviceRequestModel(
    val user_id:Long,
    val owner_id:Long,
    val device_cid:String,
    val name:String,
    val type:DeviceType,
    val mac:String
):Serializable