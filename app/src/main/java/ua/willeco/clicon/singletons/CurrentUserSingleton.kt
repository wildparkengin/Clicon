package ua.willeco.clicon.singletons

import ua.willeco.clicon.model.User

object CurrentUserSingleton{

    private var userID : Long = 0

    fun init(id: Long)  {
        userID = id
    }

    fun getUserID(): Long {
        return userID
    }
}