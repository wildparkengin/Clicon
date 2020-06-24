package ua.willeco.clicon.mvp.view

import ua.willeco.clicon.enums.DialogViewType

interface DialogsViewInterface {
    interface DialogButtonListeners{
        fun onDialogPositiveClick(type: DialogViewType,anyObj:Any?)
    }
}