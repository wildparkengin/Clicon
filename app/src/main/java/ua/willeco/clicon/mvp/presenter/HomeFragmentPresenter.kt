package ua.willeco.clicon.mvp.presenter

import ua.willeco.clicon.enums.AppRequestEventType
import ua.willeco.clicon.enums.DialogViewType
import ua.willeco.clicon.http.ApiRequests
import ua.willeco.clicon.model.Facility
import ua.willeco.clicon.model.getRequestsModels.GetDevicesListResponse
import ua.willeco.clicon.model.getRequestsModels.GetFacilityListResponse
import ua.willeco.clicon.model.setRequestModel.DeviceRequestModel
import ua.willeco.clicon.mvp.contract.HomeFragmentContract
import ua.willeco.clicon.mvp.repository.DevicesRepository
import ua.willeco.clicon.mvp.repository.FacilityRepository
import javax.inject.Inject

class HomeFragmentPresenter(homeView:HomeFragmentContract.View):HomeFragmentContract.Presenter,
    BasePresenter<HomeFragmentContract.View>(homeView),HomeFragmentContract.Repository {

    @Inject
    lateinit var api: ApiRequests
    private var facilityRepository: FacilityRepository
    private var deviceRepository: DevicesRepository
    private var tempFacilitiesList:ArrayList<Facility>? = null

    init {
        facilityRepository = FacilityRepository(api)
        deviceRepository = DevicesRepository(api)
    }

    override fun deleteDeviceRequest(device_cid: String) {
        deviceRepository.deleteDevice(this,device_cid)
    }

    override fun addNewDevicesRequest(device: DeviceRequestModel) {
        deviceRepository.createDevice(this,device)
    }

    override fun onFinishedRequest(responseData: Any, requestEventType: AppRequestEventType) {
        when(responseData){
            is GetFacilityListResponse ->{
                validateGetFacilityResponse(responseData)
            }
            is GetDevicesListResponse ->{
                validateGetDevicesResponse(responseData)}
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

    override fun validateGetFacilityResponse(response: GetFacilityListResponse) {
        when(response.access){
            false ->{
                response.message?.let { view.showToastError(it) }
            }
            true ->{
                view.loadFacilitiesRecycler(response)
                tempFacilitiesList = response.facilityList
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

    override fun validateDialogsPositiveClick(typeDialog: DialogViewType, anyObj: Any?) {
        when(typeDialog){
            DialogViewType.ADD_FACILITY,
            DialogViewType.UPDATE_FACILITY,
            DialogViewType.DELETE_FACILITY ->{
                getFacilityListResponse()
            }
            DialogViewType.ADD_DEVICE,
            DialogViewType.UPDATE_DEVICE,
            DialogViewType.DELETE_DEVICE ->{
                getDevicesListResponse("")
            }
        }
    }

    override fun getTempFacilityList(): ArrayList<Facility>? {
        return tempFacilitiesList
    }
}