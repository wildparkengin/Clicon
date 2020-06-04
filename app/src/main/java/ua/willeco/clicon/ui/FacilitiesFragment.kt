package ua.willeco.clicon.ui

import android.app.Dialog
import android.media.Image
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialDialogs
import ua.willeco.clicon.R
import ua.willeco.clicon.adapters.DevicesAdapter
import ua.willeco.clicon.adapters.FacilitiesAdapter
import ua.willeco.clicon.databinding.FragmentFacilityLayoutBinding
import ua.willeco.clicon.model.RequestsModels.GetDevicesListResponse
import ua.willeco.clicon.model.RequestsModels.GetFacilitiesListResponse
import ua.willeco.clicon.mvp.BaseFragment
import ua.willeco.clicon.mvp.contract.FacilitiesContract
import ua.willeco.clicon.mvp.presenter.FacilitiesPresenter
import ua.willeco.clicon.utility.ViewsElementsUtill

class FacilitiesFragment:BaseFragment<FacilitiesPresenter>(),FacilitiesContract.View{

    private lateinit var binding:FragmentFacilityLayoutBinding
    private lateinit var mFacilityAdapter:FacilitiesAdapter
    private lateinit var mDeviceAdapter:DevicesAdapter
    private var dialogAddFacility:AddFacilityDialog? = null
    private var dialogAddDevice:AddDeviceDialog? = null
    private lateinit var popupMenu: PopupMenu

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding  = FragmentFacilityLayoutBinding.inflate(inflater,container,false)
        initView()

        presenter.getFacilityResponse()
        return binding.root
    }

    override fun initView() {
        initPopupView()
    }

    override fun showError(error: String) {
        ViewsElementsUtill.showShortToastMessage(binding.root.context,error)
    }

    override fun showLoader() {
    }

    override fun closeLoader() {

    }

    override fun loadFacilitiesRecycler(facilityData: GetFacilitiesListResponse) {
        if (!facilityData.facilityList.isNullOrEmpty()){
            binding.facilityRecycler.apply {
                this.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
                mFacilityAdapter = FacilitiesAdapter(context,facilityData.facilityList)
                this.adapter = mFacilityAdapter

                presenter.getDevicesResponse()

            }
        }else{
            showError("Unex error")
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
                showError("Unex error")
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    override fun showPopup() {
        popupMenu.show()
    }

    override fun instantiatePresenter(): FacilitiesPresenter {
        return FacilitiesPresenter(this)
    }

    override fun clickAddFacility() {
        dialogAddFacility = AddFacilityDialog(binding.root.context)
        dialogAddFacility?.show()

    }

    override fun clickAddDevice() {
        dialogAddDevice = AddDeviceDialog(binding.root.context)
        dialogAddDevice?.show()
    }

    override fun initPopupView() {
//        popupMenu = PopupMenu(binding.root.context,binding.root.rootView)
//        popupMenu.inflate(R.menu.popup_menu)
//
//        popupMenu.setOnMenuItemClickListener {
//            when(it.itemId){
//                R.id.mnuCreateFacility -> {
//
//                }
//                R.id.mnuCreateDevice -> {
//
//                }
//            }
//            false
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.facility_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return (when(item.itemId) {
            R.id.action_add_room-> {
                clickAddFacility()
                true
            }

            R.id.action_add_device->{
                clickAddDevice()
                true
            }
            else ->
                super.onOptionsItemSelected(item)
        })
    }
}