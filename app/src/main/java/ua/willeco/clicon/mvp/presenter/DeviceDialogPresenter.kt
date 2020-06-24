package ua.willeco.clicon.mvp.presenter

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import ua.willeco.clicon.enums.AppRequestEventType
import ua.willeco.clicon.http.ApiRequests
import ua.willeco.clicon.mvp.contract.DeviceDialogContract
import ua.willeco.clicon.mvp.repository.DevicesRepository
import javax.inject.Inject

class DeviceDialogPresenter(deviceDialogView: DeviceDialogContract.View):
    BasePresenter<DeviceDialogContract.View>(deviceDialogView),
    DeviceDialogContract.Presenter,
    DeviceDialogContract.Repository  {

    @Inject
    lateinit var api: ApiRequests
    private var devicesRepository: DevicesRepository

    init {
        devicesRepository = DevicesRepository(api)
    }

    override fun parseArguments(arg: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun validateSendData(type: AppRequestEventType, facilityName: String) {
        TODO("Not yet implemented")
    }

    override fun validateErrorEditFieldName(value: CharSequence?): Boolean {
        TODO("Not yet implemented")
    }

    override fun getDialogTitle(type: AppRequestEventType): String {
        TODO("Not yet implemented")
    }

    override fun getTextNameDeviceChangedListener(): TextWatcher {
        return object :TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val content = s.toString()
                if (content.length >= 3) {
//                    binding.edtLayoutNewDeviceName.setEndIconActivated(true)
//                    binding.edtLayoutNewDeviceName.boxStrokeColor = Color.GREEN
//                    binding.edtLayoutNewDeviceName.error = null
                    //edtLayout.error = null
                } else {
//                    binding.edtLayoutNewDeviceName.setEndIconActivated(false)
//                    binding.edtLayoutNewDeviceName.boxStrokeColor = Color.RED
//                    binding.edtLayoutNewDeviceName.error = ""
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
    }

    override fun getTextDeviceIDDeviceChangedListener(): TextWatcher {
        return object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                val content = s.toString()
                if (content.length >= 12){
//                    binding.edtLayoutNewDeviceChipid.setEndIconActivated(true)
//                    binding.edtLayoutNewDeviceChipid.boxStrokeColor = Color.GREEN
//                    binding.edtLayoutNewDeviceChipid.error = null
                }else{
//                    binding.edtLayoutNewDeviceChipid.setEndIconActivated(false)
//                    binding.edtLayoutNewDeviceChipid.boxStrokeColor = Color.RED
//                    binding.edtLayoutNewDeviceChipid.error = ""
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
    }

    override fun updateDevice(name: String?, mac: String?) {
        TODO("Not yet implemented")
    }

    override fun createDevice(name: String?) {
        TODO("Not yet implemented")
    }

    override fun onFinishedRequest(responseData: Any, requestEventType: AppRequestEventType) {
        TODO("Not yet implemented")
    }

    override fun onFailureRequest(t: String) {
        TODO("Not yet implemented")
    }
}