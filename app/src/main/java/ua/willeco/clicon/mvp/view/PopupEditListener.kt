package ua.willeco.clicon.mvp.view

import ua.willeco.clicon.enums.AppRequestEventType

interface PopupEditListener {
    fun deleteItem(itemDelete:AppRequestEventType, macOrChipID:String)
    fun changeItem(itemChange:AppRequestEventType, anyItem: Any)
}