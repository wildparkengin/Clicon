package ua.willeco.clicon.mvp.repository

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ua.willeco.clicon.http.ApiRequests
import ua.willeco.clicon.singletons.CurrentUserSingleton

class AutorizationRepository constructor(private val api:ApiRequests):BaseResponseRepositoryInterface{

    private var subscription: Disposable? = null

    fun getResponseAuth(onFinishedListener: BaseResponseRepositoryInterface.OnFinishedRequest, credential:String) {
        subscription = api.getAuthentication(credential)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate {
                onFinishedListener.onFailureRequest(null)}
            .subscribe(
                { responseData ->
                    onFinishedListener.onFinishedRequest(responseData)
                    responseData.user_id?.let { saveUserID(it) }
                    subscription?.dispose()
                },
                {
                    onFinishedListener.onFailureRequest(it)
                    subscription?.dispose()
                }
            )
    }

    private fun saveUserID(id:Long){
        CurrentUserSingleton.init(id)
    }
}