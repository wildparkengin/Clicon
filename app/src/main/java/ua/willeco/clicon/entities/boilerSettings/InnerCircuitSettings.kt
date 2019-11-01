package ua.willeco.clicon.entities.boilerSettings

import ua.willeco.clicon.entities.boilerSettings.BoilerSettings
import java.io.Serializable

class InnerCircuitSettings: BoilerSettings(),Serializable{
    private val pumpActivationTemp = 40 // minTemp
    private val modulationWaterTemp = 40 // maxTemp
    private val heatWaterActivationTemp = 0
}