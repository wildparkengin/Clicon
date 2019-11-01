package ua.willeco.clicon.entities

import ua.willeco.clicon.enums.UserRole
import java.io.Serializable
import java.util.*


class User :Serializable{
    private val serialVersionUID = -4925793429104298783L
    private val id: Long? = null
    private val login: String? = null
    private val password: String? = null
    private val userRole = UserRole.OWNER.name
    private val email = ""
    private val phoneNumber = ""
    private val firstName = ""
    private val lastName = ""
    private val address = ""
    private val lastLogIn: Date? = null
    private val enabled = true
    private val groupList = mutableListOf<Group>()
    private val deviceContainer = DeviceContainer()
}