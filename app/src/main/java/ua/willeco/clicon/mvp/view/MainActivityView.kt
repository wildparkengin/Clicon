package ua.willeco.clicon.mvp.view

import androidx.fragment.app.Fragment

interface MainActivityView:BaseView {
    /**
     * Displays an error in the view
     * @param error the error to display in the view
     */
    fun initView()
    /**
     * Displays an error in the view
     * @param error the error to display in the view
     */
    fun initNavigationView()
    /**
     * Displays an error in the view
     * @param error the error to display in the view
     */
    fun changeViewFragment(fragmentName: String)
}