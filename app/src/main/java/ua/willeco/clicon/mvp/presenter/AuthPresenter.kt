package ua.willeco.clicon.mvp.presenter

import android.content.Context
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ua.willeco.clicon.http.ApiRequests
import ua.willeco.clicon.model.RequestsModels.AuthentificateResponse
import ua.willeco.clicon.mvp.contract.AuthContract
import ua.willeco.clicon.utility.Converting
import ua.willeco.clicon.utility.Security
import ua.willeco.clicon.utility.Validation
import javax.inject.Inject

class AuthPresenter:AuthContract.Presenter {

    private lateinit var _view:AuthContract.View
    private var subscription: Disposable? = null
    @Inject
    lateinit var cliconApi: ApiRequests

    override fun validateAuthEnteredData(login: String, password: String) {
        var loginCheck: Boolean = false
        var passwordCheck: Boolean = false

        when(login.isEmpty()){
            true->{_view.setErrorMessageToLoginEditText()}
            false->{
                if (Validation.isValidTextInputData(login)){
                    loginCheck = true
                }else{
                    //TODO create show message to data error length
                    _view.showToast("Length")
                }
            }
        }

        when(password.isEmpty()){
            true->{_view.setErrorMessageToPasswordEditText()}
            false->{
                if (Validation.isValidTextInputData(password)){
                    passwordCheck = true
                }else{
                    //TODO create show message to data error length
                    _view.showToast("Length")
                }
            }
        }

        if (loginCheck && passwordCheck){
            getResponseAuth(login, password)
        }
    }

    override fun getResponseAuth(login: String, password: String) {
        val passCrypt = Security.cryptPasswordAES(password)

        _view.showLoader()
        subscription = cliconApi.autentificate(login,passCrypt)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate { _view.closeLoader() }
            .subscribe(
                { responseData ->
                    validateAuth(responseData)
                    _view.closeLoader()
                },
                //TODO set string to error message
                {
                    _view.showToast("Unknown Error")
                    _view.closeLoader()
                }
            )
    }

    override fun validateAuth(data: AuthentificateResponse) {
        when(data.access){
            true ->{
                toMainActivity()
            }
            false ->{
                _view.showToast(data.message)
            }
        }
    }

    override fun toMainActivity() {
        _view.showToast("Ok. Go to the next Activity")
//        try {
//            val  intent = Intent(this,MainActivity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            startActivity(intent)
//        }catch (e:Exception){
//            e.printStackTrace()
//        }
    }

    override fun attach(view: AuthContract.View) {
        this._view = view
    }

    override fun detach() {
        subscription?.dispose()
    }
}