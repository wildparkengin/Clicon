package ua.willeco.clicon.mvp.presenter

import android.content.SharedPreferences
import retrofit2.Retrofit
import ua.willeco.clicon.enums.AppRequestEventType
import ua.willeco.clicon.http.ApiRequests
import ua.willeco.clicon.model.getRequestsModels.GetAuthentificateSimpleResponse
import ua.willeco.clicon.mvp.contract.SplashActivityContract
import ua.willeco.clicon.mvp.repository.AutorizationRepository
import ua.willeco.clicon.singletons.CurrentUserSingleton
import ua.willeco.clicon.utility.Constants
import javax.inject.Inject

class SplashActivityPresenter(splashActivityView:SplashActivityContract.View):SplashActivityContract.Presenter, BasePresenter<SplashActivityContract.View>(splashActivityView),SplashActivityContract.Repository{

    @Inject
    lateinit var api: ApiRequests
    @Inject
    lateinit var apiClient: Retrofit
    @Inject
    lateinit var sharedPreferences: SharedPreferences
    lateinit var authRepository: AutorizationRepository

    override fun onViewCreated() {
        initRequestAuth()
        super.onViewCreated()
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

    override fun validateResponse(responseGet: GetAuthentificateSimpleResponse) {
        view.toTheNextActivity(responseGet.access)
        if (responseGet.access){
            responseGet.user_id?.let { CurrentUserSingleton.init(it) }
        }
    }

    override fun saveUserID(id: Long) {
        TODO("Not yet implemented")
    }

    override fun onFinishedRequest(responseData: Any, requestEventType: AppRequestEventType) {
        if (responseData is GetAuthentificateSimpleResponse){
            validateResponse(responseData)
        }
    }

    override fun onFailureRequest(t: String) {
        view.toTheNextActivity(false)
    }

}