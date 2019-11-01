package com.wildpark.clicon.entities.boilerSettings

import java.io.Serializable

class AlarmFlowSettings: BoilerSettings(),Serializable{
    private val circuitMaxTemp = 80 // maxTemperatureInnerConture
    private val timeToNormalizationCheck = 60
    private val fuelScrewMaxTemp = 60 // maxTemperatureFuelScrew
    private val fuelScrewAlarmWorkTime = 20 // workTimeFuelScrew
}