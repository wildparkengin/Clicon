package ua.willeco.clicon.model

import java.io.Serializable

class Boiler500: Boiler(),Serializable {
    private val burner1:Burner? = null
    private val burner2:Burner? = null
    private val outputDoorTemp:Int = 0 // текущая температура подачи дверей -
    private val inputDoorTemp:Int = 0 // текущая температура обратки всех дверей -
    private val outputEconomizerTemp:Int = 0 // текущая температура подачи экономайзера -
    private val inputEconomizerTemp:Int = 0 // текущая температура обратки экономайзера -
    private val secondaryZoneTemp:Int = 0 // текущая температура вторичной зоны -
    private val secondaryZoneLightSensor:Int = 0 // текущие показатели фотоэлемента вторичной зоны -
    //private val convectiveTemp: [40,40,40,40,40,40], // текущие показатели температур конвективной зоны -
    private val vacuumSensor:Int  = 0 // текущие показатели датчика разрежения [0, 1023] | для пользователя преобразовать в диапазон [-125, 125] -
    private val extraFeedSensor:Boolean =  true // текущее состояние датчика доп подачи распределительного бункера -
    private val lambda:Int = 0 // текущие показатели лямбды (0-100%) -
    private val ashScrewGeneral:Boolean = false // текущее состояние реле общего золоудаления -
    private val secondaryPwm:Int = 0 // текущая мощность дуйчика вторичного воздуха -
    private val exhaustedGasPwm:Int = 0 // текущая частота дымососа отработанных газов (Гц, [0-50]) -
    private val sensorsState:SensorBoiler500? = null
}