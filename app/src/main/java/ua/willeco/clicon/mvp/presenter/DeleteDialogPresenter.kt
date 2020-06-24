package ua.willeco.clicon.mvp.presenter

import android.os.Bundle
import ua.willeco.clicon.R
import ua.willeco.clicon.enums.AppRequestEventType
import ua.willeco.clicon.enums.DialogViewType
import ua.willeco.clicon.http.ApiRequests
import ua.willeco.clicon.model.Device
import ua.willeco.clicon.model.Facility
import ua.willeco.clicon.model.getRequestsModels.SimpleResponse
import ua.willeco.clicon.mvp.contract.DeleteDialogContract
import ua.willeco.clicon.mvp.repository.DevicesRepository
import ua.willeco.clicon.mvp.repository.FacilityRepository
import ua.willeco.clicon.utility.Constants
import javax.inject.Inject

class DeleteDialogPresenter(deleteDialogView: DeleteDialogContract.View):
    BasePresenter<DeleteDialogContract.View>(deleteDialogView),DeleteDialogContract.Presenter, DeleteDialogContract.Repository{

    @Inject
    lateinit var api: ApiRequests
    private var devicesRepository: DevicesRepository
    private var facilityRepository:FacilityRepository
    private var item:Any? = null

    init {
        devicesRepository = DevicesRepository(api)
        facilityRepository = FacilityRepository(api)
    }

    override fun getTitleDialog(requestType: AppRequestEventType): String {
        var tempTitle = ""
        tempTitle = when(requestType) {
            AppRequestEventType.DELETE_FACILITY -> {
                view.getViewContext().getString(R.string.alert_dialog_title_delete_facility)
            }
            AppRequestEventType.DELETE_DEVICE -> {
                view.getViewContext().getString(R.string.alert_dialog_title_delete_device)
            } else -> {
                "None title"
            }
        }
        return createTitleFromParseArguments(tempTitle)
    }

    override fun getMessageDialogs(requestType: AppRequestEventType): String {
        return when(requestType) {
            AppRequestEventType.DELETE_FACILITY -> {
                view.getViewContext().getString(R.string.alert_dialog_message_delete_facility)
            }
            AppRequestEventType.DELETE_DEVICE -> {
                view.getViewContext().getString(R.string.alert_dialog_message_delete_device)
            } else -> { "None message" }
        }
    }

    override fun createTitleFromParseArguments(title: String): String {
        if (item!=null){
            item.let {
                return when(it){
                    is Facility -> "$title ${it.name}?"
                    is Device -> {
                        "$title ${it.name}?"
                    }else ->{
                        title
                    }
                }
            }
        }
        return ""
    }

    override fun parseArgument(arg:Bundle?){
        if (arg == null) {
            return
        } else {
            item =  arg.getSerializable(Constants.DIALOG_DELETE_ARGUMENTS)
        }
    }

    override fun getCurrentTypeDialog(requestType: AppRequestEventType): DialogViewType? {
        return when(requestType){
            AppRequestEventType.DELETE_FACILITY -> DialogViewType.DELETE_FACILITY
            AppRequestEventType.DELETE_DEVICE -> DialogViewType.DELETE_DEVICE
            else -> null
        }
    }

    override fun validateRequestType() {
        if (item!=null){
            when(item){
                is Device ->{
                    (item as Device).chipId?.let { devicesRepository.deleteDevice(this, it) }
                }
                is Facility ->{
                    (item as Facility).mac?.let { facilityRepository.deleteFacilityResponse(this, it) }
                }
            }
        }
    }

    override fun deleteFacility(mac: String?) {
        mac?.let { facilityRepository.deleteFacilityResponse(this, it) }
    }

    override fun deleteDevice(device_cid: String?) {
        device_cid?.let { devicesRepository.deleteDevice(this, it) }
    }

    override fun onFinishedRequest(responseData: Any, requestEventType: AppRequestEventType) {
        when (responseData) {
            is SimpleResponse -> {
                responseData.let {
                    if (it.access) {
                        view.showCompleteDeleteMessage()
                    } else {
                        view.showErrorMessage(responseData.message)
                    }
                }
            }
        }
    }

    override fun onFailureRequest(t: String) {
        view.showErrorMessage(t)
    }
}