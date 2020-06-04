package ua.willeco.clicon.ui

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import ua.willeco.clicon.databinding.DialogCreateDeviceBinding
import ua.willeco.clicon.mvp.contract.DialogAddDeviceContract

class AddDeviceDialog constructor(cont: Context): Dialog(cont), DialogAddDeviceContract.View {

    private lateinit var binding: DialogCreateDeviceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        initView()
        super.onCreate(savedInstanceState)
    }

    override fun initView() {
        binding = DialogCreateDeviceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setCancelable(false)

        binding.btnAcceptAddDevice.setOnClickListener {
            onClickAddButton()
        }

        binding.btnCancelAddDevice.setOnClickListener {
            dismissDialog()
        }
    }

    override fun setErrorHintToNameEditText() {

    }

    override fun setErrorHintToChipIdEditText() {

    }


    override fun dismissDialog() {
        this.dismiss()
    }

    override fun onClickAddButton() {

    }


}