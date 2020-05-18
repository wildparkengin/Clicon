package ua.willeco.clicon.model

import java.io.Serializable

class DeviceContainer :Serializable{
    private val id: Long? = null
    private val boilerList = listOf<Boiler>()
}