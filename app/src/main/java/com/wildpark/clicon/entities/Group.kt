package com.wildpark.clicon.entities

import com.wildpark.clicon.entities.widgets.Widget
import java.io.Serializable


class Group :Serializable{
    private val id: Long? = null
    private var name = ""
    private var desc = ""
    private var main = false
    private val widgetList = listOf<Widget>()

    fun createMain(): Group {
        val group = Group()
        group.name = "*main-group*"
        group.desc = "*main-group-desc*"
        group.main = true
        return group
    }
}