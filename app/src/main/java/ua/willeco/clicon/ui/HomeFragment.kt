package ua.willeco.clicon.ui

import android.content.Context
import android.os.Bundle
import android.view.*
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
import ua.willeco.clicon.model.getRequestsModels.GetDevicesListResponse
import ua.willeco.clicon.model.getRequestsModels.GetFacilityListResponse
import ua.willeco.clicon.mvp.BaseFragment
import ua.willeco.clicon.mvp.contract.HomeFragmentContract
import ua.willeco.clicon.mvp.presenter.HomeFragmentPresenter
import ua.willeco.clicon.mvp.view.AdapterFacilityItemClick
import ua.willeco.clicon.mvp.view.DialogsViewInterface
import ua.willeco.clicon.mvp.view.PopupEditListener
import ua.willeco.clicon.utility.ViewsElementsUtill

class HomeFragment:BaseFragment<HomeFragmentPresenter>(),HomeFragmentContract.View,DialogsViewInterface.DialogButtonListeners, PopupEditListener,AdapterFacilityItemClick{

    private lateinit var binding:FragmentFacilityLayoutBinding
    private lateinit var mContext:Context
    private lateinit var mFacilityAdapter:FacilitiesAdapter
    private lateinit var mDeviceAdapter:DevicesAdapter
    private var mDialog: DialogFragment? = null
    private lateinit var listenerPopupMenu:PopupEditListener
    private lateinit var listenerClickFacilityItem:AdapterFacilityItemClick

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding  = FragmentFacilityLayoutBinding.inflate(inflater,container,false)
        mContext = binding.root.context
        initListeners()
        presenter.getFacilityListResponse()
        return binding.root
    }

    override fun initListeners() {
        listenerPopupMenu = this
        listenerClickFacilityItem = this
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

        ViewsElementsUtill.showShortToastMessage(mContext,message)

    }

    override fun showToastError(error: String) {
        ViewsElementsUtill.showShortToastMessage(mContext,error)
    }

    override fun showLoader() {
    }

    override fun closeLoader() {

    }

    override fun loadFacilitiesRecycler(facilityData: GetFacilityListResponse) {
        if (!facilityData.facilityList.isNullOrEmpty()){
            binding.facilityRecycler.apply {
                layoutManager = GridLayoutManager(context,1,GridLayoutManager.HORIZONTAL,false)
                mFacilityAdapter = FacilitiesAdapter(mContext,listenerPopupMenu,listenerClickFacilityItem,facilityData.facilityList)
                this.adapter = mFacilityAdapter
            }
        }else{
            showToastError(binding.root.context.getString(R.string.toast_message_empty_facility_list))
        }
    }

    override fun loadDevicesRecycler(devicesDataResponse: GetDevicesListResponse) {
        try {
            if (!devicesDataResponse.boiler101List.isNullOrEmpty()){
                binding.facilityRecycler.apply {
                    this.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
                    //mDeviceAdapter = DevicesAdapter(devicesDataResponse.deviceList)
                    this.adapter = mDeviceAdapter
                }
            }else{
                showToastError(binding.root.context.getString(R.string.toast_message_empty_device_list))
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    override fun getViewContext(): Context {
        return mContext
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
                showCustomDialog(DialogViewType.ADD_FACILITY,AppRequestEventType.ADD_FACILITY,null)
                true
            }
            R.id.action_add_device -> {
                showCustomDialog(DialogViewType.ADD_DEVICE,AppRequestEventType.ADD_DEVICE, presenter.getTempFacilityList())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showCustomDialog(type: DialogViewType, eventType: AppRequestEventType, mObj: Any?) {
        mDialog = CustomDialogFactory.createCustomDialog(this,type,eventType,mObj)
        mDialog?.show(childFragmentManager, mDialog?.tag)
    }

    override fun onDialogPositiveClick(type: DialogViewType, anyObj: Any?) {
        presenter.validateDialogsPositiveClick(type,anyObj)
    }

    override fun addNewDeviceItem() {
        showCustomDialog(DialogViewType.ADD_DEVICE,AppRequestEventType.ADD_DEVICE,presenter.getTempFacilityList())
    }

    override fun deleteItem(itemDeleteType: AppRequestEventType, item: Any) {
        when(itemDeleteType){
            AppRequestEventType.DELETE_DEVICE ->{
                showCustomDialog(DialogViewType.DELETE_DEVICE,itemDeleteType,item)
            }
            AppRequestEventType.DELETE_FACILITY ->{
                showCustomDialog(DialogViewType.DELETE_FACILITY,itemDeleteType,item)
            }
            else -> null
        }
    }

    override fun changeItem(itemChangeType: AppRequestEventType, item: Any) {
        when(itemChangeType){
            AppRequestEventType.UPDATE_FACILITY -> {
                showCustomDialog(DialogViewType.UPDATE_FACILITY,AppRequestEventType.UPDATE_FACILITY,item)
            }
            AppRequestEventType.UPDATE_DEVICE -> {
                showCustomDialog(DialogViewType.UPDATE_DEVICE,AppRequestEventType.UPDATE_DEVICE,item)
            }
            else -> return
        }
    }

    override fun clickOnItem(mac: String) {
        presenter.getDevicesListResponse(mac)
    }
}