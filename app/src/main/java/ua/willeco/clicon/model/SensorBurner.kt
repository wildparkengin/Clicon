package ua.willeco.clicon.model

import java.io.Serializable

class SensorBurner:Serializable {
    val insideTemp:Boolean = false  // доступность датчика температуры над горелкой
    val fuelScrewTemp:Boolean = false// доступность датчика температуры шнека подачи
}