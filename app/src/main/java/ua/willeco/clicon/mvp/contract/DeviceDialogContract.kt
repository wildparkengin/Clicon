package ua.willeco.clicon.mvp.contract

import android.os.Bundle
import android.text.TextWatcher
import ua.willeco.clicon.enums.AppRequestEventType
import ua.willeco.clicon.model.Facility
import ua.willeco.clicon.mvp.repository.BaseResponseRepositoryInterface
import ua.willeco.clicon.mvp.view.BaseView

interface DeviceDialogContract {

    interface View: BaseView {
        fun initViewElement()

        fun initListenersToViewsElements()

        fun setErrorToNameInput()

        fun setErrorToDeviceIdInput()

        fun showToastMessage(message:String)

        fun setAcceptNameInput()

        fun setAcceptDeviceIdInput()

        fun initData(facility: Facility?)

        fun sendMainViewToUpdateData()
    }

    interface Presenter{

        fun parseArguments(arg: Bundle?)

        fun validateSendData(type: AppRequestEventType, facilityName:String)

        fun validateErrorEditFieldName(value:CharSequence?):Boolean

        fun getDialogTitle(type: AppRequestEventType):String

        fun getTextNameDeviceChangedListener(): TextWatcher

        fun getTextDeviceIDDeviceChangedListener(): TextWatcher
    }

    interface Repository: BaseResponseRepositoryInterface.OnFinishedRequest{

        fun updateDevice(name:String?, mac:String?)

        fun createDevice(name:String?)
    }
}