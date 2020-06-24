package ua.willeco.clicon.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ua.willeco.clicon.R
import ua.willeco.clicon.databinding.DialogFacilityBinding
import ua.willeco.clicon.enums.AppRequestEventType
import ua.willeco.clicon.enums.DialogViewType
import ua.willeco.clicon.model.Facility
import ua.willeco.clicon.mvp.BaseDialog
import ua.willeco.clicon.mvp.contract.FacilityDialogContract
import ua.willeco.clicon.mvp.presenter.FacilityDialogPresenter
import ua.willeco.clicon.mvp.view.DialogsViewInterface
import ua.willeco.clicon.utility.Constants
import ua.willeco.clicon.utility.ViewsElementsUtill


class FacilityDialog(
    var mListener: DialogsViewInterface.DialogButtonListeners,
    var type: AppRequestEventType
) : BaseDialog<FacilityDialogPresenter>(),FacilityDialogContract.View{ //CustomDialogViewInterface.DialogInitViewElements, CustomDialogViewInterface.DialogWithEnterData

    lateinit var binding: DialogFacilityBinding
    lateinit var mContext: Context

    companion object{
        fun newInstance(listener:DialogsViewInterface.DialogButtonListeners, type: AppRequestEventType, item: Any?): FacilityDialog? {
            val d = FacilityDialog(listener,type)
            if (item is Facility){
                val args = Bundle()
                args.putSerializable(Constants.DIALOG_FACILITY_ARGUMENTS, item)
                d.arguments = args
            }
            return d
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogFacilityBinding.inflate(layoutInflater)
        mContext = binding.root.context

        presenter.parseArguments(arguments)
        initViewElement()
        return binding.root
    }

    override fun initData(facility: Facility?){
        facility.let {
            binding.edtFacilityName.setText(facility?.name)
            binding.edtFacilityName.requestFocus()
        }
    }

    override fun sendMainViewToUpdateData() {
        when(type){
            AppRequestEventType.ADD_FACILITY->{
                mListener.onDialogPositiveClick(DialogViewType.ADD_FACILITY,null)
            }
            AppRequestEventType.UPDATE_FACILITY ->{
                mListener.onDialogPositiveClick(DialogViewType.UPDATE_FACILITY,null)
            }
            else -> null
        }

        this.dialog?.dismiss()
    }

    override fun initViewElement() {

        this.isCancelable = false

        binding.txtTitleFacilityDialog.text = presenter.getDialogTitle(type)

        initListenersToViewsElements()
    }

    override fun initListenersToViewsElements() {
        binding.edtFacilityName.addTextChangedListener(presenter.getTextChangedListener())

        binding.facilityTextInputLayout.setEndIconOnClickListener {
            if (!it.isActivated){
                showToastMessage(getString(R.string.promt_error_edittext_length_3))
            }
        }

        binding.btnAcceptFacilityDialog.setOnClickListener {
            if (presenter.validateErrorEditFieldName(binding.facilityTextInputLayout.error)){
                presenter.validateSendData(type, binding.edtFacilityName.text.toString())
            }else{
                showToastMessage(getString(R.string.error_empty_fields))
            }
        }

        binding.btnCancelFacilityDialog.setOnClickListener {
            dialog?.dismiss()
        }
    }

    override fun setErrorInput() {
        binding.facilityTextInputLayout.setEndIconActivated(false)
        binding.facilityTextInputLayout.error = ""
    }

    override fun showToastMessage(message: String) {
        ViewsElementsUtill.showShortToastMessage(mContext,getString(R.string.error_empty_fields))
    }

    override fun setAcceptInput() {
        binding.facilityTextInputLayout.setEndIconActivated(true)
        binding.facilityTextInputLayout.error = null
    }

    override fun getViewContext(): Context {
        return binding.root.context
    }

    override fun instantiatePresenter(): FacilityDialogPresenter {
        return FacilityDialogPresenter(this)
    }
}