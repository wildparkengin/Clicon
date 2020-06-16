package ua.willeco.clicon.factory

import androidx.fragment.app.DialogFragment
import ua.willeco.clicon.enums.AppRequestEventType
import ua.willeco.clicon.enums.DialogViewType
import ua.willeco.clicon.mvp.view.DialogAddUpdateView
import ua.willeco.clicon.ui.DialogAddDevice
import ua.willeco.clicon.ui.DialogAddUpdateFacility

class CustomDialogFactory{
    companion object{
        fun createCustomDialog(listener:DialogAddUpdateView.DialogButtonListeners, typeDialog: DialogViewType, eventType: AppRequestEventType,item:Any?):DialogFragment? = when(typeDialog){
            DialogViewType.NONE -> null
            DialogViewType.FACILITY_DIALOG -> DialogAddUpdateFacility.newInstance(listener,eventType,item)
            DialogViewType.DEVICE_DIALOG -> DialogAddDevice(listener,eventType)
        }
    }
}