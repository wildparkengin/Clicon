package ua.willeco.clicon.mvp.presenter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import ua.willeco.clicon.R
import ua.willeco.clicon.enums.AppRequestEventType
import ua.willeco.clicon.http.ApiRequests
import ua.willeco.clicon.model.Facility
import ua.willeco.clicon.mvp.contract.FacilityDialogContract
import ua.willeco.clicon.mvp.repository.FacilityRepository
import ua.willeco.clicon.utility.Constants
import javax.inject.Inject

class FacilityDialogPresenter(facilityDialogView:FacilityDialogContract.View):
    BasePresenter<FacilityDialogContract.View>(facilityDialogView),
    FacilityDialogContract.Presenter,
    FacilityDialogContract.Repository {

    @Inject
    lateinit var api: ApiRequests
    private var facilityRepository: FacilityRepository
    private var tempFacility:Facility? = null

    init {
        facilityRepository = FacilityRepository(api)
    }

    override fun onFinishedRequest(responseData: Any, requestEventType: AppRequestEventType) {
        view.sendMainViewToUpdateData()
    }

    override fun onFailureRequest(t: String) {
        view.showToastMessage(t)
    }

    override fun parseArguments(arg: Bundle?) {
        if (arg!=null){
            try {
                val item = arg.getSerializable(Constants.DIALOG_FACILITY_ARGUMENTS)
                item.let {
                    if (it is Facility){
                        view.initData(it)
                        tempFacility = it
                    }
                }
            }catch (n:NullPointerException){
                view.showToastMessage("Nullpoiner in Arguments")
                n.printStackTrace()
            }
        }else{
            view.showToastMessage("Empty Arguments")
        }
    }

    override fun validateSendData(type: AppRequestEventType, facilityName: String) {
        when(type){
            AppRequestEventType.UPDATE_FACILITY -> {
                if (tempFacility?.name == facilityName){
                    view.showToastMessage("Nothing to change")
                }else{
                    updateFacility(facilityName,tempFacility?.mac)
                }
            }

            AppRequestEventType.ADD_FACILITY ->{
                createFacility(facilityName)
            } else -> return
        }
    }

    override fun updateFacility(name: String?, mac: String?) {
        if (name != null && mac !=null) {
            facilityRepository.updateFacilityResponse(this,name,mac)
        }
    }

    override fun createFacility(name: String?) {
        if (name != null) {
            facilityRepository.createFacilityResponse(this,name)
        }
    }

    override fun validateErrorEditFieldName(value: CharSequence?):Boolean {
        return value.isNullOrEmpty()
    }

    override fun getDialogTitle(type: AppRequestEventType):String {
        return when(type){
            AppRequestEventType.ADD_FACILITY -> view.getViewContext().getString(R.string.promt_popup_menu_add_facility)
            AppRequestEventType.UPDATE_FACILITY -> view.getViewContext().getString(R.string.dialog_title_update_facility)
            else -> "NONE TITLE DIALOG"
        }
    }

    override fun getTextChangedListener(): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val content = s.toString()
                if (content.length >= 3) {
                    view.setAcceptInput()
                } else {
                    view.setErrorInput()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
    }
}