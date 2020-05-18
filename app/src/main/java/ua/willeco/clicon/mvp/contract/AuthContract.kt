package ua.willeco.clicon.mvp.contract

import ua.willeco.clicon.model.RequestsModels.AuthentificateResponse
import ua.willeco.clicon.model.User
import ua.willeco.clicon.mvp.presenter.BasePresenter

interface AuthContract {
    interface View {
        /**
         * Method to prevent action after login auth success
         */
        fun onClickSaveLoginData()
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

    }

    interface Presenter: BasePresenter<View>{
        /**
         * Method to validate entered data from user
         */
        fun validateAuthEnteredData(login:String,password:String)
        /**
         * Method to prevent action after login auth success
         */
        fun getResponseAuth(login: String, password: String)
        /**
         * Method to prevent action after login auth success
         */
        fun validateAuth(data:AuthentificateResponse)

        /**
         * Method to prevent action after login auth success
         */
        fun toMainActivity()
    }

    interface Model{
        /**
         * Method to prevent action after login auth success
         */
        fun onSaveAuthData(user:User)
        /**
         * Method to prevent action after login auth success
         */
        fun getSavedLoginData():User
    }
}