package ua.willeco.clicon.model.boilerSettings

import java.io.Serializable

class IgnitionFlowSettings : FSaPWMincluded(),Serializable{
    private val tubularHeatingElementWorkTime = 120
    private val tubularHeatingElementPauseTime = 120
    private val firstFeed = 120
    private val autoIgnition = false
    private val ignitionLightSensorMaxValue = 55
}