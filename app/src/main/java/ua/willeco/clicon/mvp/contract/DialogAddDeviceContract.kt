package ua.willeco.clicon.mvp.contract

import ua.willeco.clicon.model.getRequestsModels.SimpleResponse
import ua.willeco.clicon.mvp.repository.BaseResponseRepositoryInterface

interface DialogAddDeviceContract {
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
        fun setErrorHintToChipIdEditText()

        /**
         * Displays an error in the view
         * @param error the error to display in the view
         */
        fun dismissDialog()
        /**
         * Displays an error in the view
         * @param error the error to display in the view
         */
        fun onClickAddButton()
    }

    interface Presenter{
        /**
         * Method to prevent action after login auth success
         */
        fun provideAddDeviceRequest()
        /**
         * Method to prevent action after login auth success
         */
        fun validateResponse(response: SimpleResponse)
    }

    interface Repository: BaseResponseRepositoryInterface.OnFinishedRequest{
    }
}