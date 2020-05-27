package ua.willeco.clicon.mvp.contract

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import ua.willeco.clicon.enums.AppSection
import ua.willeco.clicon.model.RequestsModels.GetAuthentificateResponse
import ua.willeco.clicon.mvp.repository.BaseResponseRepositoryInterface
import ua.willeco.clicon.mvp.view.BaseView

interface MainActivityContract {
    interface View: BaseView {
        /**
         * Displays an error in the view
         * @param error the error to display in the view
         */
        fun initView()
        /**
         * Displays an error in the view
         * @param error the error to display in the view
         */
        fun changeViewFragment(fragment:Fragment)
    }

    interface Presenter{
        /**
         * Method to prevent action after login auth success
         */
        fun validateResponse(responseGet: GetAuthentificateResponse)
    }

    interface Repository: BaseResponseRepositoryInterface.OnFinishedRequest{
    }
}