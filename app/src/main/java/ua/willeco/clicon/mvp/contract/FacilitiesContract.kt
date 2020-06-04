package ua.willeco.clicon.mvp.contract

import ua.willeco.clicon.model.RequestsModels.GetDevicesListResponse
import ua.willeco.clicon.model.RequestsModels.GetFacilitiesListResponse
import ua.willeco.clicon.mvp.presenter.FacilitiesPresenter
import ua.willeco.clicon.mvp.repository.BaseResponseRepositoryInterface
import ua.willeco.clicon.mvp.view.BaseView
import ua.willeco.clicon.mvp.view.MenuClickListenerInterface

interface FacilitiesContract {

    interface View:BaseView,MenuClickListenerInterface{

        /**
         * Method to prevent action after login auth success
         */
        fun instantiatePresenter(): FacilitiesPresenter
        /**
         * Method to prevent action after login auth success
         */
        fun initView()
        /**
         * Displays an error in the view
         * @param error the error to display in the view
         */
        fun showError(error: String)
        /**
         * Method to show load animation until get response
         */
        fun showLoader()
        /**
         * Method to close animation after get response
         */
        fun closeLoader()

        /**
         * Method to close animation after get response
         */
        fun loadFacilitiesRecycler(facilityData: GetFacilitiesListResponse)

        /**
         * Method to close animation after get response
         */
        fun loadDevicesRecycler(devicesDataResponse: GetDevicesListResponse)

        /**
         * Method to close animation after get response
         */
        fun showPopup()
        /**
         * Method to close animation after get response
         */
        fun initPopupView()

    }

    interface Presenter{
        /**
         * Method to prevent action after login auth success
         */
        fun getFacilityResponse()
        /**
         * Method to prevent action after login auth success
         */
        fun validateFacilityResponse(response: GetFacilitiesListResponse)
        /**
         * Method to prevent action after login auth success
         */
        fun getDevicesResponse()
        /**
         * Method to prevent action after login auth success
         */
        fun validateDevicesResponse(response: GetDevicesListResponse)
    }

    interface Repository: BaseResponseRepositoryInterface.OnFinishedRequest{
    }
}