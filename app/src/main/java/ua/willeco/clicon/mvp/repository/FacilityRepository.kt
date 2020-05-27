package ua.willeco.clicon.mvp.repository

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ua.willeco.clicon.http.ApiRequests
import ua.willeco.clicon.singletons.CurrentUserSingleton

class FacilityRepository constructor(private val api: ApiRequests):BaseResponseRepositoryInterface{

    private var subscription: Disposable? = null

    fun getListFacilitiesResponse(onFinishedListener: BaseResponseRepositoryInterface.OnFinishedRequest) {
        subscription = api.getFacilitiesList(CurrentUserSingleton.getUserID())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate {
                onFinishedListener.onFailureRequest(null)}
            .subscribe(
                { responseData ->
                    onFinishedListener.onFinishedRequest(responseData)
                    subscription?.dispose()
                },
                {
                    onFinishedListener.onFailureRequest(it)
                    subscription?.dispose()
                }
            )
    }
}