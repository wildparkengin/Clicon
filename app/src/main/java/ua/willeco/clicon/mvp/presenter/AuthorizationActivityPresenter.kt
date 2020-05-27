package ua.willeco.clicon.mvp.presenter

import android.content.SharedPreferences
import io.reactivex.disposables.Disposable
import ua.willeco.clicon.http.ApiRequests
import ua.willeco.clicon.model.RequestsModels.GetAuthentificateResponse
import ua.willeco.clicon.mvp.contract.AuthorizationActivityContract
import ua.willeco.clicon.mvp.repository.AutorizationRepository
import ua.willeco.clicon.utility.Constants
import ua.willeco.clicon.utility.Security
import ua.willeco.clicon.utility.Validation
import javax.inject.Inject

class AuthorizationActivityPresenter(authorizationActivityView:AuthorizationActivityContract.View):AuthorizationActivityContract.Presenter,BasePresenter<AuthorizationActivityContract.View>(authorizationActivityView),AuthorizationActivityContract.Repository {

    @Inject
    lateinit var api: ApiRequests
    @Inject
    lateinit var sharedPreferences: SharedPreferences
    lateinit var authRepository: AutorizationRepository
    private lateinit var passCrypt:String
    private var subscription: Disposable? = null

    override fun validateAuthEnteredData(login: String, password: String) {
        var loginCheck: Boolean = false
        var passwordCheck: Boolean = false

        when(login.isEmpty()){
            true->{view.setErrorMessageToLoginEditText()}
            false->{
                if (Validation.isValidTextInputData(login)){
                    loginCheck = true
                }else{
                    //TODO create show message to data error length
                    view.showToast("Length")
                }
            }
        }

        when(password.isEmpty()){
            true->{view.setErrorMessageToPasswordEditText()}
            false->{
                if (Validation.isValidTextInputData(password)){
                    passwordCheck = true
                }else{
                    //TODO create show message to data error length
                    view.showToast("Length")
                }
            }
        }

        if (loginCheck && passwordCheck){
            getResponseAuth(login, password)
        }
    }

    private fun getResponseAuth(login: String, password: String) {
        passCrypt = Security.cryptPasswordAES("$login&$password")
        view.showLoader()

        authRepository = AutorizationRepository(api)
        authRepository.getResponseAuth(this,passCrypt)
    }

    override fun validateAuth(data: GetAuthentificateResponse) {
        when(data.access){
            true ->{
                view.toMainActivity()
                saveUserCredential()
            }
            false ->{
                view.showToast(data.message)
            }
        }
    }

    override fun saveUserCredential() {
        if (view.getStateCheckBox()){
            val edit = sharedPreferences.edit()
            edit.putString(Constants.PREFERENCES_LOGIN_HASH,passCrypt)
            edit.apply()
        }
    }

    override fun onFinishedRequest(response: Any) {
        if (response is GetAuthentificateResponse){
            validateAuth(response)
        }
        view.closeLoader()
    }

    override fun onFailureRequest(t: Throwable?) {
        view.closeLoader()
        view.showToast(t?.localizedMessage)
    }

    override fun onViewDestroyed() {
        super.onViewDestroyed()
        this.subscription?.dispose()
    }
}