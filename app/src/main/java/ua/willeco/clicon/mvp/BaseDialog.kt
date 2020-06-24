package ua.willeco.clicon.mvp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import ua.willeco.clicon.mvp.presenter.BasePresenter
import ua.willeco.clicon.mvp.view.BaseView

abstract class BaseDialog<P : BasePresenter<BaseView>> : BaseView, DialogFragment() {

    protected lateinit var presenter: P
    protected abstract fun instantiatePresenter(): P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = instantiatePresenter()
    }
}