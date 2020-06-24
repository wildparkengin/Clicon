package ua.willeco.clicon.mvp.contract

import android.os.Bundle
import android.text.TextWatcher
import ua.willeco.clicon.enums.AppRequestEventType
import ua.willeco.clicon.model.Facility
import ua.willeco.clicon.mvp.repository.BaseResponseRepositoryInterface
import ua.willeco.clicon.mvp.view.BaseView

interface FacilityDialogContract {

    interface View:BaseView{

        fun initViewElement()

        fun initListenersToViewsElements()

        fun setErrorInput()

        fun showToastMessage(message:String)

        fun setAcceptInput()

        fun initData(facility:Facility?)

        fun sendMainViewToUpdateData()
    }

    interface Presenter{

        fun parseArguments(arg:Bundle?)

        fun validateSendData(type: AppRequestEventType, facilityName:String)

        fun validateErrorEditFieldName(value:CharSequence?):Boolean

        fun getDialogTitle(type: AppRequestEventType):String

        fun getTextChangedListener(): TextWatcher
    }

    interface Repository: BaseResponseRepositoryInterface.OnFinishedRequest{
        fun updateFacility(name:String?, mac:String?)
        fun createFacility(name:String?)
    }
}