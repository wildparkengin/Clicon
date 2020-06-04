package ua.willeco.clicon.model

import java.io.Serializable

class SensorBoiler500:SensorBasicBoiler(),Serializable{
    val outputDoorTemp:Boolean = false // доступность датчика температуры подачи дверей
    val inputDoorTemp:Boolean = false // доступность датчика температуры обратки всех дверей
    val outputEconomizerTemp:Boolean = false // доступность датчика температуры подачи экономайзера
    val inputEconomizerTemp:Boolean = false // доступность датчика температуры обратки экономайзера
    val secondaryZoneTemp:Boolean = false  // доступность датчика температуры вторичной зоны
    //TODO Ask about this convectiveTemp
    //val convectiveTemp: [true,true,true,true,true,true] // доступность датчиков температуры конвективной зоны


}