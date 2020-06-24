package ua.willeco.clicon.mvp.contract

import android.os.Bundle
import android.text.TextWatcher
import androidx.appcompat.app.AlertDialog
import ua.willeco.clicon.enums.AppRequestEventType
import ua.willeco.clicon.enums.DialogViewType
import ua.willeco.clicon.mvp.repository.BaseResponseRepositoryInterface
import ua.willeco.clicon.mvp.view.BaseView

interface DeleteDialogContract {
    interface View: BaseView {

        fun initViewDialogBuilder():AlertDialog

        fun onAcceptButtonClick()

        fun closeDialog()

        fun showCompleteDeleteMessage()

        fun showErrorMessage(message:String)
    }

    interface Presenter{

        fun getTitleDialog(requestType:AppRequestEventType):String

        fun getMessageDialogs(requestType: AppRequestEventType):String

        fun createTitleFromParseArguments(title:String):String

        fun getCurrentTypeDialog(requestType:AppRequestEventType):DialogViewType?

        fun validateRequestType()

        fun parseArgument(arg:Bundle?)

    }

    interface Repository: BaseResponseRepositoryInterface.OnFinishedRequest{
        fun deleteFacility(mac:String?)
        fun deleteDevice(device_cid:String?)
    }
}