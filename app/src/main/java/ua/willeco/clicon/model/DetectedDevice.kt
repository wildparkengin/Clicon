package ua.willeco.clicon.model

import java.io.Serializable
import ua.willeco.clicon.enums.BoilerType
import ua.willeco.clicon.enums.DeviceType
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