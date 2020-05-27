package ua.willeco.clicon.mvp.contract

import androidx.annotation.StringRes
import ua.willeco.clicon.model.RequestsModels.GetAuthentificateResponse
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
         * Displays an error in the view
         * @param errorResId the resource id of the error to display in the view
         */
        fun showError(@StringRes errorResId: Int){
            this.showError(getContext().getString(errorResId))
        }
        /**
         * Method to prevent action after login auth success
         */
        fun toTheNextActivity(isSignIn:Boolean)
    }

    interface Presenter{
        /**
         * Method to prevent action after login auth success
         */
        fun validateResponse(responseGet: GetAuthentificateResponse)
    }

    interface Repository:BaseResponseRepositoryInterface.OnFinishedRequest{
    }
}