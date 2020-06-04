package ua.willeco.clicon.ui

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import ua.willeco.clicon.R
import ua.willeco.clicon.databinding.DialogCreateFacilityBinding
import ua.willeco.clicon.mvp.contract.DialogAddFacilityContract

class AddFacilityDialog constructor(cont: Context):Dialog(cont),DialogAddFacilityContract.View{

    private lateinit var binding:DialogCreateFacilityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        initView()
        super.onCreate(savedInstanceState)
    }

    override fun initView() {
        binding = DialogCreateFacilityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setCancelable(false)

        binding.btnAcceptAddFacility.setOnClickListener {
            onClickAddButton()
        }

        binding.btnCancelAddFacility.setOnClickListener {
            onClickCancelButton()
        }
    }

    override fun setErrorHintToNameEditText() {

    }

    override fun onClickAddButton() {

    }

    override fun onClickCancelButton() {
        this.dismiss()
    }
}