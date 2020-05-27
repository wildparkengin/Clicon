package ua.willeco.clicon.mvp.contract

import androidx.annotation.StringRes
import ua.willeco.clicon.model.RequestsModels.GetAuthentificateResponse
import ua.willeco.clicon.model.RequestsModels.GetFacilitiesListResponse
import ua.willeco.clicon.mvp.repository.BaseResponseRepositoryInterface
import ua.willeco.clicon.mvp.view.BaseView

interface FacilitiesContract {

    interface View: BaseView {
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
         * Displays an error in the view
         * @param errorResId the resource id of the error to display in the view
         */
        fun showError(@StringRes errorResId: Int){
            this.showError(getContext().getString(errorResId))
        }
        /**
         * Method to prevent action after login auth success
         */
        fun showDialogToAddFacility()
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
        fun loadFacilitiesRecycler(facilitiesDataResponse:GetFacilitiesListResponse)
    }

    interface Presenter{
        /**
         * Method to prevent action after login auth success
         */
        fun getFacilityResponse()
        /**
         * Method to prevent action after login auth success
         */
        fun validateFacilityResponse(response:GetFacilitiesListResponse)
    }

    interface Repository: BaseResponseRepositoryInterface.OnFinishedRequest{
    }
}