package ua.willeco.clicon.mvp

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ua.willeco.clicon.mvp.presenter.BasePresenter
import ua.willeco.clicon.mvp.view.BaseView

abstract class BaseActivity<P : BasePresenter<BaseView>> : BaseView, AppCompatActivity() {

    protected lateinit var presenter: P
    protected abstract fun instantiatePresenter(): P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = instantiatePresenter()
    }

    /**
     * Instantiates the presenter the Activity is based on.
     */

    override fun getContext(): Context {
        return this
    }
}