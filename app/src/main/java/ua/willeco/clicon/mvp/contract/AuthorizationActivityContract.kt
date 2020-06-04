package ua.willeco.clicon.mvp.contract

import ua.willeco.clicon.model.RequestsModels.GetAuthentificateSimpleResponse
import ua.willeco.clicon.mvp.repository.BaseResponseRepositoryInterface
import ua.willeco.clicon.mvp.view.BaseView

interface AuthorizationActivityContract {

    interface View:BaseView{
        /**
         * Method to prevent action after login auth success
         */
        fun initView()
        /**
         * Method to show load animation until get response
         */
        fun showToast(message: String?)
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
        fun setErrorMessageToLoginEditText()

        /**
         * Method to close animation after get response
         */
        fun setErrorMessageToPasswordEditText()

        /**
         * Method to prevent action after login auth success
         */
        fun toMainActivity()
        /**
         * Method to prevent action after login auth success
         */
        fun onClickButtonAuth()
        /**
         * Method to prevent action after login auth success
         */
        fun getStateCheckBox():Boolean
    }

    interface Presenter{
        /**
         * Method to validate entered data from user
         */
        fun saveUserCredential()
        /**
         * Method to validate entered data from user
         */
        fun validateAuthEnteredData(login:String,password:String)
        /**
         * Method to prevent action after login auth success
         */
        fun validateAuth(data:GetAuthentificateSimpleResponse)
    }

    interface Repository:BaseResponseRepositoryInterface.OnFinishedRequest{
//        /**
//         * Method to prevent action after login auth success
//         */
//        fun getResponseAuth(onFinishedListener:BaseResponseRepositoryInterface.OnFinishedRequest, credential:String)
    }
}