package ua.willeco.clicon.mvp.presenter

import ua.willeco.clicon.http.ApiRequests
import ua.willeco.clicon.model.RequestsModels.GetFacilitiesListResponse
import ua.willeco.clicon.mvp.contract.FacilitiesContract
import ua.willeco.clicon.mvp.repository.FacilityRepository
import javax.inject.Inject

class FacilitiesPresenter(facilityView:FacilitiesContract.View):FacilitiesContract.Presenter,
    BasePresenter<FacilitiesContract.View>(facilityView),FacilitiesContract.Repository {

    @Inject
    lateinit var api: ApiRequests
    lateinit var facilityRepository: FacilityRepository

    override fun onFinishedRequest(response: Any) {
        if (response is GetFacilitiesListResponse){
            validateFacilityResponse(response)
        }else{
            view.showError("Some error")
        }
        view.closeLoader()
    }

    override fun onFailureRequest(t: Throwable?) {
        view.closeLoader()
        t?.message?.let { view.showError(it) }
    }

    override fun getFacilityResponse() {
        view.showLoader()
        facilityRepository = FacilityRepository(api)
        facilityRepository.getListFacilitiesResponse(this)
    }

    override fun validateFacilityResponse(response: GetFacilitiesListResponse) {
        when(response.access){
            false ->{
                view.showError(response.message)
            }
            true ->{
                view.loadFacilitiesRecycler(response)
            }
        }
    }
}