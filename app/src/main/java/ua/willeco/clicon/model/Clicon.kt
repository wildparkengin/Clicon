package ua.willeco.clicon.model

class Clicon:Device(){
    private val indoorTemp:Int? = 0 // текущая температура внутри помещения
    private val humidity:Int? = 0 // текущая влажность внутри помещения
    private val co2Level:Int? = 0 // текущий уровень CO2
    private val outdoorTemp:Int? = 0 // текущая температура снаружи
    private val outdoorConnected:Boolean? =  false // подключён ли датчик наружней температуры
    private val heatingRelay:Boolean? =  false // текущее состояние реле обогрева
    private val coolingRelay:Boolean? =  false // текущее состояние реле охлаждения
    private val ventilationRelay:Boolean =  false // текущее состояние реле проветривания
}