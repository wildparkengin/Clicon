package ua.willeco.clicon.model

import ua.willeco.clicon.enums.BoilerMode
import java.io.Serializable

class Burner:Serializable {
    private val boilerMode: BoilerMode? = null  // текущий режим в горелке 1
    private val pwm:Int? = 0 // текущая мощность
    private val fuelScrew:Boolean? = false // текущее состояние реле подачи топлива
    private val ashScrew:Boolean? =  false // текущее состояние реле золоудаления
    private val heatingElement:Boolean? = false // текущее состояние нагревающего элемента
    private val fireBar:String? = "fold" // текущее положение цилиндра колосника
    private val fuelGate:String? = "open" // текущее положение цилиндра заслонки
    private val insideTemp:Int? = 0 // текущая температура над горелкой
    private val lightSensor:Int? = 0 // текущие показатели фотоэлемента над горелкой
    private val fuelScrewTemp:Int? = 0 // текущая температура шнека подачи
    private val extraFuelSensor:Boolean? = false // датчик доп подачи для бункера
    private val extraFuelScrew:Boolean? = false // шнек доп подачи для бункера
    private val sensorsState:SensorBurner? = null
}