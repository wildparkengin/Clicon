package ua.willeco.clicon.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import ua.willeco.clicon.R
import ua.willeco.clicon.adapters.DevicesAdapter
import ua.willeco.clicon.adapters.FacilitiesAdapter
import ua.willeco.clicon.databinding.FragmentFacilityLayoutBinding
import ua.willeco.clicon.enums.AppRequestEventType
import ua.willeco.clicon.enums.DialogViewType
import ua.willeco.clicon.factory.CustomDialogFactory
import ua.willeco.clicon.model.Facility
import ua.willeco.clicon.model.getRequestsModels.GetDevicesListResponse
import ua.willeco.clicon.model.getRequestsModels.GetRoomListResponse
import ua.willeco.clicon.mvp.BaseFragment
import ua.willeco.clicon.mvp.contract.HomeFragmentContract
import ua.willeco.clicon.mvp.presenter.HomeFragmentPresenter
import ua.willeco.clicon.mvp.view.DialogAddUpdateView
import ua.willeco.clicon.mvp.view.PopupEditListener
import ua.willeco.clicon.utility.ViewsElementsUtill

class HomeFragment:BaseFragment<HomeFragmentPresenter>(),HomeFragmentContract.View,DialogAddUpdateView.DialogButtonListeners, PopupEditListener{

    private lateinit var binding:FragmentFacilityLayoutBinding
    private lateinit var mFacilityAdapter:FacilitiesAdapter
    private lateinit var mDeviceAdapter:DevicesAdapter
    private var mDialog: DialogFragment? = null
    private lateinit var listenerPopupMenu:PopupEditListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding  = FragmentFacilityLayoutBinding.inflate(inflater,container,false)
        presenter.getFacilityListResponse()
        return binding.root
    }

    override fun initView() {

    }

    override fun showToastMessage(messageType: AppRequestEventType) {
       val message = when(messageType){
            AppRequestEventType.DELETE_FACILITY ->{
                binding.root.context.getString(R.string.toast_message_delete_success)
            }
            AppRequestEventType.UPDATE_FACILITY ->{
                binding.root.context.getString(R.string.toast_message_update_facility_success)
            }
            AppRequestEventType.ADD_FACILITY ->{
                binding.root.context.getString(R.string.toast_message_add_facility_success)
            }
            AppRequestEventType.ADD_DEVICE ->{
                binding.root.context.getString(R.string.toast_message_add_device_success)
            }
            AppRequestEventType.DELETE_DEVICE ->{
                binding.root.context.getString(R.string.toast_message_delete_success)
            }
            else -> return
        }

        ViewsElementsUtill.showShortToastMessage(binding.root.context,message)

    }

    override fun showToastError(error: String) {
        ViewsElementsUtill.showShortToastMessage(binding.root.context,error)
    }

    override fun showLoader() {
    }

    override fun closeLoader() {

    }

    override fun loadFacilitiesRecycler(facilityData: GetRoomListResponse) {
        if (!facilityData.facilityList.isNullOrEmpty()){
            listenerPopupMenu = this
            binding.facilityRecycler.apply {
                layoutManager = GridLayoutManager(context,1,GridLayoutManager.HORIZONTAL,false)
                mFacilityAdapter = FacilitiesAdapter(context,listenerPopupMenu,facilityData.facilityList)
                adapter = mFacilityAdapter
            }
        }else{
            showToastError("Unex error")
        }
    }

    override fun loadDevicesRecycler(devicesDataResponse: GetDevicesListResponse) {
        try {
            if (!devicesDataResponse.boiler100List.isNullOrEmpty()){
                binding.facilityRecycler.apply {
                    this.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
                    //mDeviceAdapter = DevicesAdapter(devicesDataResponse.deviceList)
                    this.adapter = mDeviceAdapter
                }
                println()
            }else{
                showToastError("Unex error")
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    override fun instantiatePresenter(): HomeFragmentPresenter {
        return HomeFragmentPresenter(this)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.facility_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_add_facility -> {
                showDialogAddUpdate(DialogViewType.FACILITY_DIALOG,AppRequestEventType.ADD_FACILITY,null)
                true
            }
            R.id.action_add_device -> {
                showDialogAddUpdate(DialogViewType.DEVICE_DIALOG,AppRequestEventType.ADD_DEVICE, null)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showDialogAddUpdate(type: DialogViewType,eventType: AppRequestEventType,mObj: Any?) {
        mDialog = CustomDialogFactory.createCustomDialog(this,type,eventType,mObj)
        mDialog?.show(childFragmentManager, mDialog?.tag)
    }

    override fun showDialogDelete(eventType: AppRequestEventType,macOrChipID:String) {
        val builder = AlertDialog.Builder(binding.root.context)
        var mTitle:String? = null
        var mMessage:String? = null

        when(eventType) {
            AppRequestEventType.DELETE_FACILITY -> {
                mTitle = binding.root.context.getString(R.string.alert_dialog_title_delete_facility)
                mMessage =
                    binding.root.context.getString(R.string.alert_dialog_message_delete_facility)
            }
            AppRequestEventType.DELETE_DEVICE -> {
                mTitle = binding.root.context.getString(R.string.alert_dialog_title_delete_device)
                mMessage =
                    binding.root.context.getString(R.string.alert_dialog_message_delete_device)
            } else -> return
        }

        builder.setTitle(mTitle)
        builder.setMessage(mMessage)
        builder.setCancelable(false)
        builder.setPositiveButton(R.string.promt_button_ok) { dialog, _ ->
            when (eventType) {
                AppRequestEventType.DELETE_FACILITY -> {
                    presenter.deleteFacilityRequest(macOrChipID)
                }
                AppRequestEventType.DELETE_DEVICE -> {
                    presenter.deleteDeviceRequest(macOrChipID)
                }
                else -> dialog.dismiss()
            }
        }
            // Display a negative button on alert dialog
        builder.setNegativeButton(R.string.promt_button_cancel){ dialog, _ ->
                dialog.dismiss()
        }
        builder.show()
    }

    override fun onDialogNegativeClick() {
        mDialog?.dismiss()
    }

    override fun onDialogPositiveClick(type: AppRequestEventType,anyObj:Any?) {
        if (anyObj is Facility){
            when (type) {
                AppRequestEventType.UPDATE_FACILITY -> {
                    presenter.updateFacilityRequest(anyObj)
                }
                AppRequestEventType.ADD_FACILITY -> {
                    anyObj.name?.let { presenter.addNewFacilityRequest(it) }
                }
                else -> return
            }
        }

        //presenter.getArgumentsFromDialog(mDialog)
    }

    override fun deleteItem(itemDelete: AppRequestEventType, macOrChipID: String) {
        showDialogDelete(itemDelete,macOrChipID)
    }

    override fun changeItem(itemChange: AppRequestEventType, anyItem: Any) {
        when(itemChange){
            AppRequestEventType.UPDATE_FACILITY -> {
                showDialogAddUpdate(DialogViewType.FACILITY_DIALOG,AppRequestEventType.UPDATE_FACILITY,anyItem)
            }
            else -> return
        }
    }
}