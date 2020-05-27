package ua.willeco.clicon.mvp.presenter

import ua.willeco.clicon.model.RequestsModels.GetAuthentificateResponse
import ua.willeco.clicon.mvp.contract.MainActivityContract

//SplashPresenter(splashActivityView:SplashActivityContract.View):SplashActivityContract.Presenter, BasePresenter<SplashActivityContract.View>(splashActivityView)
class MainActivityPresenter(mainActivityView:MainActivityContract.View):MainActivityContract.Presenter,BasePresenter<MainActivityContract.View>(mainActivityView){
    override fun validateResponse(responseGet: GetAuthentificateResponse) {
        TODO("Not yet implemented")
    }
}