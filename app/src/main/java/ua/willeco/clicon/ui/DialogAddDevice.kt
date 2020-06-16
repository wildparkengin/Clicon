package ua.willeco.clicon.ui

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import ua.willeco.clicon.R
import ua.willeco.clicon.databinding.DialogAddDeviceBinding
import ua.willeco.clicon.enums.AppRequestEventType
import ua.willeco.clicon.mvp.view.DialogAddUpdateView
import ua.willeco.clicon.utility.ViewsElementsUtill

class DialogAddDevice constructor(private val listener:DialogAddUpdateView.DialogButtonListeners, private val type: AppRequestEventType): DialogFragment(),DialogAddUpdateView.DialogInitViewElements{
    private lateinit var dialogTitle: String
    lateinit var binding: DialogAddDeviceBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogAddDeviceBinding.inflate(layoutInflater)

        initAuxiliaryElement()

        return initViewDialogBuilder(dialogTitle)
    }

    override fun initAuxiliaryElement() {
        dialogTitle = when(type){
            AppRequestEventType.ADD_DEVICE -> getString(R.string.promt_popup_menu_add_device)
            AppRequestEventType.UPDATE_DEVICE -> getString(R.string.dialog_title_update_device)
            else -> "NONE TITLE DIALOG"
        }
        this.isCancelable = false

        binding.edtNewDeviceName.addTextChangedListener(edtNameTextWatcher)
        binding.edtNewDeviceChipid.addTextChangedListener(edtChipIdTextWatcher)

        initRoomList()
    }

    override fun initViewDialogBuilder(title: String): AlertDialog {
        return AlertDialog.Builder(binding.root.context)
            .setTitle(title)
            .setView(binding.root)
            .setNegativeButton(R.string.promt_button_cancel) { dialog, _ ->
                dialog.dismiss()
                listener.onDialogNegativeClick()
            }
            .setPositiveButton(R.string.promt_button_add) { dialog, _ ->
                if (binding.edtLayoutNewDeviceName.isActivated && binding.edtLayoutNewDeviceChipid.isActivated){
                    //listener.onDialogPositiveClick()
                }else{
                    ViewsElementsUtill.showShortToastMessage(binding.root.context,getString(R.string.error_empty_fields))
                }
                //listener.onDialogPositiveClick()
                //targetFragment?.onActivityResult(Constants.RESULT_FROM_DIALOG, Activity.RESULT_OK, activity?.intent)
            }
            .create()
    }

    private fun initRoomList(){
        val item = listOf("Hi","By")
        val adapter = ArrayAdapter(binding.root.context,android.R.layout.simple_list_item_1,item)
        (binding.txtRoomList as AutoCompleteTextView).setAdapter(adapter)
    }

    private val edtNameTextWatcher = object :TextWatcher{
        override fun afterTextChanged(s: Editable?) {
            val content = s.toString()
                if (content.length >= 3){
                    binding.edtLayoutNewDeviceName.setEndIconActivated(true)
                    binding.edtLayoutNewDeviceName.boxStrokeColor = Color.GREEN
                    binding.edtLayoutNewDeviceName.error = null
                    //edtLayout.error = null
                }else{
                    binding.edtLayoutNewDeviceName.setEndIconActivated(false)
                    binding.edtLayoutNewDeviceName.boxStrokeColor = Color.RED
                    binding.edtLayoutNewDeviceName.error = ""
                }
            }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }

    private val edtChipIdTextWatcher = object :TextWatcher{
        override fun afterTextChanged(s: Editable?) {
            val content = s.toString()
            if (content.length >= 12){
                binding.edtLayoutNewDeviceChipid.setEndIconActivated(true)
                binding.edtLayoutNewDeviceChipid.boxStrokeColor = Color.GREEN
                binding.edtLayoutNewDeviceChipid.error = null
            }else{
                binding.edtLayoutNewDeviceChipid.setEndIconActivated(false)
                binding.edtLayoutNewDeviceChipid.boxStrokeColor = Color.RED
                binding.edtLayoutNewDeviceChipid.error = ""
            }
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }
}
