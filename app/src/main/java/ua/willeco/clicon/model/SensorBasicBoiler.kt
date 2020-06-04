package ua.willeco.clicon.model

abstract class SensorBasicBoiler {
    val outputTemp:Boolean = false   // доступность датчика температуры подачи
    val inputTemp:Boolean = false     // доступность датчика температуры обратки
}