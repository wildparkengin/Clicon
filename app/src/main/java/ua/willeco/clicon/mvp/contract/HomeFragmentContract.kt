package ua.willeco.clicon.mvp.contract

import androidx.fragment.app.DialogFragment
import ua.willeco.clicon.enums.AppRequestEventType
import ua.willeco.clicon.enums.DialogViewType
import ua.willeco.clicon.model.getRequestsModels.GetDevicesListResponse
import ua.willeco.clicon.model.getRequestsModels.GetRoomListResponse
import ua.willeco.clicon.model.getRequestsModels.FacilityCRUDResponse
import ua.willeco.clicon.model.getRequestsModels.SimpleResponse
import ua.willeco.clicon.mvp.presenter.HomeFragmentPresenter
import ua.willeco.clicon.mvp.repository.BaseResponseRepositoryInterface
import ua.willeco.clicon.mvp.view.BaseView

interface HomeFragmentContract {

    interface View:BaseView{
        /**
         * Method to prevent action after login auth success
         */
        fun instantiatePresenter(): HomeFragmentPresenter
        /**
         * Method to prevent action after login auth success
         */
        fun initView()
        /**
         * Displays an error in the view
         * @param error the error to display in the view
         */
        fun showToastMessage(messageType: AppRequestEventType)
        /**
         * Displays an error in the view
         * @param error the error to display in the view
         */
        fun showToastError(error: String)
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
        fun showDialogAddUpdate(type: DialogViewType, eventType: AppRequestEventType, mObj:Any?)
        /**
         * Method to close animation after get response
         */
        fun showDialogDelete(eventType: AppRequestEventType, macOrChipID:String)
        /**
         * Method to close animation after get response
         */
        fun loadFacilitiesRecycler(facilityData: GetRoomListResponse)
        /**
         * Method to close animation after get response
         */
        fun loadDevicesRecycler(devicesDataResponse: GetDevicesListResponse)
    }

    interface Presenter{
        /**
         * Method to prevent action after login auth success
         */
        fun getFacilityListResponse()
        /**
         * Method to prevent action after login auth success
         */
        fun getDevicesListResponse(mac:String)
        /**
         * Method to prevent action after login auth success
         */
        fun validateGetFacilityResponse(response: GetRoomListResponse)
        /**
         * Method to prevent action after login auth success
         */
        fun validateGetDevicesResponse(response: GetDevicesListResponse)

        /**
         * Method to prevent action after login auth success
         */
        fun validateCRUDRoomResponse(responseFacility: FacilityCRUDResponse,requestType: AppRequestEventType)
        /**
         * Method to prevent action after login auth success
         */
        fun validateCRUDDevicesResponse(response: SimpleResponse,requestType: AppRequestEventType)
        /**
         * Method to prevent action after login auth success
         */
        fun getArgumentsFromDialog(dialog:DialogFragment?)
    }

    interface Repository: BaseResponseRepositoryInterface.OnFinishedRequest{
        /**
         * Method to prevent action after login auth success
         */
        fun addNewFacilityRequest(roomName:String)
        /**
         * Method to prevent action after login auth success
         */
        fun updateFacilityRequest(any: Any?)
        /**
         * Method to prevent action after login auth success
         */
        fun deleteFacilityRequest(mac:String)
        /**
         * Method to prevent action after login auth success
         */
        fun addNewDevicesRequest(device_cid:String, deviceName:String)
        /**
         * Method to prevent action after login auth success
         */
        fun deleteDeviceRequest(device_cid: String)

    }
}