package ua.willeco.clicon.factory

import androidx.fragment.app.DialogFragment
import ua.willeco.clicon.enums.AppRequestEventType
import ua.willeco.clicon.enums.DialogViewType
import ua.willeco.clicon.mvp.view.DialogsViewInterface
import ua.willeco.clicon.ui.DeleteDialog
import ua.willeco.clicon.ui.DeviceDialog
import ua.willeco.clicon.ui.FacilityDialog

class CustomDialogFactory{
    companion object{
        fun createCustomDialog(listener:DialogsViewInterface.DialogButtonListeners, typeDialog: DialogViewType, eventType: AppRequestEventType, item:Any?):DialogFragment? = when(typeDialog){
            DialogViewType.ADD_FACILITY -> FacilityDialog.newInstance(listener,eventType,item)
            DialogViewType.UPDATE_FACILITY -> FacilityDialog.newInstance(listener,eventType,item)
            DialogViewType.DELETE_FACILITY -> DeleteDialog.newInstance(listener,eventType,item)
            DialogViewType.ADD_DEVICE -> DeviceDialog.newInstance(listener,eventType,item)
            DialogViewType.UPDATE_DEVICE -> DeviceDialog.newInstance(listener,eventType,item)
            DialogViewType.DELETE_DEVICE -> DeleteDialog.newInstance(listener,eventType,item)
        }
    }
}