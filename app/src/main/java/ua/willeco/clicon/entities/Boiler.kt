package ua.willeco.clicon.entities

import ua.willeco.clicon.entities.boilerSettings.InnerCircuitSettings
import ua.willeco.clicon.entities.boilerSettings.IgnitionFlowSettings
import ua.willeco.clicon.entities.boilerSettings.MainFlowSettings
import ua.willeco.clicon.entities.boilerSettings.ModulationFlowSettings
import ua.willeco.clicon.entities.boilerSettings.AlarmFlowSettings
import ua.willeco.clicon.entities.simple.Core
import ua.willeco.clicon.entities.simple.Cylinder
import ua.willeco.clicon.enums.AlarmType
import ua.willeco.clicon.enums.BoilerMode
import ua.willeco.clicon.enums.BoilerType
import ua.willeco.clicon.enums.DeviceType
import java.io.Serializable
import java.util.*


class Boiler: Device,Serializable {
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