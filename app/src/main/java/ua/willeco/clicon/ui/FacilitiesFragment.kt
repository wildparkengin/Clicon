package ua.willeco.clicon.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ua.willeco.clicon.adapters.FacilitiesAdapter
import ua.willeco.clicon.databinding.FragmentFacilityLayoutBinding
import ua.willeco.clicon.model.RequestsModels.GetFacilitiesListResponse
import ua.willeco.clicon.mvp.BaseFragment
import ua.willeco.clicon.mvp.contract.FacilitiesContract
import ua.willeco.clicon.mvp.presenter.FacilitiesPresenter

class FacilitiesFragment :BaseFragment<FacilitiesPresenter>(),FacilitiesContract.View {

    lateinit var binding:FragmentFacilityLayoutBinding
    lateinit var rcvFacility:RecyclerView
    lateinit var mAdapter:FacilitiesAdapter

    companion object{
        fun newInstance():FacilitiesFragment{
            return FacilitiesFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding  = FragmentFacilityLayoutBinding.inflate(inflater,container,false)
        initView()
        return binding.root
    }


    override fun initView() {
        rcvFacility = binding.facilityRecycler
        presenter.getFacilityResponse()
    }

    override fun showError(error: String) {
        println()
    }

    override fun showDialogToAddFacility() {
        println()
    }

    override fun showLoader() {
        println()
    }

    override fun closeLoader() {
        println()
    }

    override fun loadFacilitiesRecycler(facilitiesDataResponse: GetFacilitiesListResponse) {
        if (!facilitiesDataResponse.facilityList.isNullOrEmpty()){
            rcvFacility.apply {
                rcvFacility.layoutManager = GridLayoutManager(context,2)
                mAdapter = FacilitiesAdapter(context,facilitiesDataResponse.facilityList)
                rcvFacility.adapter = mAdapter
            }
        }else{
            showError("Unex error")
        }

    }

    override fun instantiatePresenter(): FacilitiesPresenter {
        return FacilitiesPresenter(this)
    }
}