package com.wildpark.clicon.entities.boilerSettings

import java.io.Serializable

abstract class BoilerSettings :Serializable{
    private val id: Long? = null
    private val clientName = "*default*"
    private val crossAppId = System.currentTimeMillis()
}