package com.wildpark.clicon.entities.simple

import com.wildpark.clicon.enums.AlarmType

class Core {
    private val serialVersionUID = -1879624907663845598L
    private val serialPortId = "/dev/ttyUSB0"
    private val serialPortSpeed = 9600
    private val debug = false
    private var crm311Online = false
    private var crm310Online = false
    private var mm311Online = false
    private var prm311Online = false

    // sensors
    private val inputEnable = true
    private val outputEnable = true
    private val fuelScrewEnable = true
    private val burnerEnable = true
    private val afterBurnEnable = true
    private val heatWaterEnable = true

    fun setAllModulesOnline() {
        this.crm311Online = true
        this.crm310Online = true
        this.mm311Online = true
        this.prm311Online = true
    }

    fun setModuleOffline(alarmType: AlarmType) {
        when (alarmType) {
            AlarmType.MODULE_CRM311 -> this.crm311Online = false
            AlarmType.MODULE_CRM310 -> this.crm310Online = false
            AlarmType.MODULE_MM311 -> this.mm311Online = false
            AlarmType.MODULE_PRM311 -> this.prm311Online = false
        }
    }
}