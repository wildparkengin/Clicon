package ua.willeco.clicon.mvp.presenter

interface BasePresenter<in T> {
    fun attach(view:T)
    fun detach()
}