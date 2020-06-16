package ua.willeco.clicon.mvp.contract

import ua.willeco.clicon.model.getRequestsModels.GetAuthentificateSimpleResponse
import ua.willeco.clicon.mvp.repository.BaseResponseRepositoryInterface
import ua.willeco.clicon.mvp.view.BaseView


interface SplashActivityContract {
    interface View:BaseView {
        /**
         * Displays an error in the view
         * @param error the error to display in the view
         */
        fun showError(error: String)

        /**
         * Method to prevent action after login auth success
         */
        fun toTheNextActivity(isSignIn:Boolean)
    }

    interface Presenter{
        /**
         * Method to prevent action after login auth success
         */
        fun validateResponse(responseGet: GetAuthentificateSimpleResponse)
    }

    interface Repository:BaseResponseRepositoryInterface.OnFinishedRequest{
        fun saveUserID(id:Long)
    }
}