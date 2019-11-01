package com.wildpark.clicon.entities

import java.io.Serializable
import com.wildpark.clicon.enums.BoilerType
import com.wildpark.clicon.enums.DeviceType
import java.util.*


class DetectedDevice: Device,Serializable {
    private val id: Long? = null
    private val chipId: String? = null
    private val deviceType: DeviceType? = null
    private val boilerType: BoilerType? = null
    private val detectionDate: Date? = null
    private val lastMessageDate: Date? = null
    private val missedMessagesCount = 0

    override fun getChipId(): String? {
        return chipId
    }
}