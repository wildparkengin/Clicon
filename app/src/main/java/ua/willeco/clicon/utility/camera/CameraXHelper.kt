package ua.willeco.clicon.utility.camera

import android.annotation.SuppressLint
import android.content.Context
import android.util.DisplayMetrics
import android.util.Log
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.common.util.concurrent.ListenableFuture
import com.google.gson.Gson
import ua.willeco.clicon.enums.DeviceType
import ua.willeco.clicon.model.QrScanDevice
import ua.willeco.clicon.mvp.contract.QrScanContract
import java.util.concurrent.Executors

class CameraXHelper(private val mContext: Context,private val previewView: PreviewView, private val listenerSendQrValue:QrScanContract.Repository):QrScanContract.Presenter {

    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>
    private lateinit var metric: DisplayMetrics
    private lateinit var mInstance:Any
    private lateinit var mCameraSelector: CameraSelector
    private lateinit var mImagePreview: Preview
    private lateinit var mImageCapture: ImageCapture
    private lateinit var mImageAnalysis:ImageAnalysis

    private var cameraProvider: ProcessCameraProvider? = null
    private var lensFacing = CameraSelector.LENS_FACING_BACK
    private val executor = Executors.newSingleThreadExecutor()

    private fun startCamera() {
        Log.d("qr","----> startCamera()")
        cameraProviderFuture = ProcessCameraProvider.getInstance(mContext).also {
            it.addListener(Runnable {
                mImagePreview = createImagePreview()
                mImageCapture = createImageCapture()
                mImageAnalysis = createImageAnalysis()

                cameraProvider = cameraProviderFuture.get()
                cameraProvider?.bindToLifecycle(mInstance as LifecycleOwner, mCameraSelector, mImagePreview,mImageCapture,mImageAnalysis)
            }, ContextCompat.getMainExecutor(mContext))
        }
    }

    private fun createCameraSelector() =
        CameraSelector.Builder().requireLensFacing(lensFacing).build()

    private fun createImagePreview() =
         Preview.Builder()
            .setTargetAspectRatio(CameraProviderOption.getAspectRatio(metric))
            .setTargetRotation(previewView.display.rotation)
            .build()
            .apply {
                setSurfaceProvider(previewView.createSurfaceProvider())
                previewView.preferredImplementationMode = PreviewView.ImplementationMode.TEXTURE_VIEW
            }

    private fun createImageAnalysis() =
        ImageAnalysis.Builder()
            .setImageQueueDepth(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()
            .also {
                it.setAnalyzer(executor,
                    QrAnalization { list ->
                        if (list.isNotEmpty()) {
                            Log.d("qr",list.toString())
                            for (barcode in list) {
                                if (!barcode.rawValue.isNullOrEmpty()) {
                                    validateQrCode(barcode.rawValue)
                                }
                            }
                        }
                    })
            }

    private fun createImageCapture() = ImageCapture.Builder()
            .setCaptureMode(ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY)
            .setTargetAspectRatio(CameraProviderOption.getAspectRatio(metric))
            .setTargetRotation(previewView.display.rotation)
            .build()

    override fun startScan(instance: Any) {
        mInstance = instance
        metric = CameraProviderOption.getMetrics(previewView)
        mCameraSelector = createCameraSelector()
        previewView.post { startCamera() }
    }

    @SuppressLint("RestrictedApi")
    override fun stopScan() {
        previewView.post(null)
        cameraProvider?.unbindAll()
        CameraX.unbindAll()
    }

    override fun validateQrCode(value: String?) {
        value.let {
            if (!value.isNullOrEmpty()){
                val mDeviseEntity = Gson().fromJson(value, QrScanDevice::class.java)
                mDeviseEntity.type.let {
                    if (it is DeviceType){
                        stopScan()
                        listenerSendQrValue.getQrScanValue(mDeviseEntity)
                    }
                }
            }
        }
    }

}