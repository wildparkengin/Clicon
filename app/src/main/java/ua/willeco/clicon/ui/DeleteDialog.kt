package ua.willeco.clicon.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import ua.willeco.clicon.R
import ua.willeco.clicon.enums.AppRequestEventType
import ua.willeco.clicon.mvp.BaseDialog
import ua.willeco.clicon.mvp.contract.DeleteDialogContract
import ua.willeco.clicon.mvp.presenter.DeleteDialogPresenter
import ua.willeco.clicon.mvp.view.DialogsViewInterface
import ua.willeco.clicon.utility.Constants
import ua.willeco.clicon.utility.ViewsElementsUtill
import java.io.Serializable

class DeleteDialog (
    var mListener: DialogsViewInterface.DialogButtonListeners,
    var type: AppRequestEventType): BaseDialog<DeleteDialogPresenter>(),DeleteDialogContract.View{

    companion object{
        fun newInstance(listener: DialogsViewInterface.DialogButtonListeners, type: AppRequestEventType, item: Any?): DeleteDialog? {
            val d = DeleteDialog(listener,type)
            val args = Bundle()
            args.putSerializable(Constants.DIALOG_DELETE_ARGUMENTS, item as Serializable?)
            d.arguments = args
            return d
        }
    }

    private lateinit var mContext: Context

    override fun onCreateDialog(savedInstanceState: Bundle?): AlertDialog {
//        initAuxiliaryElement()
        this.context.let {
            if (it != null) {
                mContext = it
                presenter.parseArgument(arguments)
            }
        }
        return initViewDialogBuilder()
    }

    override fun initViewDialogBuilder(): AlertDialog {
        this.isCancelable = false
        return AlertDialog.Builder(getViewContext())
            .setTitle(presenter.getTitleDialog(type))
            .setMessage(presenter.getMessageDialogs(type))
            .setNegativeButton(R.string.promt_button_cancel) { dialog, _ ->
                closeDialog()
            }
            .setPositiveButton(R.string.promt_button_ok) { dialog, _ ->
                onAcceptButtonClick()
            }
            .create()
    }

    override fun onAcceptButtonClick() {
        presenter.validateRequestType()
    }

    override fun closeDialog() {
        dialog?.dismiss()
    }

    override fun showCompleteDeleteMessage() {
        ViewsElementsUtill.showShortToastMessage(getViewContext(),"Delete is success")
        presenter.getCurrentTypeDialog(type).let {
            if (it != null) {
                mListener.onDialogPositiveClick(it,null)
            }
        }
    }

    override fun showErrorMessage(message: String) {
        ViewsElementsUtill.showShortToastMessage(getViewContext(),message)
    }

    override fun getViewContext(): Context {
        return mContext
    }

    override fun instantiatePresenter(): DeleteDialogPresenter {
       return DeleteDialogPresenter(this)
    }
}