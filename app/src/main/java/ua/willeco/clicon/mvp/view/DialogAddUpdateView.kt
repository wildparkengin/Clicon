package ua.willeco.clicon.mvp.view

import androidx.appcompat.app.AlertDialog
import ua.willeco.clicon.enums.AppRequestEventType

interface DialogAddUpdateView {

    interface DialogInitViewElements{
        fun initAuxiliaryElement()
        fun initViewDialogBuilder(title:String):AlertDialog
    }

    interface DialogButtonListeners{
        fun onDialogNegativeClick()
        fun onDialogPositiveClick(type: AppRequestEventType,anyObj:Any?)
    }
}