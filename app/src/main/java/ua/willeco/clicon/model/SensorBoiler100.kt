package ua.willeco.clicon.model

import java.io.Serializable

class SensorBoiler100: SensorBasicBoiler(),Serializable {
    val burnerTemp:Boolean = false    // доступность датчика температуры до теплообменника
    val afterBurnTemp:Boolean = false // доступность датчика температуры после теплообменника
    val fuelScrewTemp:Boolean = false // доступность датчика температуры шнека подачи
    val heatWaterTemp:Boolean = false // доступность датчика температуры ГВС
}