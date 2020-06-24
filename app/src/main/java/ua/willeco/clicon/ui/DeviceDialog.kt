package ua.willeco.clicon.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import ua.willeco.clicon.R
import ua.willeco.clicon.databinding.DialogDeviceBinding
import ua.willeco.clicon.enums.AppRequestEventType
import ua.willeco.clicon.enums.DeviceType
import ua.willeco.clicon.enums.DialogViewType
import ua.willeco.clicon.model.Facility
import ua.willeco.clicon.model.QrScanDevice
import ua.willeco.clicon.mvp.BaseDialog
import ua.willeco.clicon.mvp.contract.DeviceDialogContract
import ua.willeco.clicon.mvp.presenter.DeviceDialogPresenter
import ua.willeco.clicon.mvp.view.DialogsViewInterface
import ua.willeco.clicon.utility.AttemptPermissions
import ua.willeco.clicon.utility.Constants
import ua.willeco.clicon.utility.ViewsElementsUtill
import ua.willeco.clicon.utility.camera.CameraXHelper


class DeviceDialog constructor(
    private val mListener:DialogsViewInterface.DialogButtonListeners,
    private val type: AppRequestEventType): BaseDialog<DeviceDialogPresenter>(),
    DeviceDialogContract.View{
    private lateinit var mTitle: String
    lateinit var binding: DialogDeviceBinding
    private var cameraHelper: CameraXHelper? = null
    private lateinit var mContext:Context

    companion object{
        fun newInstance(listener: DialogsViewInterface.DialogButtonListeners, type: AppRequestEventType, item: Any?): DeviceDialog? {
            val d = DeviceDialog(listener,type)
            val args = Bundle()
            args.putSerializable(Constants.DIALOG_FACILITY_LIST_DEVICE_ARGUMENTS, parseListFacility(item))
            d.arguments = args
            return d
        }

        private fun parseListFacility(item:Any?):ArrayList<Facility>{
            val facilityList = ArrayList<Facility>()
            if (item is ArrayList<*>){
                item.let {
                    for( facility in it){
                        if (facility is Facility){
                            facilityList.add(facility)
                        }
                    }
                }
            }
            return facilityList
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogDeviceBinding.inflate(layoutInflater)
        //initAuxiliaryElement()
        return binding.root
    }

//    override fun initAuxiliaryElement() {
//
//        mContext = binding.root.context
//
//        mTitle = when(type){
//            AppRequestEventType.ADD_DEVICE -> getString(R.string.promt_popup_menu_add_device)
//            AppRequestEventType.UPDATE_DEVICE -> getString(R.string.dialog_title_update_device)
//            else -> "NONE TITLE DIALOG"
//        }
//        binding.frameQrScan.visibility = View.GONE
//        binding.txtDeviceDialogTitle.text = mTitle
//
//        this.isCancelable = false
//
//        binding.imgQrScan.setOnClickListener(qrImgClickListener)
//        binding.edtNewDeviceName.addTextChangedListener(edtNameTextWatcher)
//        binding.edtNewDeviceChipid.addTextChangedListener(edtChipIdTextWatcher)
//        binding.btnDialogDeviceCancel.setOnClickListener(btnCancelOnClickListener)
//        binding.btnDialogDeviceAccept.setOnClickListener(btnAcceptOnClickListener)
//
//        binding.txtFacilityList.validator = selectFacilityValidator
//
//        initFacilityList()
//        initDeviceTypeList()
//    }

//    override fun initViewDialogBuilder(): AlertDialog {
//        return AlertDialog.Builder(mContext)
//            .setTitle(mTitle)
//            .setView(binding.root)
//            .setNeutralButton(R.string.promt_button_neutral_qr){dialog, which ->
//
//            }
//            .setNegativeButton(R.string.promt_button_cancel) { dialog, _ ->
//                dialog.dismiss()
//                listener.onDialogNegativeClick()
//            }
//            .setPositiveButton(R.string.promt_button_add) { dialog, _ ->
//                //validateDateInput()
//            }
//            .create()
//    }

//    override fun validateDateInput() {
//        if (binding.edtLayoutNewDeviceName.error == null && binding.edtLayoutNewDeviceChipid.error == null ){
//            sendDataFromUserInput()
//        }else{
//            ViewsElementsUtill.showShortToastMessage(mContext,getString(R.string.error_empty_fields))
//        }
//    }
//
//    override fun sendDataFromUserInput() {
//        when(type){
//            AppRequestEventType.UPDATE_DEVICE -> {
////                if (mItemFacility?.name == binding.edtFacilityName.text.toString()){
////                    ViewsElementsUtill.showShortToastMessage(binding.root.context,"Nothing to change")
////                }else{
////                    mItemFacility?.name = binding.edtFacilityName.text.toString()
////                }
//            }
//
//            AppRequestEventType.ADD_DEVICE ->{
////                val mDevice = Facility()
////                mItemFacility.name = binding.edtFacilityName.text.toString()
//            } else -> return
//        }
//
//        //listener.onDialogPositiveClick(type,)
//    }

    private fun initFacilityList(){
        val adapter = ArrayAdapter(mContext,android.R.layout.simple_list_item_1,getFacilityList())
        binding.txtFacilityList.setAdapter(adapter)
    }
    private fun initDeviceTypeList(){
        val adapter = ArrayAdapter(mContext,android.R.layout.simple_list_item_1,DeviceType.values())
        binding.txtDeviceList.setAdapter(adapter)
    }

    private val qrImgClickListener = View.OnClickListener { v ->
        when(binding.frameQrScan.visibility){
            View.VISIBLE ->{
                binding.frameQrScan.visibility= View.GONE
                stopQrScanner()

            }
            View.GONE ->{
                binding.frameQrScan.visibility= View.VISIBLE
                if (AttemptPermissions.cameraPermissionsGranted(mContext)) {
                    initQrScanner()
                } else {
                    requestPermissions(
                        Constants.REQUIRED_PERMISSIONS,
                        Constants.REQUEST_CODE_CAMERA_PERMISSIONS
                    )
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Constants.REQUEST_CODE_CAMERA_PERMISSIONS) {
            if (AttemptPermissions.cameraPermissionsGranted(mContext)) {
//                binding.txViewQrScan.post {
//                    startCamera()
//                }
            } else {
                return
            }
        }
    }

    private val btnCancelOnClickListener = View.OnClickListener { dialog?.dismiss() }

    private val btnAcceptOnClickListener = View.OnClickListener {
        //sendDataFromUserInput()
    }


    private val selectFacilityValidator = object : AutoCompleteTextView.Validator{
        override fun fixText(invalidText: CharSequence?): CharSequence {
            return "New facility"
        }

        override fun isValid(text: CharSequence?): Boolean {
            return !text.isNullOrEmpty()
        }

    }

    private fun getFacilityList():ArrayList<String>{
        val facilityList = ArrayList<String>()
        if (arguments!=null){
            try {
                val item = arguments?.getSerializable(Constants.DIALOG_FACILITY_LIST_DEVICE_ARGUMENTS)
                item.let {
                    for( facility in it as ArrayList<*>){
                        if (facility is Facility){
                            facilityList.add(facility.name.toString())
                        }
                    }
                }
            }catch (n:NullPointerException){
                n.printStackTrace()
            }
        }
        return facilityList
    }

    private fun initQrScanner(){
        if (cameraHelper==null){
            //cameraHelper = CameraXHelper(mContext, binding.txViewQrScan,this)
            cameraHelper?.startScan(this)
        }else{
            cameraHelper?.startScan(this)
        }
    }

    private fun stopQrScanner(){
        cameraHelper?.stopScan()
    }

    fun getQrScanValue(device: QrScanDevice) {
        try {
            if (device.owner_id!=-1){
                ViewsElementsUtill.showLongToastMessage(mContext,mContext.getString(R.string.message_device_is_already_added))
            } else{
                binding.frameQrScan.visibility = View.GONE
                binding.edtNewDeviceChipid.setText(device.device_cid)
                getTypeItemPosition(device.type)
            }
        }catch (e:Exception){
            Log.d("qr",e.message.orEmpty())
        }
    }

    private fun getTypeItemPosition(type:DeviceType?){
        val mDevAdapter = binding.txtDeviceList.adapter
        val n: Int = mDevAdapter.count
        if (type!=null){
            run loop@{
                for (i in 0 until n) {
                    val tempItem = mDevAdapter.getItem(i) as DeviceType
                    if (tempItem.name == type.name){
                        binding.txtDeviceList.setText(binding.txtDeviceList.adapter.getItem(i).toString(), false);
                        return@loop
                    }
                }
            }
        }
    }

    override fun initViewElement() {
        this.isCancelable = false

        initListenersToViewsElements()
    }

    override fun initListenersToViewsElements() {
        binding.btnDialogDeviceCancel.setOnClickListener {
            dismiss()
        }

        binding.btnDialogDeviceAccept.setOnClickListener {
            if (presenter.validateErrorEditFieldName(binding.edtLayoutNewDeviceName.error)){
                //presenter.validateSendData(type, binding.edtFacilityName.text.toString())
            }else{
                showToastMessage(getString(R.string.error_empty_fields))
            }
        }
    }

    override fun setErrorToNameInput() {
        binding.edtLayoutNewDeviceName.setEndIconActivated(false)
        binding.edtLayoutNewDeviceName.error = ""
    }

    override fun setErrorToDeviceIdInput() {
        binding.edtLayoutNewDeviceChipid.setEndIconActivated(false)
        binding.edtLayoutNewDeviceChipid.error = ""
    }

    override fun setAcceptNameInput() {
        binding.edtLayoutNewDeviceName.setEndIconActivated(true)
        binding.edtLayoutNewDeviceName.error = null
    }

    override fun setAcceptDeviceIdInput() {
        binding.edtLayoutNewDeviceChipid.setEndIconActivated(true)
        binding.edtLayoutNewDeviceChipid.error = null
    }

    override fun showToastMessage(message: String) {
        ViewsElementsUtill.showShortToastMessage(getViewContext(),message)
    }

    override fun initData(facility: Facility?) {
        TODO("Not yet implemented")
    }

    override fun sendMainViewToUpdateData() {
        when(type){
            AppRequestEventType.ADD_DEVICE->{
                mListener.onDialogPositiveClick(DialogViewType.ADD_FACILITY,null)
            }
            AppRequestEventType.UPDATE_DEVICE ->{
                mListener.onDialogPositiveClick(DialogViewType.UPDATE_FACILITY,null)
            }
            else -> null
        }
        this.dialog?.dismiss()
    }

    override fun getViewContext(): Context {
        return binding.root.context
    }

    override fun instantiatePresenter(): DeviceDialogPresenter {
        return DeviceDialogPresenter(this)
    }
}
