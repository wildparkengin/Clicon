package ua.willeco.clicon.model

import ua.willeco.clicon.enums.BoilerMode
import java.io.Serializable

abstract class Boiler: Device(),Serializable {
    private val boilerMode = BoilerMode.ANY
    private val pwm:Int = 0
    private val fuelScrew:Boolean = false
    private val ashScrew:Boolean = false
    private val heatingElement:Boolean = false // текущее состояние нагревающего элемента
    private val innerCircuitPump:Boolean = false // текущее состояние реле насоса внутреннего контура +
    private val heatWaterPump:Boolean = false // текущее состояние реле ГВС
    private val outputTemp:Int = 0 // текущая температура подачи +
    private val inputTemp:Int = 0 // текущая температура обратки +
    private val fuelScrewTemp:Int = 0 // текущая температура шнека подачи
    private val burnerTemp:Int = 0 // текущая температура до теплообменника
    private val afterBurnTemp:Int = 0 // текущая температура после теплообменника
    private val heatWaterTemp:Int = 0 // текущая температура ГВС
    private val bunkerFill:Boolean = false // состояние наполненности малого бункера с топливом (true - Достаточно, реле доп подачи выключено)

}