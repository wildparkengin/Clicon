package ua.willeco.clicon.ui

import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import ua.willeco.clicon.R
import ua.willeco.clicon.databinding.DialogAddFacilityBinding
import ua.willeco.clicon.enums.AppRequestEventType
import ua.willeco.clicon.model.Facility
import ua.willeco.clicon.mvp.view.DialogAddUpdateView
import ua.willeco.clicon.utility.ViewsElementsUtill


class DialogAddUpdateFacility(
    var mListener: DialogAddUpdateView.DialogButtonListeners,
    var type: AppRequestEventType
) : DialogFragment(),DialogAddUpdateView.DialogInitViewElements{

    lateinit var binding: DialogAddFacilityBinding
    lateinit var dialogTitle:String
    private var mItemFacility:Facility? = null

    companion object{
        fun newInstance(listener:DialogAddUpdateView.DialogButtonListeners,type: AppRequestEventType,item: Any?): DialogAddUpdateFacility? {
            val d = DialogAddUpdateFacility(listener,type)
            if (item is Facility){
                val args = Bundle()
                args.putSerializable("facility", item)
                d.arguments = args
            }
            return d
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogAddFacilityBinding.inflate(layoutInflater)
        initAuxiliaryElement()
        return initViewDialogBuilder(dialogTitle)
    }

    override fun initAuxiliaryElement() {
        dialogTitle = when(type){
            AppRequestEventType.ADD_FACILITY -> getString(R.string.promt_popup_menu_add_facility)
            AppRequestEventType.UPDATE_FACILITY -> getString(R.string.dialog_title_update_facility)
            else -> "NONE TITLE DIALOG"
        }

        this.isCancelable = false

        binding.edtFacilityName.addTextChangedListener(edtNameTextWatcher)

        binding.roomTextInputLayout.setEndIconOnClickListener {
            if (!it.isActivated){
                ViewsElementsUtill.showShortToastMessage(context,getString(R.string.promt_error_edittext_length_3))
            }
        }

        if (arguments!=null){
            try {
                val item = arguments?.getSerializable("facility")
                item.let {
                    if (it is Facility){
                        initData(it)
                    }
                }
            }catch (n:NullPointerException){
                n.printStackTrace()
            }
        }else{
            //TODO mItemFacil is will null
            print("dd")
        }
    }

    override fun initViewDialogBuilder(title:String): AlertDialog {
        return AlertDialog.Builder(binding.root.context)
            .setTitle(title)
            .setView(binding.root)
            .setNegativeButton(R.string.promt_button_cancel) { dialog, _ ->
                dialog.dismiss()
                mListener.onDialogNegativeClick()
            }
            .setPositiveButton(R.string.promt_button_add) { dialog, _ ->
                if (binding.roomTextInputLayout.error == null){
                    validateToEditData()
                }else{
                    ViewsElementsUtill.showShortToastMessage(binding.root.context,getString(R.string.error_empty_fields))
                }
            }
            .create()
    }

    private fun validateToEditData(){
        when(type){
            AppRequestEventType.UPDATE_FACILITY -> {
                if (mItemFacility?.name == binding.edtFacilityName.text.toString()){
                    ViewsElementsUtill.showShortToastMessage(binding.root.context,"Nothing to change")
                }else{
                    mItemFacility?.name = binding.edtFacilityName.text.toString()

                }
            }

            AppRequestEventType.ADD_FACILITY ->{
                mItemFacility = Facility()
                mItemFacility?.name = binding.edtFacilityName.text.toString()
            } else -> return
        }

        mListener.onDialogPositiveClick(type,mItemFacility)
    }

    private fun initData(facility: Facility?){
        mItemFacility = facility
        mItemFacility.let {
            binding.edtFacilityName.setText(mItemFacility?.name)
            binding.edtFacilityName.requestFocus()
        }
    }

    private val edtNameTextWatcher = object :TextWatcher{
        override fun afterTextChanged(s: Editable?) {
            val content = s.toString()
            if (content.length >= 3){
                binding.roomTextInputLayout.setEndIconActivated(true)
                binding.roomTextInputLayout.error = null
            }else{
                binding.roomTextInputLayout.setEndIconActivated(false)
                binding.roomTextInputLayout.error = ""
            }
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }
}