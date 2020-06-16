package ua.willeco.clicon.mvp.contract

import ua.willeco.clicon.model.getRequestsModels.GetDevicesListResponse
import ua.willeco.clicon.model.getRequestsModels.GetRoomListResponse
import ua.willeco.clicon.mvp.repository.BaseResponseRepositoryInterface

interface DialogAddFacilityContract {
    interface View{
        /**
         * Displays an error in the view
         * @param error the error to display in the view
         */
        fun initView()
        /**
         * Displays an error in the view
         * @param error the error to display in the view
         */
        fun setErrorHintToNameEditText()
        /**
         * Displays an error in the view
         * @param error the error to display in the view
         */
        fun onClickAddButton()
        /**
         * Displays an error in the view
         * @param error the error to display in the view
         */
        fun onClickCancelButton()
    }

    interface Presenter{
        /**
         * Method to prevent action after login auth success
         */
        fun getFacilityResponse()
        /**
         * Method to prevent action after login auth success
         */
        fun validateFacilityResponse(response: GetRoomListResponse)
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