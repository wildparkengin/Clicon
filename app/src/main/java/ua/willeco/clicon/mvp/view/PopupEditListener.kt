package ua.willeco.clicon.mvp.view

import ua.willeco.clicon.enums.AppRequestEventType

interface PopupEditListener {
    fun addNewDeviceItem()
    fun deleteItem(itemDeleteType:AppRequestEventType, item:Any)
    fun changeItem(itemChangeType:AppRequestEventType, item:Any)
}