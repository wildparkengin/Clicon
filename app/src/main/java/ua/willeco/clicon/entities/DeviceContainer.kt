package ua.willeco.clicon.entities

import ua.willeco.clicon.entities.Boiler
import java.io.Serializable

class DeviceContainer :Serializable{
    private val id: Long? = null
    private val boilerList = listOf<Boiler>()
}