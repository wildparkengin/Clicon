package ua.willeco.clicon.mvp.presenter

import androidx.fragment.app.DialogFragment
import ua.willeco.clicon.enums.AppRequestEventType
import ua.willeco.clicon.http.ApiRequests
import ua.willeco.clicon.model.Facility
import ua.willeco.clicon.model.getRequestsModels.*
import ua.willeco.clicon.mvp.contract.HomeFragmentContract
import ua.willeco.clicon.mvp.repository.DevicesRepository
import ua.willeco.clicon.mvp.repository.FacilityRepository
import ua.willeco.clicon.ui.DialogAddDevice
import ua.willeco.clicon.ui.DialogAddUpdateFacility
import javax.inject.Inject

class HomeFragmentPresenter(facilityView:HomeFragmentContract.View):HomeFragmentContract.Presenter,
    BasePresenter<HomeFragmentContract.View>(facilityView),HomeFragmentContract.Repository {

    @Inject
    lateinit var api: ApiRequests
    private var facilityRepository: FacilityRepository
    private var deviceRepository: DevicesRepository

    init {
        facilityRepository = FacilityRepository(api)
        deviceRepository = DevicesRepository(api)
    }

    override fun addNewFacilityRequest(facilityName:String) {
        facilityRepository.createFacilityResponse(this,facilityName)
    }

    override fun updateFacilityRequest(any: Any?) {
        any.let {
            if (it is Facility){
                facilityRepository.updateFacilityResponse(
                    this,
                    it.name.toString(),
                    it.mac.toString()
                )
            }
        }
    }

    override fun deleteFacilityRequest(mac: String) {
        facilityRepository.deleteFacilityResponse(this,mac)
    }

    override fun deleteDeviceRequest(device_cid: String) {
        deviceRepository.deleteDevice(this,device_cid)
    }

    override fun addNewDevicesRequest(device_cid:String, deviceName:String) {
        deviceRepository.createDevice(this,device_cid,deviceName)
    }

    override fun onFinishedRequest(responseData: Any, requestEventType: AppRequestEventType) {
        when(responseData){
            is GetRoomListResponse ->{
                validateGetFacilityResponse(responseData)}
            is GetDevicesListResponse ->{
                validateGetDevicesResponse(responseData)}
            is FacilityCRUDResponse ->{
                validateCRUDRoomResponse(responseData,requestEventType)}
            is DeviceCRUDResponce ->{
                validateCRUDDevicesResponse(responseData,requestEventType)
            }

            else ->{
                view.showToastError("Unnamed response")
            }
        }
        view.closeLoader()
    }

    override fun onFailureRequest(t: String) {
        view.closeLoader()
        view.showToastError(t)
    }

    override fun getFacilityListResponse() {
        view.showLoader()
        facilityRepository.getFacilityListResponse(this)
    }

    override fun getDevicesListResponse(mac: String) {
        view.showLoader()
        deviceRepository.getListDeviceResponse(this,mac)
    }

    override fun validateGetFacilityResponse(response: GetRoomListResponse) {
        when(response.access){
            false ->{
                response.message?.let { view.showToastError(it) }
            }
            true ->{
                view.loadFacilitiesRecycler(response)
            }
        }
    }

    override fun validateGetDevicesResponse(response: GetDevicesListResponse) {
        when(response.access){
            false ->{
                response.message?.let { view.showToastError(it) }
            }
            true ->{
                view.loadDevicesRecycler(response)
            }
        }
    }

    override fun validateCRUDRoomResponse(responseFacility: FacilityCRUDResponse,requestType: AppRequestEventType) {
        when(responseFacility.access){
            false ->{
                responseFacility.message.let { view.showToastError(it) }
            }
            true ->{
                getFacilityListResponse()
                view.showToastMessage(requestType)
            }
        }
    }

    override fun validateCRUDDevicesResponse(response: SimpleResponse,requestType: AppRequestEventType) {
        when(response.access){
            false ->{
                response.message.let { view.showToastError(it) }
            }
            true ->{
                view.showToastMessage(requestType)
            }
        }
    }

    override fun getArgumentsFromDialog(dialog: DialogFragment?) {
        when(dialog){
            is DialogAddUpdateFacility ->{
                if (dialog.arguments !=null){
                    dialog.arguments?.getString("roomName")?.let { addNewFacilityRequest(it) }
                }else{
                    getFacilityListResponse()
                }
            }
            is DialogAddDevice ->{ }
        }
    }
}