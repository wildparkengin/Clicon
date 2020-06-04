package ua.willeco.clicon.mvp.presenter

import ua.willeco.clicon.http.ApiRequests
import ua.willeco.clicon.model.RequestsModels.GetDevicesListResponse
import ua.willeco.clicon.model.RequestsModels.GetFacilitiesListResponse
import ua.willeco.clicon.mvp.contract.FacilitiesContract
import ua.willeco.clicon.mvp.repository.BaseResponseRepositoryInterface
import ua.willeco.clicon.mvp.repository.DevicesRepository
import ua.willeco.clicon.mvp.repository.FacilityRepository
import javax.inject.Inject

class FacilitiesPresenter(facilityView:FacilitiesContract.View):FacilitiesContract.Presenter,
    BasePresenter<FacilitiesContract.View>(facilityView),FacilitiesContract.Repository {

    @Inject
    lateinit var api: ApiRequests
    private lateinit var facilityRepository: FacilityRepository
    private lateinit var deviceRepository: DevicesRepository

    override fun onFinishedRequest(responseData: Any) {
        when(responseData){
            is GetFacilitiesListResponse ->{
                validateFacilityResponse(responseData)}
            is GetDevicesListResponse ->{
                validateDevicesResponse(responseData)}
            else ->{
                view.showError("Some error")
            }
        }
        view.closeLoader()
    }

    override fun onFailureRequest(t: String) {
        view.closeLoader()
        view.showError(t)
    }

    override fun getFacilityResponse() {
        view.showLoader()
        facilityRepository = FacilityRepository(api)
        facilityRepository.getListFacilitiesResponse(this)
    }

    override fun validateFacilityResponse(response: GetFacilitiesListResponse) {
        when(response.access){
            false ->{
                response.message?.let { view.showError(it) }
            }
            true ->{
                view.loadFacilitiesRecycler(response)
            }
        }
    }

    override fun getDevicesResponse() {
        deviceRepository= DevicesRepository(api)
        deviceRepository.getListDeviceResponse(this,"wl-aea4a0ce3833")
    }

    override fun validateDevicesResponse(response: GetDevicesListResponse) {
        when(response.access){
            false ->{
                response.message?.let { view.showError(it) }
            }
            true ->{
                view.loadDevicesRecycler(response)
            }
        }
    }
}