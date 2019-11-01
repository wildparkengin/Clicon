package com.wildpark.clicon.entities

import com.wildpark.clicon.entities.boilerSettings.InnerCircuitSettings
import com.wildpark.clicon.entities.boilerSettings.IgnitionFlowSettings
import com.wildpark.clicon.entities.boilerSettings.MainFlowSettings
import com.wildpark.clicon.entities.boilerSettings.ModulationFlowSettings
import com.wildpark.clicon.entities.boilerSettings.AlarmFlowSettings
import com.wildpark.clicon.entities.simple.Core
import com.wildpark.clicon.entities.simple.Cylinder
import com.wildpark.clicon.enums.AlarmType
import com.wildpark.clicon.enums.BoilerMode
import com.wildpark.clicon.enums.BoilerType
import com.wildpark.clicon.enums.DeviceType
import java.io.Serializable
import java.util.*


class Boiler:Device,Serializable {
    private val id: Long? = null
    private val chipId: String? = null
    private val name = ""
    private val boilerMode = BoilerMode.ANY
    private val previousBoilerMode: BoilerMode? = null
    private val outputTemp = 0 // ON CHART
    private val inputTemp = 0 // ON CHART
    private val burnTemp = 0 // ON CHART
    private val afterBurnTemp = 0 // ON CHART
    private val screwTemp = 0 // ON CHART
    private val pwm = 0
    private val lightSensor = 0
    private val heatWaterTemp = 0 // home
    private val fuelScrewState = false
    private val innerCircuitPumpState = false
    private val ashScrewState = false
    private val heatWaterPumpState = false
    private val ignitionElementState = false
    private val floodingState = false
    private val smokeState = false
    private val alarmState = false

    private val crm310Pwm = 0
    private val exhauster = 0
    private val exhaustGasExhauster = 0
    private val vacuumSensor = 0
    private val extraFeedSensor = false
    private val core = Core()
    private val alarms: Set<AlarmType>? = null

    private val innerCircuitSettings = InnerCircuitSettings()
    private val ignitionFlowSettings = IgnitionFlowSettings()
    private val mainFlowSettings = MainFlowSettings()
    private val modulationFlowSettings = ModulationFlowSettings()
    private val alarmFlowSettings = AlarmFlowSettings()
    private val burner1Cylinder = Cylinder()
    private val burner1Cylinder2 = Cylinder()
    private val pumpProducing = 1000 // in liters
    private val fuelScrewProducing = 100 // in grams per seconds
    private val maxAshSize = 10000 // in grams
    private val ashScrewWorkTime = 0

    private val deviceType = DeviceType.BOILER
    private val boilerType = BoilerType.PRO100
    private val lastUpdate: Date? = null

    override fun getChipId(): String {
        return chipId.toString()
    }
}