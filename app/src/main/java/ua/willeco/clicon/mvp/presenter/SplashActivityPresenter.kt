package ua.willeco.clicon.mvp.presenter

import android.content.SharedPreferences
import io.reactivex.disposables.Disposable
import ua.willeco.clicon.http.ApiRequests
import ua.willeco.clicon.model.RequestsModels.GetAuthentificateResponse
import ua.willeco.clicon.mvp.contract.SplashActivityContract
import ua.willeco.clicon.mvp.repository.AutorizationRepository
import ua.willeco.clicon.utility.Constants
import javax.inject.Inject

class SplashActivityPresenter(splashActivityView:SplashActivityContract.View):SplashActivityContract.Presenter, BasePresenter<SplashActivityContract.View>(splashActivityView),SplashActivityContract.Repository{

    @Inject
    lateinit var api: ApiRequests
    @Inject
    lateinit var sharedPreferences: SharedPreferences

    lateinit var authRepository: AutorizationRepository

    private var subscription: Disposable? = null

    override fun onViewCreated() {
        initRequestAuth()
        super.onViewCreated()
    }

    override fun onViewDestroyed() {
        super.onViewDestroyed()
        this.subscription?.dispose()
    }

    private fun initRequestAuth(){
        val userData = getHashLoginData()

        if (userData.isNullOrEmpty()){
            view.toTheNextActivity(false)
        }else{
            authRepository = AutorizationRepository(api)
            authRepository.getResponseAuth(this,userData)
        }
    }

    private fun getHashLoginData():String?{
       return sharedPreferences.getString(Constants.PREFERENCES_LOGIN_HASH,"")
    }

    override fun validateResponse(responseGet: GetAuthentificateResponse) {
        if (responseGet.access!=null){
            view.toTheNextActivity(responseGet.access)
        }else{
            view.toTheNextActivity(false)
        }
    }

    override fun onFinishedRequest(response: Any) {
        if (response is GetAuthentificateResponse){
            validateResponse(response)
        }
    }

    override fun onFailureRequest(t: Throwable?) {
        view.toTheNextActivity(false)
    }

}